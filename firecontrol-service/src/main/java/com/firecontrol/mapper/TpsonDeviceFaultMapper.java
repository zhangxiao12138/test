package com.firecontrol.mapper;

import com.firecontrol.domain.entity.TpsonDeviceFaultEntity;
import org.apache.ibatis.annotations.Param;

/**
 * Created by mariry on 2019/9/30.
 */
public interface TpsonDeviceFaultMapper {

    public TpsonDeviceFaultEntity selectById(@Param("id") Long id);

    public Integer deleteById(@Param("id") Long id);

    public Integer insert(TpsonDeviceFaultEntity fault);

    public Integer insertSelective(TpsonDeviceFaultEntity fault);

    public Integer updateById(TpsonDeviceFaultEntity fault);
}
