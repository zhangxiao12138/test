package com.firecontrol.mapper.iotmapper;

import com.firecontrol.domain.entity.TpsonDeviceLogEntity;
import org.apache.ibatis.annotations.Param;

/**
 * Created by mariry on 2019/9/30.
 */
public interface TpsonDeviceLogMapper {

    public TpsonDeviceLogEntity selectById(@Param("id") Long id);

    public Integer deleteById(@Param("id") Long id);

    public Integer insert(TpsonDeviceLogEntity log);

    public Integer insertSelective(TpsonDeviceLogEntity log);

    public Integer updateById(TpsonDeviceLogEntity log);

}
