package com.firecontrol.service;

import com.firecontrol.common.OpResult;

/**
 * Created by mariry on 2019/10/11.
 */
public interface TpsonAlarmService {

    OpResult getAlarmStatistics(Integer systemType, Long companyId);

    OpResult getAlarmTypeBySystemType(Integer systemType, Long deviceType);

    OpResult getAlarmTypeByDeviceType(Long deviceType);

    OpResult getAlarmTrendDiagram(Integer systemType, Integer companyId, Long start, Long end);

    OpResult getAlarmList(Integer systemType, Integer faultType, Integer status, Integer isOutdoor, String deviceCode, Long startTime, Long endTime, Integer currentPage, Integer length);

    OpResult deleteHandledAlarmByIds(String ids);

    OpResult batchHandle(String ids, Integer status, String dealDetail);

    OpResult getDealStatus();

    OpResult getAlarmDetailById(Long id);

    OpResult updateAlarmDetailById(Long id, Integer status, String dealDetail);

    OpResult dealTypeCount(Integer systemType, Integer startTime, Integer endTime, String companyId);

    OpResult getBuildingAlarmCount(Integer systemType, Integer startTime, Integer endTime, String companyId);

    OpResult alarmCountByType(Integer systemType, Integer startTime, Integer endTime, String companyId);

    OpResult getAlarmHandleRank(Integer systemType, Integer startTime, Integer endTime, String companyId);



}
