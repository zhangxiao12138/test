package com.firecontrol.service;

import com.firecontrol.common.OpResult;
import com.firecontrol.domain.dto.DeviceSearch;
import com.firecontrol.domain.entity.TpsonDeviceEntity;
import io.swagger.models.auth.In;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Created by mariry on 2019/9/30.
 */
public interface TpsonDeviceService {

    public OpResult insertDevice(TpsonDeviceEntity device);

    OpResult updateDeviceInfo(TpsonDeviceEntity device);

    OpResult getDeviceByPage(DeviceSearch device);

    OpResult getDeviceById(Long id);

    OpResult changeDeviceStatus(String ids, Integer status);

    OpResult deleteDeviceByIds(String ids);

    OpResult faultCount(Long companyId);

    OpResult getDeviceTypeList();

    OpResult getDeviceVersionList(Long deviceType);

    OpResult getDeviceByCode(String deviceCode);

    OpResult getDeviceStatusStatic(Integer systemType);

    OpResult offLineStat(Integer systemType, Long companyId);

    OpResult deviceLog(String deviceCode, String logData, Integer startTime, Integer endTime, Integer currentPage, Integer length);

    OpResult deviceStat(Long companyId);
}
