package com.firecontrol.service;

import com.firecontrol.common.OpResult;
import com.firecontrol.domain.entity.TpsonDeviceEntity;

/**
 * Created by mariry on 2019/9/30.
 */
public interface TpsonDeviceService {

    public OpResult insertDevice(TpsonDeviceEntity device);

    OpResult getDeviceByPage();

    OpResult getDeviceById();

}
