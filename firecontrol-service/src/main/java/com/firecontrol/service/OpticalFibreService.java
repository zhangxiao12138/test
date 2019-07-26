package com.firecontrol.service;

import com.firecontrol.domain.entity.OpticalFibreTempEntity;

import java.util.List;

/**
 * Created by mariry on 2019/7/18.
 */
public interface OpticalFibreService {

    List<OpticalFibreTempEntity> getData(Integer channel);

}
