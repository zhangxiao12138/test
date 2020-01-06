package com.firecontrol.mapper.iotmapper;

import com.firecontrol.domain.entity.PatrolDeviceType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by mariry on 2020/1/6.
 */
public interface PatrolDeviceTypeMapper {

    PatrolDeviceType getById(@Param("id") Long id);

    Integer insert(PatrolDeviceType deviceType);

    Integer deleteById(@Param("id") Long id);

    List<PatrolDeviceType> getTopNode(@Param("vendorId") Long vendorId);

    List<PatrolDeviceType> getListByParentNodeId(@Param("vendorId") Long vendorId, @Param("parentNode") Long parentNodeId);



}
