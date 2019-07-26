package com.firecontrol.service.impl;

import com.firecontrol.domain.entity.OpticalFibreTempEntity;
import com.firecontrol.mapper.OpticalFibreTempMapper;
import com.firecontrol.service.OpticalFibreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mariry on 2019/7/18.
 */
@Service
public class OpticalFibreServiceImpl implements OpticalFibreService {
    @Autowired
    private OpticalFibreTempMapper opticalFibreTempMapper;

    @Override
    public List<OpticalFibreTempEntity> getData(Integer channel) {

        List<OpticalFibreTempEntity> dataList = opticalFibreTempMapper.getData();


        return dataList;

    }

}
