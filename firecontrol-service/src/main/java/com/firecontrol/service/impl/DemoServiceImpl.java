package com.firecontrol.service.impl;

import com.firecontrol.domain.entity.DemoEntity;
import com.firecontrol.mapper.DemoMapper;
import com.firecontrol.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mariry on 2019/6/21.
 */
@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private DemoMapper demoMapper;

    @Override
    public List<DemoEntity> getAllDemoEntity() {

        return demoMapper.getAll();
    }



}
