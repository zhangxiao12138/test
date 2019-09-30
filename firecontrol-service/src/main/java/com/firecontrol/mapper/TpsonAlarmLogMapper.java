package com.firecontrol.mapper;

import com.firecontrol.domain.entity.TpsonAlarmLogEntity;
import org.apache.ibatis.annotations.Param;

/**
 * Created by mariry on 2019/9/30.
 */
public interface TpsonAlarmLogMapper {

    public TpsonAlarmLogEntity selectById(@Param("id") Long id);

    public Integer deleteById(@Param("id") Long id);

    public Integer insert(TpsonAlarmLogEntity log);

    public Integer insertSelective(TpsonAlarmLogEntity log);

    public Integer updateById(TpsonAlarmLogEntity log);

}
