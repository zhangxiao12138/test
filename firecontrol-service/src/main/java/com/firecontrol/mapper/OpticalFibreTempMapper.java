package com.firecontrol.mapper;

import com.firecontrol.domain.entity.OpticalFibreTempEntity;

import java.util.List;

/**
 * Created by mariry on 2019/7/18.
 */
public interface OpticalFibreTempMapper {

    public void insert(OpticalFibreTempEntity opticalFibreTemp);

    public List<OpticalFibreTempEntity> getData();




}
