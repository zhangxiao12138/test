package com.firecontrol.service;

import com.firecontrol.common.OpResult;
import com.firecontrol.common.TBConstants;
import com.firecontrol.domain.dto.DeviceSearch;
import com.firecontrol.domain.dto.DeviceUpdateDto;
import com.firecontrol.domain.entity.ElectricConfig;
import com.firecontrol.domain.entity.ElectricLog;
import com.firecontrol.domain.entity.TpsonDeviceEntity;
import io.swagger.models.auth.In;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Created by mariry on 2019/9/30.
 */
public interface TpsonDeviceService {

    public OpResult insertDevice(TpsonDeviceEntity device);

    OpResult updateDeviceInfo(DeviceUpdateDto device);

    OpResult getDeviceByPage(DeviceSearch device);

    OpResult getDeviceList(DeviceSearch device);

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

    OpResult getElectricDiagram(Integer companyId, Long start, Long end, String deviceCode);

    OpResult getElectricDeviceStatistic(DeviceSearch search);

    //电器设备实时监控数据
    OpResult realTimeWatch(DeviceSearch search);

    //反向控制电闸
    OpResult setRelay(String deviceCode, Integer switchOn);

    //电器设备报警阈值配置
    OpResult updateThreshold(ElectricConfig search);
    //获取电器设备报警阈值
    OpResult getThreshold(String companyCode);


}
