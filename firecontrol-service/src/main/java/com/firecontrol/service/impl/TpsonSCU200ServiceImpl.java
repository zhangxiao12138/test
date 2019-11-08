package com.firecontrol.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.firecontrol.common.OpResult;
import com.firecontrol.common.TBConstants;
import com.firecontrol.domain.dto.SCU200DataDto;
import com.firecontrol.domain.dto.SCU200Dto;
import com.firecontrol.domain.entity.TpsonAlarmEntity;
import com.firecontrol.domain.entity.TpsonDeviceEntity;
import com.firecontrol.mapper.iotmapper.TpsonAlarmMapper;
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

    @Override
    public OpResult saveSCU200Data(SCU200Dto dto) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);

        try{
            //TODO:塞队列

            TpsonDeviceEntity device = tpsonDeviceMapper.selectByDeviceCode(dto.getDeviceCode());
            SCU200DataDto data = dto.getData();
            if(data != null){
                TpsonAlarmEntity alarm = new TpsonAlarmEntity();
                if(!StringUtils.isEmpty(data.getSmokeStatus()) && data.getSmokeStatus().equals(TBConstants.AlarmStatus.ALARM)) {
                    //1. 有未处理的同设备同类型报警，则不增加新纪录，在原有的记录上，count 加1
                    //2. 没有未处理的，则新建记录

                    //查询是否有已存在的同设备同类型未处理报警
                    alarm = tpsonAlarmMapper.getUnhandleAlarmByDeviceInfo(dto.getDeviceCode(), (long)19);
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
}
