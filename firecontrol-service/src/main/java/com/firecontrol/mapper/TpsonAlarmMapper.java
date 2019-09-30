package com.firecontrol.mapper;

import com.firecontrol.domain.entity.TpsonAlarmEntity;
import org.apache.ibatis.annotations.Param;

/**
 * Created by mariry on 2019/9/30.
 */
public interface TpsonAlarmMapper {
    public TpsonAlarmEntity selectById(@Param("id") Long id);

    public Integer deleteById(@Param("id") Long id);

    public Integer insert(TpsonAlarmEntity alarm);

    public Integer insertSelective(TpsonAlarmEntity alarm);

    public Integer updateById(TpsonAlarmEntity alarmEntity);



}
