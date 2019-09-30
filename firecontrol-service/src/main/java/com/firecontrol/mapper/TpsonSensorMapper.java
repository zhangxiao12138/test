package com.firecontrol.mapper;

import com.firecontrol.domain.entity.TpsonSensorEntity;
import org.apache.ibatis.annotations.Param;

/**
 * Created by mariry on 2019/9/30.
 */
public interface TpsonSensorMapper {

    public TpsonSensorEntity selectById(@Param("id") Long id);

    public Integer deleteById(@Param("id") Long id);

    public Integer insert(TpsonSensorEntity log);

    public Integer insertSelective(TpsonSensorEntity log);

    public Integer updateById(TpsonSensorEntity log);

}
