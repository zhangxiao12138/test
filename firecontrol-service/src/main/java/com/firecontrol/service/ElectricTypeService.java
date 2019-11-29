package com.firecontrol.service;

import com.firecontrol.common.OpResult;

/**
 * Created by mariry on 2019/11/26.
 */
public interface ElectricTypeService {

    public OpResult addType(String name, Integer level);


    public OpResult listDeviceName(String name);


}
