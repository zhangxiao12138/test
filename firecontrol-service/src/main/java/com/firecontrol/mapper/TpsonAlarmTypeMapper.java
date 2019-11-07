package com.firecontrol.mapper;

import com.firecontrol.domain.entity.TpsonAlarmType;
import com.firecontrol.domain.entity.TpsonDeviceType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by mariry on 2019/10/11.
 */
public interface TpsonAlarmTypeMapper {

    public TpsonDeviceType selectById(@Param("id") Long id);

    public Integer deleteById(@Param("id") Long id);

    public Integer insert(TpsonAlarmType alarm);

    public List<TpsonAlarmType> getBySystemDeviceType(@Param("systemType") Integer systemType, @Param("deviceType") Long deviceType);

    public List<TpsonAlarmType> getByDeviceType(@Param("deviceType") Long deviceType);

}
