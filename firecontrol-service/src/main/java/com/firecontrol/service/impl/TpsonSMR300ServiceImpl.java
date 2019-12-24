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
import com.firecontrol.service.TpsonSMR3002AlarmService;
import com.firecontrol.service.TpsonSMR3002Service;
import com.firecontrol.service.TpsonSensorService;
import com.mycorp.vodplatform.server.TerminalServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    private TpsonDeviceFaultMapper tpsonFaultMapper;
    @Autowired
    private WebSocketService webSocketService;
    @Autowired
    private FloorMapper floorMapper;
    @Autowired
    private ElectricLogMapper logMapper;
    @Autowired
    private ElectricTypeMapper electricTypeMapper;
    @Autowired
    private ElectricConfigMapper electricConfigMapper;

    @Value("${tpson.company}")
    private String companyCode;



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
            //获取主机功率报警阈值与是否断电配置
            //TODO:配置/获取companyCode
            ElectricConfig config = electricConfigMapper.getByCompanyCode("0");


            // 录入实时传感器数据到sensor_log表
            if(!handleRealTimeData(dto,device.getId(), device.getCode(), sensorList, dto.getTime(), config)) {
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
            insertOrUpdateFault(dto, device);
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
        String secret = StringMD5Util.stringMD5("000002" + "#" + "000002");
        String token = StringMD5Util.stringMD5("000002" + "#" + secret + "#" + 1800 + "#" + System.currentTimeMillis());

        //坑：新增company时（即000002这种）,需要将生成的secret手工写入tp_third_party_certification表

        return token;
    }


    /**
     * 录入实时记录数据
     * @param dto
     * @param deviceId
     * @param deviceCode
     * @param sensorList
     * @return
     */
    private Boolean handleIdentifyData(SMR3002Dto dto, Long deviceId, String deviceCode, List<TpsonSensorEntity> sensorList, Integer deviceType, String floorId, String buildingId) {
        Boolean b = true;

        ElectricLog log = new ElectricLog();
        SMR3002DataDto data = dto.getData();

        log.setElecName(data.getElecName());
        log.setAction(data.getAction());
        log.setAddTime(dto.getTime());
        log.setUuid(data.getUuid());
        log.setPower(data.getPower());
        log.setDeviceCode(dto.getDeviceCode());
        log.setDeviceId(deviceId.intValue());

        String deviceName = tpsonDeviceMapper.getDeviceNameById(deviceId);
        log.setSiteName(deviceName);

        Long typeId = electricTypeMapper.getTypeByName(data.getElecName());
        if(typeId != null){
            log.setType(typeId.intValue());
        }
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
    private Boolean handleRealTimeData(SMR3002Dto dto, Long deviceId, String deviceCode, List<TpsonSensorEntity> sensorList, Integer logTime,ElectricConfig config) {
        Boolean b = true;

        if (dto.getData() != null) {
            SMR3002DataDto data = dto.getData();
            for (TpsonSensorEntity sensor : sensorList) {
                if (sensor.getSensorType() == TBConstants.SensorType.sydl) {//剩余电流
                    SensorLog log = new SensorLog(deviceId, deviceCode, sensor.getId(), sensor.getSensorType().intValue(),
                            logTime, new Byte((byte) (1)), new Byte((byte) (0)), data.getResidueCurrent().toString());
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
                    if(data.getPower() != null && data.getPower() > config.getDevicePowerThreshold()) {
                        //TODO：主机功率过载,添加报警信息，websocket 推送，判断是否需要断电
                        		ExecutorService e = Executors.newFixedThreadPool(3);        //新建线程池3
		                        e.execute(new TpsonSMR3002AlarmService(config, dto, deviceCode));

                    }

                } else if (sensor.getSensorType() == TBConstants.SensorType.nh) {//能耗
                    SensorLog log = new SensorLog(deviceId, deviceCode, sensor.getId(), sensor.getSensorType().intValue(),
                            logTime, new Byte((byte) (1)), new Byte((byte) (0)), data.getRealElectricity().toString());
                    sensorLogMapper.insert(log);
                }else if (sensor.getSensorType() == TBConstants.SensorType.gzdh) {//故障电弧
                    SensorLog log = new SensorLog(deviceId, deviceCode, sensor.getId(), sensor.getSensorType().intValue(),
                            logTime, new Byte((byte) (1)), new Byte((byte) (0)), data.getFaultArcCount().toString());
                    sensorLogMapper.insert(log);
                }

            }
        }

        return b;
    }
    private Boolean insertOrUpdateFault(SMR3002Dto dto, TpsonDeviceEntity device) {
        Boolean rtn = true;
        try{
            // 用电故障类型：剩余电流，温度1，温度2，温度3，温度4，故障电弧，短路
            TpsonDeviceFaultEntity fault = tpsonFaultMapper.getUnhandleFaultByDeviceCode(dto.getDeviceCode(), (long)5);
            if(fault != null){
                tpsonFaultMapper.updateFaultCountById(fault.getId());

                //故障无需推送页面

            }else{
                //新增
                //通用传感器故障 5
                Floor floor = null;
                fault = new TpsonDeviceFaultEntity();
                fault.setAddTime(dto.getTime());
                fault.setType((long)5);//通用故障，type =5
                fault.setDeviceCode(dto.getDeviceCode());
                fault.setDeviceId(device.getId());
                fault.setDeviceName(device.getName());
                fault.setDeviceType(device.getType());
                fault.setDeviceVersion(device.getVersion());
                fault.setIsOutdoor(device.getIsOutdoor());

                //TODO:设置传感器id
                // alarm.setSensorId();
                fault.setSensorName(device.getName() + "传感器");
                fault.setStatus((byte)0);


                //获取设备地址信息
                if(!StringUtils.isEmpty(device.getFloorId())){
                    floor = floorMapper.selectById(device.getFloorId());
                    if(floor == null){
                        floor = new Floor();
                        floor.setName("");
                    }
                }

                fault.setDetail(floor.getName() + "用电传感器故障" + dto.getData().getFaultTypeString());
                fault.setFloorName(floor.getName());
                fault.setPosition(floor.getName());
                fault.setIsPending(false);
                fault.setCount(1);
                fault.setDf(0);
                //TODO:全部building_id由long改为string
                fault.setBuildingId(device.getBuildingId());
                tpsonFaultMapper.insert(fault);

                //故障无需页面推送

            }
            //添加设备心跳日志 device heart log
            insertDeviceHeartLog(device.getId());

        }catch (Exception e) {
            log.error("insertOrUpdateAlarm ERROR! e = {}", e);
            return false;
        }

        return rtn;
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
