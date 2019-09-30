package com.firecontrol.mapper;

import com.firecontrol.domain.entity.TpsonDeviceFaultTypeEntity;
import org.apache.ibatis.annotations.Param;

/**
 * Created by mariry on 2019/9/30.
 */
public interface TpsonDeviceFaultTypeMapper {

    public TpsonDeviceFaultTypeEntity selectById(@Param("id") Long id);

    public Integer deleteById(@Param("id") Long id);

    public Integer insert(TpsonDeviceFaultTypeEntity faultTypeEntity);

    public Integer insertSelective(TpsonDeviceFaultTypeEntity faultTypeEntity);

    public Integer updateById(TpsonDeviceFaultTypeEntity faultTypeEntity);

}
