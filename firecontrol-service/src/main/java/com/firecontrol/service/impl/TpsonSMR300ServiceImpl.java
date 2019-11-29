package com.firecontrol.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.firecontrol.common.OpResult;
import com.firecontrol.common.StringMD5Util;
import com.firecontrol.common.TBConstants;
import com.firecontrol.common.WebSocketService;
import com.firecontrol.domain.dto.*;
import com.firecontrol.domain.entity.*;
import com.firecontrol.mapper.iotmapper.*;
import com.firecontrol.mapper.ydmapper.FloorMapper;
import com.firecontrol.service.TpsonDeviceService;
import com.firecontrol.service.TpsonSMR3002Service;
import com.firecontrol.service.TpsonSensorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mariry on 2019/11/11.
 */
@Service
public class TpsonSMR300ServiceImpl implements TpsonSMR3002Service {
    private static final Logger log = LoggerFactory.getLogger(TpsonSMR3002Service.class);

    @Autowired
    private TpsonDeviceMapper tpsonDeviceMapper;
    @Autowired
    private TpsonSensorMapper tpsonSensorMapper;
    @Autowired
    private SensorLogMapper sensorLogMapper;
    @Autowired
    private TpsonDeviceHeartLogMapper heartLogMapper;
    @Autowired
    private TpsonAlarmMapper tpsonAlarmMapper;
    @Autowired
    private WebSocketService webSocketService;
    @Autowired
    private FloorMapper floorMapper;
    @Autowired
    private ElectricLogMapper logMapper;


    @Override
    public OpResult saveSMR3002Data(SMR3002Dto dto) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);


        log.info("SMR3002 data received: {}", JSON.toJSONString(dto));
        if(dto == null) {
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage("接收到数据为空!");
            return op;
        }
        //查询设备及传感器列表
        TpsonDeviceEntity device = tpsonDeviceMapper.selectByDeviceCode(dto.getDeviceCode());
        if(device == null){
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage("设备编码不存在");
            return op;
        }

        if(device.getRunningState() == TBConstants.RunningState.forbidden){
            //对forbidden的设备不做处理
            op.setMessage("设备已被禁用，不处理推送消息");
            return op;
        }

        SensorSearch search = new SensorSearch(device.getCode());
        List<TpsonSensorEntity> sensorList = tpsonSensorMapper.getSensorListBySearch(search);

        //data_type 固定为“REAL_DATA"/WARNING_DATA/ALARM_DATA/FAULT_DATA/IDENTIFY_DATA（电器识别推送）
        if(TBConstants.DATATYPE.realData.equals(dto.getDataType())){
            // 录入实时传感器数据到sensor_log表
            if(!handleRealTimeData(dto,device.getId(), device.getCode(), sensorList, dto.getTime())) {
                log.error("saveSMR3002Data handleRealTimeData error! param = {}", dto);
                op.setStatus(OpResult.OP_FAILED);
                op.setMessage(OpResult.OpMsg.SAVE_FAIL);
                return op;
            }
            //若设备状态是未激活，则更新为正常
            if(device.getRunningState() != TBConstants.RunningState.alarm || device.getRunningState() != TBConstants.RunningState.fault) {
                updateDeviceStatue(device.getId(), TBConstants.RunningState.online);
            }
        }else if(TBConstants.DATATYPE.alarmData.equals(dto.getDataType())){
            //生成并处理报警信息，且推送前端
            insertOrUpdateAlarm(dto, device);
            updateDeviceStatue(device.getId(), TBConstants.RunningState.alarm);

        }else if(TBConstants.DATATYPE.faultData.equals(dto.getDataType())) {
            //生成并处理故障数据
            //TODO:若runningState 不是禁用状态，则更改设备信息runningState信息为故障
            //更改设备状态为故障状态
            updateDeviceStatue(device.getId(), TBConstants.DeviceStatus.fault);

        }else if(TBConstants.DATATYPE.identifyData.equals(dto.getDataType())) {
            //电器识别数据，记录电器日志device_log, 等
            if(!handleIdentifyData(dto,device.getId(), device.getCode(), sensorList, device.getType().intValue(), device.getFloorId(), device.getBuildingId())) {
                log.error("saveSMR3002Data handleRealTimeData error! param = {}", dto);
                op.setStatus(OpResult.OP_FAILED);
                op.setMessage(OpResult.OpMsg.SAVE_FAIL);
                return op;
            }


        }





        return op;
    }

    @Override
    public String getTpsonToken() {
        String company = "000002";
        String secret = StringMD5Util.stringMD5(company + "#" + company);
        String token = StringMD5Util.stringMD5(company + "#" + secret + "#" + 1800 + "#" + System.currentTimeMillis());

        //坑：新增company时（即000002这种）,需要将生成的secret手工写入tp_third_party_certification表
//        Map rtnMap = new HashMap();
//        rtnMap.put("secret", secret);
//        rtnMap.put("token", token);

        return token;
    }


    /**
     * 录入实时记录数据
     * @param dto
     * @param deviceId
     * @param deviceCode
     * @param sensorList
     * @param logTime
     * @return
     */
    private Boolean handleIdentifyData(SMR3002Dto dto, Long deviceId, String deviceCode, List<TpsonSensorEntity> sensorList, Integer deviceType, String floorId, String buildingId) {
        Boolean b = true;
//        {
//            "time":1515294211, //UNIX时间戳，时间
//                "deviceType":"SMR3002", //固定为此
//                "deviceCode":"12345678", //设备编码
//                "dataType":"IDENTIFY_DATA", //固定为此
//                "data":{
//            "elecName":"电瓶车充电器",//电器名称
//                    "identifyResult":[{"name":"电瓶车充电器","possible":0.98},{"name":"电风扇","possible":0.6}], //识别结果集
//            "power":202, //功率,浮点数，单位瓦
//                    "elecType":0, //int，电器类型（0普通类型，2大功率电器，4发热电器，6大功率发热电器）
//                    "action":1, //int，电器状态：-1拔出，0无动作，1接入，2换挡或模式变化，只有接入(action=1)的电器才能学习
//                    "uuid":"3850335492070604572" //记录id
//        }
//        }

        ElectricLog log = new ElectricLog();
        SMR3002DataDto data = dto.getData();

        log.setElecName(data.getElecName());
        log.setAction(data.getAction());
        log.setAddTime(dto.getTime());
        log.setUuid(data.getUuid());
        log.setPower(data.getPower());
        log.setDeviceCode(dto.getDeviceCode());
        log.setDeviceId(deviceId.intValue());
        log.setType(deviceType);
        log.setFeature("");


        log.setPowerType(data.getElecType().byteValue());
        log.setPossibleStr(data.getIdentifyResult());
        log.setFloorId(floorId);
        log.setBuildingId(buildingId);

        List<ElectricPossible> list = JSON.parseArray(data.getIdentifyResult(), ElectricPossible.class);

        Double maxPossible;
        if(!CollectionUtils.isEmpty(list)){
            maxPossible  = (Double)list.get(0).getPossible() *100;
        }else{
            maxPossible = 0D;
        }
        log.setMaxPossible(maxPossible.intValue());//已知电器最大概率值,百分比

        logMapper.insert(log);

        return b;

    }

    /**
     * 录入实时传感器数据到sensor_log表
     * @param dto
     * @return
     */
    private Boolean handleRealTimeData(SMR3002Dto dto, Long deviceId, String deviceCode, List<TpsonSensorEntity> sensorList, Integer logTime) {
        Boolean b = true;

        if (dto.getData() != null) {
            SMR3002DataDto data = dto.getData();
            for (TpsonSensorEntity sensor : sensorList) {
                if (sensor.getSensorType() == TBConstants.SensorType.sydl) {//剩余电流
                    SensorLog log = new SensorLog(deviceId, deviceCode, sensor.getId(), sensor.getSensorType().intValue(),
                            logTime, new Byte((byte) (1)), new Byte((byte) (0)), data.getRealElectricity().toString());
                    sensorLogMapper.insert(log);
                } else if (sensor.getSensorType() == TBConstants.SensorType.dqwd && "1".equals(sensor.getCode())) {
                    SensorLog log = new SensorLog(deviceId, deviceCode, sensor.getId(), sensor.getSensorType().intValue(),
                            logTime, new Byte((byte) (1)), new Byte((byte) (0)), data.getTemperature1().toString());
                    sensorLogMapper.insert(log);
                } else if (sensor.getSensorType() == TBConstants.SensorType.dqwd && "2".equals(sensor.getCode())) {
                    SensorLog log = new SensorLog(deviceId, deviceCode, sensor.getId(), sensor.getSensorType().intValue(),
                            logTime, new Byte((byte) (1)), new Byte((byte) (0)), data.getTemperature2().toString());
                    sensorLogMapper.insert(log);
                } else if (sensor.getSensorType() == TBConstants.SensorType.dqwd && "3".equals(sensor.getCode())) {
                    SensorLog log = new SensorLog(deviceId, deviceCode, sensor.getId(), sensor.getSensorType().intValue(),
                            logTime, new Byte((byte) (1)), new Byte((byte) (0)), data.getTemperature3().toString());
                    sensorLogMapper.insert(log);
                } else if (sensor.getSensorType() == TBConstants.SensorType.dqwd && "4".equals(sensor.getCode())) {
                    SensorLog log = new SensorLog(deviceId, deviceCode, sensor.getId(), sensor.getSensorType().intValue(),
                            logTime, new Byte((byte) (1)), new Byte((byte) (0)), data.getTemperature4().toString());
                    sensorLogMapper.insert(log);
                } else if (sensor.getSensorType() == TBConstants.SensorType.yxdl) {//有效电流
                    SensorLog log = new SensorLog(deviceId, deviceCode, sensor.getId(), sensor.getSensorType().intValue(),
                            logTime, new Byte((byte) (1)), new Byte((byte) (0)), data.getCurrent().toString());
                    sensorLogMapper.insert(log);
                } else if (sensor.getSensorType() == TBConstants.SensorType.yxdy) {//有效电压
                    SensorLog log = new SensorLog(deviceId, deviceCode, sensor.getId(), sensor.getSensorType().intValue(),
                            logTime, new Byte((byte) (1)), new Byte((byte) (0)), data.getVoltage().toString());
                    sensorLogMapper.insert(log);
                } else if (sensor.getSensorType() == TBConstants.SensorType.gl) {//功率
                    SensorLog log = new SensorLog(deviceId, deviceCode, sensor.getId(), sensor.getSensorType().intValue(),
                            logTime, new Byte((byte) (1)), new Byte((byte) (0)), data.getPower().toString());
                    sensorLogMapper.insert(log);
                } else if (sensor.getSensorType() == TBConstants.SensorType.gl) {//能耗
                    SensorLog log = new SensorLog(deviceId, deviceCode, sensor.getId(), sensor.getSensorType().intValue(),
                            logTime, new Byte((byte) (1)), new Byte((byte) (0)), data.getRealElectricity().toString());
                    sensorLogMapper.insert(log);
                }else if (sensor.getSensorType() == TBConstants.SensorType.gl) {//故障电弧
                    SensorLog log = new SensorLog(deviceId, deviceCode, sensor.getId(), sensor.getSensorType().intValue(),
                            logTime, new Byte((byte) (1)), new Byte((byte) (0)), data.getFaultArcCount().toString());
                    sensorLogMapper.insert(log);
                }

            }
        }

        return b;
    }


    private Boolean insertOrUpdateAlarm(SMR3002Dto dto, TpsonDeviceEntity device) {
        //1. 有未处理的同设备同类型报警，则不增加新纪录，在原有的记录上，count 加1
        //2. 没有未处理的，则新建记录
        //3. 为了防止硬件bug，不再区分报警的具体类型，防拆、着火等，统一录为烟感报警

        //查询是否有已存在的同设备同类型未处理报警
        Boolean rtn = true;
        try{
            //10	通用报警:  所有用电故障都是通用报警
            TpsonAlarmEntity alarm = tpsonAlarmMapper.getUnhandleAlarmByDeviceInfo(dto.getDeviceCode(), (long)10);
            if(alarm != null){
                tpsonAlarmMapper.updateAlarmCountById(alarm.getId());

                //websocket 推送页面
                WsAlarmPush wAlarm = new WsAlarmPush();
                BeanUtils.copyProperties(alarm, wAlarm);
                wAlarm.setMsgType(1);
                WebSocketMsg wsmsg = new WebSocketMsg();
                wsmsg.setMsgType(1);
                wsmsg.setShowWindow(1);
                wsmsg.setData(wAlarm);
                webSocketService.sendInfo(JSON.toJSONString(wsmsg), null);

            }else{
                //新增记录
                //通用报警
                Floor floor = null;
                alarm = new TpsonAlarmEntity();
                alarm.setAddTime(dto.getTime());
                alarm.setType((long)10);//通用报警，type =10
                alarm.setLevel(1);//hardcode， 默认1:低
                alarm.setDeviceCode(dto.getDeviceCode());
                alarm.setDeviceId(device.getId());
                alarm.setDeviceName(device.getName());
                alarm.setDeviceType(device.getType());
                alarm.setDeviceVersion(device.getVersion());
                alarm.setIsOutdoor(device.getIsOutdoor());
                alarm.setIsReview(false);

                //TODO:设置传感器id
                // alarm.setSensorId();
                alarm.setSensorName(device.getName() + "传感器");
                alarm.setStatus((byte)0);


                //获取设备地址信息
                if(!StringUtils.isEmpty(device.getFloorId())){
                    floor = floorMapper.selectById(device.getFloorId());
                    if(floor == null){
                        floor = new Floor();
                        floor.setName("");
                    }
                }

                alarm.setDetail(floor.getName() + "用电传感器报警");
                alarm.setFloorName(floor.getName());
                alarm.setPosition(floor.getName());
                alarm.setSiteName(floor.getName());
                alarm.setIsPending(false);
                alarm.setCameraId(device.getCameraId());
                alarm.setCount(1);
                alarm.setRecoverTime(0);
                alarm.setIsDelete(false);
                //TODO:全部building_id由long改为string
                alarm.setBuildingId(device.getBuildingId());
                tpsonAlarmMapper.insert(alarm);


                //websocket 推送页面
                WsAlarmPush wAlarm = new WsAlarmPush();
                BeanUtils.copyProperties(alarm, wAlarm);
                wAlarm.setMsgType(1);
                WebSocketMsg wsmsg = new WebSocketMsg();
                wsmsg.setMsgType(1);
                wsmsg.setShowWindow(1);
                wsmsg.setData(wAlarm);

                webSocketService.sendInfo(JSON.toJSONString(wsmsg), null);

            }
            //添加设备心跳日志 device heart log
            insertDeviceHeartLog(device.getId());

        }catch (Exception e) {
            log.error("insertOrUpdateAlarm ERROR! e = {}", e);
            return false;
        }

        return rtn;
    }


    private Boolean insertDeviceHeartLog(Long deviceId) {
        //添加设备心跳日志 device heart log
        try{
            TpsonDeviceHeartLog log = new TpsonDeviceHeartLog();
            log.setAddTime((int)System.currentTimeMillis() / 1000);
            log.setDeviceId(deviceId);
            log.setOnline(true);
            heartLogMapper.insert(log);
            return true;
        }catch (Exception e) {
            log.error("insertDeviceHeartLog ERROR! deviceId: " + deviceId + ", e= " + JSON.toJSONString(e));
            return false;
        }

    }

    private Boolean updateDeviceStatue(Long deviceId, Integer runningState){
        tpsonDeviceMapper.updateDeviceRunningState(deviceId, runningState);
        return true;
    }
}
