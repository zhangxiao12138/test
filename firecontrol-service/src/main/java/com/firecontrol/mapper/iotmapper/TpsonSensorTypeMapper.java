package com.firecontrol.mapper.iotmapper;

import com.firecontrol.domain.entity.TpsonSensorType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by mariry on 2019/11/4.
 */
public interface TpsonSensorTypeMapper {

    public List<TpsonSensorType> getAll();

    public TpsonSensorType getTypeByDeviceType(@Param("deviceType")Integer deviceType);

}
