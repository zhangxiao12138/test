package com.firecontrol.mapper;

import com.firecontrol.domain.entity.TpsonDeviceEntity;
import com.firecontrol.domain.entity.TpsonDeviceLogEntity;
import org.apache.ibatis.annotations.Param;

/**
 * Created by mariry on 2019/9/30.
 */
public interface TpsonDeviceMapper {

    public TpsonDeviceEntity selectById(@Param("id") Long id);

    public Integer deleteById(@Param("id") Long id);

    public Integer insert(TpsonDeviceEntity log);

    public Integer insertSelective(TpsonDeviceEntity log);

    public Integer updateById(TpsonDeviceEntity log);


}
