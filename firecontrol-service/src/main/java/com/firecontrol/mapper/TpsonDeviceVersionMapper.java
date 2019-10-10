package com.firecontrol.mapper;

import com.firecontrol.domain.entity.TpsonDeviceVersion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by mariry on 2019/10/9.
 */
public interface TpsonDeviceVersionMapper {

    TpsonDeviceVersion selectById(@Param("id") Long id);

    List<TpsonDeviceVersion> selectByDeviceType(@Param("deviceType") Long deviceType);


}
