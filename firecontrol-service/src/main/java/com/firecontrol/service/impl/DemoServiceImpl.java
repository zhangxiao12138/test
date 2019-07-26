package com.firecontrol.service.impl;

import com.firecontrol.common.FireSocket;
import com.firecontrol.common.FireSocketService;
import com.firecontrol.domain.entity.DemoEntity;
import com.firecontrol.mapper.DemoMapper;
import com.firecontrol.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mariry on 2019/6/21.
 */
@Service
public class DemoServiceImpl implements DemoService {
    private static final Logger log = LoggerFactory.getLogger(DemoServiceImpl.class);

    @Autowired
    private DemoMapper demoMapper;
    @Autowired
    private FireSocketService fireSocketService;

    @Override
    public List<DemoEntity> getAllDemoEntity() {

        return demoMapper.getAll();
    }

    @Override
    public String setDirection(String d) {
        FireSocket fs = new FireSocket(1, 1, d);
        try{
            fireSocketService.send(fs);
        }catch (Exception e) {
            log.info("socket sent exception! e: " + e);
            return "failure";
        }
        return "success";
    }


}
