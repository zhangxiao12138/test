package com.firecontrol.mapper.iotmapper;

import com.firecontrol.domain.entity.ElectricType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by mariry on 2019/11/26.
 */
public interface ElectricTypeMapper {

    List<ElectricType> getAll();

    List<ElectricType> getByName(@Param("name") String name);

    void insert(ElectricType type);

    ElectricType getByFullName(@Param("name") String name);

    String getNameByType(@Param("id") Long id);


}
