package com.firecontrol.mapper;

import com.firecontrol.domain.entity.DeviceInfoEntity;

/**
 * Created by mariry on 2019/9/27.
 */
public interface DeviceInfoMapper {

    public void insertDevice(DeviceInfoEntity device);

    public Integer updateDevice(DeviceInfoEntity device);

    /**
     *
     * @param device
     * @return
     */
    public Integer updateDeviceDelete(DeviceInfoEntity device);

    /**
     * 修改禁用、启用标志
     * @param device
     * @return
     */
    public Integer updateDeviceRunningState(DeviceInfoEntity device);



}
