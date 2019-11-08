package com.firecontrol.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.firecontrol.common.OpResult;
import com.firecontrol.domain.dto.SensorSearch;
import com.firecontrol.domain.entity.TpsonSensorEntity;
import com.firecontrol.domain.entity.TpsonSensorType;
import com.firecontrol.mapper.iotmapper.TpsonSensorMapper;
import com.firecontrol.mapper.iotmapper.TpsonSensorTypeMapper;
import com.firecontrol.service.TpsonSensorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by mariry on 2019/10/8.
 */
@Service
public class TpsonSensorServiceImpl implements TpsonSensorService {
    private static final Logger log = LoggerFactory.getLogger(TpsonSensorServiceImpl.class);

    @Autowired
    private TpsonSensorMapper tpsonSensorMapper;

    @Autowired
    private TpsonSensorTypeMapper tpsonSensorTypeMapper;

    @Override
    public OpResult getSensorListBySearch(SensorSearch search) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        if(search.getDeviceCode() == null){
            op.setMessage("设备编码为必传项!");
            op.setStatus(OpResult.OP_FAILED);
            return op;
        }
        List<TpsonSensorEntity> list = new ArrayList<>();
        try{
            list = tpsonSensorMapper.getSensorListBySearch(search);
            for(TpsonSensorEntity s : list){
                s.setSensorTypeName(formatSensorTypeName(s.getSensorType()));
            }
            op.setDataValue(list);

        }catch (Exception e){
            log.error("TpsonSensorServiceImpl.getSensorListBySearch error! param={}, e={}", search, e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
        }
        return op;
    }


    @Override
    public OpResult getSensorInfoById(Long id) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);

        if(id == null){
            op.setMessage("传感器id不可为空!");
            op.setStatus(OpResult.OP_SUCCESS);
            return op;
        }
        try{
            TpsonSensorEntity sensor = tpsonSensorMapper.selectById(id);
            op.setDataValue(sensor);
        }catch (Exception e){
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            log.error("getSensorInfoById error! id = {}, e = {}", id, e);
            return op;
        }
        return op;
    }

    @Override
    public OpResult updateSensorInfo(TpsonSensorEntity sensor) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        try{
            if(sensor.getId() == null){
                op.setStatus(OpResult.OP_FAILED);
                op.setMessage("id不可以为空!");
                return op;
            }
            tpsonSensorMapper.updateById(sensor);
        }catch (Exception e){
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            log.error("updateSensorInfo error! sensor = {}, e = {}", sensor, e);
            return op;
        }
        return op;
    }

    @Override
    public OpResult changeState(String id, Integer state) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        if(StringUtils.isEmpty(id) || state == null){
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage("传感器id或目标状态不可为空");
            return op;
        }
        try{
            //id,逗号分隔的id字符串

            tpsonSensorMapper.updateSensorState(Arrays.asList(id.split(",")), state);

        }catch (Exception e){
            log.error("TpsonSensorServiceImpl.changeState error! id = "
                    + id
                    + ", state = "
                    + state + ", e=" + e);
            op.setStatus(OpResult.OP_SUCCESS);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }

        return op;
    }

    @Override
    public Boolean insertSensor(TpsonSensorEntity sensor) {
        Boolean b = true;
        try{
            //tpsonSensorMapper.insert(sensor);
            tpsonSensorMapper.insertSelective(sensor);
        }catch (Exception e){
            log.error("TpsonSensorServiceImpl.insertSensor error! e=" + e
            + ",sensor=" + JSON.toJSONString(sensor));
            b = false;
            return b;
        }
        return b;
    }

    @Override
    public Boolean deleteSensorByDevice(List<String> deviceCodeList) {
        Boolean b = true;
        try{
            tpsonSensorMapper.deleteSensorByDevice(deviceCodeList);
        }catch (Exception e){
            log.error("TpsonSensorServiceImpl.deleteSensorByDevice error! e=" + e
                    + ",deviceCodeList=" + JSON.toJSONString(deviceCodeList));
            b = false;
            return b;
        }
        return b;
    }

    @Override
    public OpResult getSensorTypeList() {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);

        List<TpsonSensorType> list = tpsonSensorTypeMapper.getAll();

        op.setDataValue(list);
        return op;
    }

    private String formatSensorTypeName(Long type) {
        //TODO: 传感器类型与名称待入库维护
        String rtn = "";
        if(type == 24){
            rtn = "NB烟雾传感器";
        }else if(type == 25){
            rtn = "NB温度传感器";
        }else if(type == 32) {
            rtn = "防拆传感器";
        }
        return rtn;
    }

}