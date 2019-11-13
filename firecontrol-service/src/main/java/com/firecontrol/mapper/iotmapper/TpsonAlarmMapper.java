package com.firecontrol.mapper.iotmapper;

import com.firecontrol.domain.dto.*;
import com.firecontrol.domain.entity.TpsonAlarmEntity;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by mariry on 2019/9/30.
 */
public interface TpsonAlarmMapper {

    public TpsonAlarmEntity selectById(@Param("id") Long id);

    public Integer deleteById(@Param("id") Long id);

    public Integer insert(TpsonAlarmEntity alarm);

    public Integer insertSelective(TpsonAlarmEntity alarm);

    public Integer updateById(TpsonAlarmEntity alarmEntity);

    public List<Integer> getAlarmCountByDeviceType(@Param("deviceTypeList") List<Long> deviceTypeList);

    public List<TpsonAlarmEntity> getAlarmByTime(@Param("deviceTypeList") List<Long> deviceTypeList, @Param("startTime") Long start,
                                                 @Param("endTime") Long end);

    public Integer getAlarmCountBySearch(@Param("deviceTypeList") List<Long>deviceTypeList,
                                                        @Param("alarmType")Integer alarmType,
                                                        @Param("status") Integer status,
                                                        @Param("isOutdoor") Integer isOutdoor,
                                                        @Param("deviceCode") String deviceCode,
                                                        @Param("startTime") Long startTime,
                                                        @Param("endTime") Long endTime);

    public List<TpsonAlarmEntity> getAlarmBySearch(@Param("deviceTypeList") List<Long>deviceTypeList,
                                                   @Param("alarmType")Integer alarmType,
                                                   @Param("status") Integer status,
                                                   @Param("isOutdoor") Integer isOutdoor,
                                                   @Param("deviceCode") String deviceCode,
                                                   @Param("startTime") Long startTime,
                                                   @Param("endTime") Long endTime,
                                                   @Param("currentPage")Integer currentPage,
                                                   @Param("length") Integer length);

    public List<Long> getUnhandledAlarmByIds(@Param("idList") List<String> idList);

    public Integer deleteHandledAlarmByIds(@Param("idList") List<String> idList);

    public Integer updateBatchDeal(@Param("idList") List<String> idList, @Param("status")Integer status, @Param("dealDetail") String dealDetail);

    public Integer updateAlarmDeal(@Param("id") Long id, @Param("status") Integer status, @Param("dealDetail") String dealDetail);

    public List<AlarmDealCountDto> dealTypeCount(@Param("deviceTypeList") List<Long>deviceTypeList,
                                                     @Param("startTime") Integer startTime,
                                                     @Param("endTime")Integer endTime,
                                                     @Param("companyId")String companyId);

    public List<AlarmTypeCountDto> alarmTypeCount(@Param("deviceTypeList") List<Long>deviceTypeList,
                                                  @Param("startTime") Integer startTime,
                                                  @Param("endTime")Integer endTime,
                                                  @Param("companyId")String companyId);


    public List<AlarmHandleCountDto> getAlarmHandleRank(@Param("deviceTypeList") List<Long>deviceTypeList,
                                                        @Param("startTime") Integer startTime,
                                                        @Param("endTime")Integer endTime,
                                                        @Param("companyId")String companyId);


    public TpsonAlarmEntity getUnhandleAlarmByDeviceInfo(@Param("deviceCode") String deviceCode,
                                                         @Param("alarmType") Long alarmType);

    public Integer updateAlarmCountById(@Param("id") Long id);

    public List<AlarmTypeCountDto> getAlarmTypeRank(@Param("startTime") Integer startTime,
                                                 @Param("endTime")Integer endTime,
                                                 @Param("companyId")Long companyId);

    public String getAlarmTypeNameByType(@Param("id") Integer id);

    public List<SumRateStatDto> homePageSumRateStat(@Param("startTime") Long startTime, @Param("endTime") Long endTime, @Param("companyId") Long companyId);


}
