package com.firecontrol.mapper.iotmapper;

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

    public Integer countUnhandledFault(@Param("companyId")Long companyId);
    public Integer countMonthlyHandledFault(@Param("companyId")Long companyId,@Param("startTime") Integer startTime);
    public Integer countDayleHandledFault(@Param("companyId")Long companyId,@Param("startTime") Integer startTime);

    public List<Long> getUnhandledFaultByIds(@Param("idList") List<String> idList);

    public void deleteHandledFaultByIds(@Param("idList") List<String>idList);

    public Integer updateBatchDeal(@Param("idList") List<String> idList, @Param("dealDetail") String dealDetail);

}
