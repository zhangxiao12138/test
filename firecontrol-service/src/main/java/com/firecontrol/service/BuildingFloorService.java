package com.firecontrol.service;

import com.firecontrol.common.OpResult;
import com.firecontrol.domain.entity.BuildingFloor;

/**
 * Created by mariry on 2020/1/9.
 */
public interface BuildingFloorService {

    OpResult addFloor(Long vendorId, String floorName);

    OpResult getFloorById(Long floorId, Long vendorId);

    OpResult getFloorByVendor(Long vendorId);


}
