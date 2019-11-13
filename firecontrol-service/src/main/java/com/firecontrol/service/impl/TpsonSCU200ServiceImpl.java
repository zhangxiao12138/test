package com.firecontrol.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.firecontrol.common.OpResult;
import com.firecontrol.common.TBConstants;
import com.firecontrol.domain.dto.SCU200DataDto;
import com.firecontrol.domain.dto.SCU200Dto;
import com.firecontrol.domain.entity.TpsonAlarmEntity;
import com.firecontrol.domain.entity.TpsonDeviceEntity;
import com.firecontrol.domain.entity.TpsonDeviceFaultEntity;
import com.firecontrol.domain.entity.TpsonDeviceHeartLog;
import com.firecontrol.mapper.iotmapper.TpsonAlarmMapper;
import com.firecontrol.mapper.iotmapper.TpsonDeviceFaultMapper;
import com.firecontrol.mapper.iotmapper.TpsonDeviceHeartLogMapper;
import com.firecontrol.mapper.iotmapper.TpsonDeviceMapper;
import com.firecontrol.service.TpsonSCU200Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mariry on 2019/11/6.
 */
@Service
public class TpsonSCU200ServiceImpl implements TpsonSCU200Service {

    private static final Logger log = LoggerFactory.getLogger(TpsonSCU200ServiceImpl.class);

    @Autowired
    private TpsonDeviceMapper tpsonDeviceMapper;

    @Autowired
    private TpsonAlarmMapper tpsonAlarmMapper;
    @Autowired
    private TpsonDeviceHeartLogMapper heartLogMapper;
    @Autowired
    private TpsonDeviceFaultMapper tpsonFaultMapper;

    @Override
    public OpResult saveSCU200Data(SCU200Dto dto) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);

        try{
            //TODO:塞队列
            //TODO:  接收信息后，修改设备列表相应的设备的状态（由未激活 变为 正常、报警、离线 等等）
            TpsonDeviceEntity device = tpsonDeviceMapper.selectByDeviceCode(dto.getDeviceCode());
            if(device == null) {
                log.error("saveSCU200Data ERROR! 设备不存在! param= {}", JSON.toJSONString(dto));
                return op;
            }

            SCU200DataDto data = dto.getData();
            if(data != null){

                if(checkAlarmExist(data)) {
                    //TODO: 添加或更新报警信息，添加设备心跳日志,
                    //TODO:添加事务层，写入小事务
                    insertOrUpdateAlarm(dto, device);
                    updateDeviceStatue(device.getId(), TBConstants.DeviceStatus.alarm);


                }else if(checkFaultExist(data)) {
                    //TODO: 添加或更新故障信息，添加设备心跳日志

                    insertOrUpdateFault(dto, device);
                    updateDeviceStatue(device.getId(), TBConstants.DeviceStatus.fault);
                }else {
                    //TODO: 一切正常，仅保留心跳日志
                    insertDeviceHeartLog(device.getId());
                    updateDeviceStatue(device.getId(), TBConstants.DeviceStatus.online);
                }

            }
        }catch (Exception e) {
            log.error("saveSCU200Data EXCEPTION! e: {}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }

        return op;

    }


    private Boolean checkAlarmExist(SCU200DataDto data){
        if(!StringUtils.isEmpty(data.getSmokeStatus()) && data.getSmokeStatus().equals(TBConstants.AlarmStatus.ALARM)) {
            //烟雾报警
            return true;
        }else if(!StringUtils.isEmpty(data.getTemperaturesStatus()) && data.getTemperaturesStatus().equals(TBConstants.AlarmStatus.ALARM)){
            //温度报警
            return true;
        }else if(!StringUtils.isEmpty(data.getDismantleStatus()) && data.getDismantleStatus().equals(TBConstants.AlarmStatus.DISMENTLE)){
            return true;
        }
        return false;
    }

    private Boolean checkFaultExist(SCU200DataDto data) {
        if(!StringUtils.isEmpty(data.getSmokeStatus()) && data.getSmokeStatus().equals(TBConstants.AlarmStatus.SMOCK_FAULT)) {
            //烟雾故障
            return true;
        }else if(!StringUtils.isEmpty(data.getTemperaturesStatus()) && data.getTemperaturesStatus().equals(TBConstants.AlarmStatus.TEMP_FAULT)){
            //温度故障
            return true;
        }else if(!StringUtils.isEmpty(data.getBatteryStatus()) && data.getBatteryStatus().equals(TBConstants.AlarmStatus.BATTERY_LOW)){
            //电池电量低
            return true;
        }
        return false;
    }


    private Boolean insertOrUpdateAlarm(SCU200Dto dto, TpsonDeviceEntity device) {
        //1. 有未处理的同设备同类型报警，则不增加新纪录，在原有的记录上，count 加1
        //2. 没有未处理的，则新建记录
        //3. 为了防止硬件bug，不再区分报警的具体类型，防拆、着火等，统一录为烟感报警

        //查询是否有已存在的同设备同类型未处理报警
        Boolean rtn = true;
        try{
            TpsonAlarmEntity alarm = tpsonAlarmMapper.getUnhandleAlarmByDeviceInfo(dto.getDeviceCode(), (long)19);
            if(alarm != null){
                tpsonAlarmMapper.updateAlarmCountById(alarm.getId());
            }else{
                //新增记录
                //NB烟雾报警
                alarm = new TpsonAlarmEntity();
                alarm.setAddTime(dto.getTime());
                alarm.setType((long)19);//NB烟雾报警，type =19
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
                alarm.setSensorName(device.getName() + "NB烟雾传感器000003");
                alarm.setStatus((byte)0);

                alarm.setDetail("NB烟雾传感器报警");//TODO:写成地址+ NB传感器报警的形式
                alarm.setFloorName("凤凰城二楼消火栓急救箱");
                alarm.setPosition("凤凰城二楼消火栓急救箱");//TODO: 设为设备地址
                alarm.setSiteName("凤凰城二楼消火栓急救箱");//TODO:设为设备地址
                alarm.setIsPending(false);
                alarm.setCameraId(device.getCameraId());
                alarm.setCount(1);
                alarm.setRecoverTime(0);
                alarm.setIsDelete(false);
                //TODO:全部building_id由long改为string
                // alarm.setBuildingId(device.getBuildingId());
                tpsonAlarmMapper.insert(alarm);
            }
            //添加设备心跳日志 device heart log
            insertDeviceHeartLog(device.getId());

        }catch (Exception e) {
            log.error("insertOrUpdateAlarm ERROR! e = {}", e);
            return false;
        }

        return rtn;
    }

    private Boolean insertOrUpdateFault(SCU200Dto dto, TpsonDeviceEntity device) {

        //直接添加故障信息
        TpsonDeviceFaultEntity fault = new TpsonDeviceFaultEntity();



        return true;

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
