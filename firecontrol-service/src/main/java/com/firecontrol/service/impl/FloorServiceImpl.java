package com.firecontrol.service.impl;

import com.firecontrol.common.OpResult;
import com.firecontrol.domain.entity.Floor;
import com.firecontrol.mapper.ydmapper.FloorMapper;
import com.firecontrol.service.FloorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mariry on 2019/11/8.
 */
@Service
public class FloorServiceImpl implements FloorService {
    private static final Logger log = LoggerFactory.getLogger(FloorService.class);

    @Autowired
    private FloorMapper ydFloorMapper;

    @Override
    public OpResult getFloorById(String id) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);

        try{
            Floor floor = ydFloorMapper.selectById(id);
            op.setDataValue(floor);

        }catch (Exception e){
            log.error("FloorServiceImpl.getFloorById ERROR! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }

        return op;
    }
}
