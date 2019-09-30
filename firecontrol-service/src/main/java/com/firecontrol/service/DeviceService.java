package com.firecontrol.service;

import com.firecontrol.common.OpResult;
import com.firecontrol.domain.dto.DeviceSearchDto;

/**
 * Created by mariry on 2019/9/27.
 */
public interface DeviceService {

    public OpResult getDeviceListByType(DeviceSearchDto search);




}
