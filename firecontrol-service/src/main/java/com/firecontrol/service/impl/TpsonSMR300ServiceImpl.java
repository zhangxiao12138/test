package com.firecontrol.service.impl;

import com.firecontrol.common.OpResult;
import com.firecontrol.common.StringMD5Util;
import com.firecontrol.common.TBConstants;
import com.firecontrol.domain.dto.SMR3002DataDto;
import com.firecontrol.domain.dto.SMR3002Dto;
import com.firecontrol.domain.dto.SensorSearch;
import com.firecontrol.domain.entity.SensorLog;
import com.firecontrol.domain.entity.TpsonDeviceEntity;
import com.firecontrol.domain.entity.TpsonSensorEntity;
import com.firecontrol.mapper.iotmapper.SensorLogMapper;
import com.firecontrol.mapper.iotmapper.TpsonDeviceMapper;
import com.firecontrol.mapper.iotmapper.TpsonSensorMapper;
import com.firecontrol.service.TpsonDeviceService;
import com.firecontrol.service.TpsonSMR3002Service;
import com.firecontrol.service.TpsonSensorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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



    @Override
    public OpResult saveSMR3002Data(SMR3002Dto dto) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);

        log.info("SMR3002 data received: {}", dto);
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
        SensorSearch search = new SensorSearch(device.getCode());
        List<TpsonSensorEntity> sensorList = tpsonSensorMapper.getSensorListBySearch(search);


        //data_type 固定为“REAL_DATA"/WARNING_DATA/ALARM_DATA/FAULT_DATA/IDENTIFY_DATA（电器识别推送）
        if(TBConstants.DATATYPE.realData.equals(dto.getDataType())){
            // 录入实时传感器数据到sensor_log表
            if(handleRealTimeData(dto,device.getId(), device.getCode(), sensorList, dto.getTime())) {
                log.error("saveSMR3002Data handleRealTimeData error! param = {}", dto);
                op.setStatus(OpResult.OP_FAILED);
                op.setMessage(OpResult.OpMsg.SAVE_FAIL);
                return op;
            }

        }else if(TBConstants.DATATYPE.alarmData.equals(dto.getDataType())){
            //生成并处理报警信息，且推送前端



        }else if(TBConstants.DATATYPE.faultData.equals(dto.getDataType())) {
            //生成并处理故障数据
        }else if(TBConstants.DATATYPE.identifyData.equals(dto.getDataType())) {
            //电器识别数据，记录电器日志device_log, 等

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
                } else if (sensor.getSensorType() == TBConstants.SensorType.dqwd && sensor.getCode() == "1") {
                    SensorLog log = new SensorLog(deviceId, deviceCode, sensor.getId(), sensor.getSensorType().intValue(),
                            logTime, new Byte((byte) (1)), new Byte((byte) (0)), data.getTemperature1().toString());
                    sensorLogMapper.insert(log);
                } else if (sensor.getSensorType() == TBConstants.SensorType.dqwd && sensor.getCode() == "2") {
                    SensorLog log = new SensorLog(deviceId, deviceCode, sensor.getId(), sensor.getSensorType().intValue(),
                            logTime, new Byte((byte) (1)), new Byte((byte) (0)), data.getTemperature2().toString());
                    sensorLogMapper.insert(log);
                } else if (sensor.getSensorType() == TBConstants.SensorType.dqwd && sensor.getCode() == "3") {
                    SensorLog log = new SensorLog(deviceId, deviceCode, sensor.getId(), sensor.getSensorType().intValue(),
                            logTime, new Byte((byte) (1)), new Byte((byte) (0)), data.getTemperature3().toString());
                    sensorLogMapper.insert(log);
                } else if (sensor.getSensorType() == TBConstants.SensorType.dqwd && sensor.getCode() == "4") {
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

}
