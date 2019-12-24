package com.firecontrol.mapper.iotmapper;

import com.firecontrol.domain.entity.ElectricEnergy;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

/**
 * Created by mariry on 2019/12/23.
 */
public interface ElectricEnergyMapper {

    ElectricEnergy getById(@Param("id") Long id);

    Integer deleteById(@Param("id") Long id);

    Integer insert(ElectricEnergy energy);

    Integer update(ElectricEnergy energy);

    Double getDailyEnergyConsumption(@Param("start") Long start, @Param("end") Long end,
                                     @Param("companyId")Long companyId, @Param("deviceCode") String deviceCode);


}
