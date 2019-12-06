package com.firecontrol.mapper.iotmapper;

import com.firecontrol.domain.dto.SensorSearch;
import com.firecontrol.domain.entity.SensorLog;
import com.firecontrol.domain.entity.TpsonSensorEntity;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by mariry on 2019/9/30.
 */
public interface TpsonSensorMapper {

    public TpsonSensorEntity selectById(@Param("id") Long id);

    public Integer deleteById(@Param("id") Long id);

    public Integer insert(TpsonSensorEntity sensor);

    public Integer insertSelective(TpsonSensorEntity sensor);

    public Integer updateById(TpsonSensorEntity sensor);

    public List<TpsonSensorEntity> getSensorListBySearch(SensorSearch sensorSearch);

    public Integer updateSensorState(@Param("idList") List<String> idList, @Param("state") Integer state);

    public Integer deleteSensorByDevice(@Param("idList")List<String> idList);



}
