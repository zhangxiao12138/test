package com.firecontrol.mapper.iotmapper;

import com.firecontrol.domain.entity.ElectricLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by mariry on 2019/11/25.
 */
public interface ElectricLogMapper {

    ElectricLog getById(@Param("id") Long id);

    List<ElectricLog> getListBySearch(ElectricLog search);

    Integer deleteById(@Param("id") Long id);

    Integer insert(ElectricLog log);

    Integer getCountBySearch(ElectricLog log);

    Integer updateTypeById(@Param("id") Long id, @Param("type") Long type);



}
