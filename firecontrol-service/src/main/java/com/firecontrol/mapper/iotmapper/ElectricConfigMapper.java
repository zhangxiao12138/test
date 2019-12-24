package com.firecontrol.mapper.iotmapper;

import com.firecontrol.domain.entity.ElectricConfig;
import org.apache.ibatis.annotations.Param;

/**
 * Created by mariry on 2019/12/24.
 */
public interface ElectricConfigMapper {

    Integer insert(ElectricConfig config);

    ElectricConfig getById(@Param("id") Long id);

    Integer deleteById(@Param("id") Long id);

    Integer update(ElectricConfig config);

    ElectricConfig getByCompanyCode(@Param("companyCode") String companyCode);


}
