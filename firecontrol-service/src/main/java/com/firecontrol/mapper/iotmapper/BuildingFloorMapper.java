package com.firecontrol.mapper.iotmapper;

import com.firecontrol.domain.entity.BuildingFloor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by mariry on 2020/1/9.
 */
public interface BuildingFloorMapper {

    BuildingFloor getById(@Param("id") Long id);

    Integer insert(BuildingFloor buildingFloor);

    Integer deleteById(@Param("id") Long id);

    Integer checkFloorName(@Param("vendorId")Long vendorId, @Param("floorName") String floorName);

    List<BuildingFloor> getFloorByCompanyId(@Param("vendorId") Long vendorId);

}
