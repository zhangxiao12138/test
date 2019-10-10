package com.firecontrol.mapper;

import com.firecontrol.domain.entity.TpsonDeviceFaultEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by mariry on 2019/9/30.
 */
public interface TpsonDeviceFaultMapper {

    public TpsonDeviceFaultEntity selectById(@Param("id") Long id);

    public Integer deleteById(@Param("id") Long id);

    public Integer insert(TpsonDeviceFaultEntity fault);

    public Integer insertSelective(TpsonDeviceFaultEntity fault);

    public Integer updateById(TpsonDeviceFaultEntity fault);

    public List<TpsonDeviceFaultEntity> getFaultByTime(@Param("deviceTypeList") List<Long>deviceTypeList,
                                                       @Param("startTime") Long startTime,
                                                       @Param("endTime") Long endTime);

    public Integer getFaultCountBySearch(@Param("deviceTypeList") List<Long>deviceTypeList,
                                 @Param("faultType")Integer faultType,
                                 @Param("status") Integer status,
                                 @Param("isOutdoor") Integer isOutdoor,
                                 @Param("deviceCode") String deviceCode,
                                 @Param("startTime") Long startTime,
                                 @Param("endTime") Long endTime);

    public List<TpsonDeviceFaultEntity> getFaultBySearch(@Param("deviceTypeList") List<Long>deviceTypeList,
                                                         @Param("faultType")Integer faultType,
                                                         @Param("status") Integer status,
                                                         @Param("isOutdoor") Integer isOutdoor,
                                                         @Param("deviceCode") String deviceCode,
                                                         @Param("startTime") Long startTime,
                                                         @Param("endTime") Long endTime,
                                                         @Param("currentPage") Integer currentPage,
                                                         @Param("length") Integer length);


}
