package com.firecontrol.mapper.iotmapper;

import com.firecontrol.domain.entity.TpsonDeviceType;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by mariry on 2019/10/10.
 */
public interface TpsonDeviceTypeMapper {

    public TpsonDeviceType selectById(@Param("id") Long id);

    public Integer deleteById(@Param("id") Long id);

    public Integer insert(TpsonDeviceType deviceType);

    public Integer updateByIdSelective(TpsonDeviceType deviceType);

    public List<Long> getDeviceTypeBySystemType(@Param("systemType") Integer systemType);

    public String getDeviceTypeNameByType(@Param("type") Integer type);

}


