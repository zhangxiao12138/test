package com.firecontrol.service;

import com.firecontrol.common.OpResult;
import com.firecontrol.domain.dto.SensorSearch;
import com.firecontrol.domain.entity.TpsonSensorEntity;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

/**
 * Created by mariry on 2019/10/8.
 */
public interface TpsonSensorService {

    public OpResult getSensorListBySearch(SensorSearch search);

    public OpResult getSensorInfoById(Long id);

    public OpResult updateSensorInfo(TpsonSensorEntity sensor);

    public OpResult changeState(Long id, Integer state);

    public Boolean insertSensor(TpsonSensorEntity sensor);

    public Boolean deleteSensorByDevice(List<String> deviceCodeList);

}
