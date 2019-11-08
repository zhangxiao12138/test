package com.firecontrol.mapper.iotmapper;

import com.firecontrol.domain.entity.TpsonDeviceHeartLog;
import org.apache.ibatis.annotations.Param;

/**
 * Created by mariry on 2019/10/16.
 */
public interface TpsonDeviceHeartLogMapper {

    TpsonDeviceHeartLog selectById(@Param("id") Long id);

    Integer deleteById(@Param("id") Long id);

    Integer insert(TpsonDeviceHeartLog log);

    Integer update(TpsonDeviceHeartLog log);

}
