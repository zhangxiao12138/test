package com.firecontrol.mapper.iotmapper;

import com.firecontrol.domain.entity.SensorLog;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by mariry on 2019/11/21.
 */
public interface SensorLogMapper {

    public SensorLog getById(@Param("id") Long id);


    public Integer deleteById(@Param("id") Long id);

    public void insert(SensorLog log);

    List<SensorLog> getRealTimeData(@Param("deviceCode") String deviceCode, @Param("startTime") Integer startTime, @Param("endTime") Integer endTime);



}
