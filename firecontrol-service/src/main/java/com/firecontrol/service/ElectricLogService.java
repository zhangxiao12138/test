package com.firecontrol.service;

import com.firecontrol.common.OpResult;
import com.firecontrol.domain.entity.ElectricLog;

/**
 * Created by mariry on 2019/11/26.
 */
public interface ElectricLogService {

    public OpResult learn(Long id, Long type);

    public OpResult getLog(ElectricLog search);

    OpResult accessStatistic(Integer isOutdoor, Long deviceId, String deviceCode, Long companyId);

}
