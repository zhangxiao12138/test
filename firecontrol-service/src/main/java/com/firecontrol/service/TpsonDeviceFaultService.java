package com.firecontrol.service;

import com.firecontrol.common.OpResult;

import java.util.Date;

/**
 * Created by mariry on 2019/10/9.
 */
public interface TpsonDeviceFaultService {

    public OpResult getFaultStatistics(Integer systemType, Long companyId);

    public OpResult getFaultType();

    public OpResult getFaultTrendDiagram(Integer systemType, Integer companyId, Long startTime, Long endTime);

    public OpResult getFaultList(Integer systemType, Integer faultType, Integer status, Integer isOutdoor, String deviceCode, Long startTime, Long endTime, Integer currentPage, Integer length);

    public OpResult getFaultDetailById(Long id);

    public OpResult updateFaultDetailById(Long id, String dealDetail);

    public OpResult deleteHandledFaultByIds(String ids);

    public OpResult batchDeal(String ids, String dealDetail);

}
