package com.firecontrol.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.firecontrol.common.OpResult;
import com.firecontrol.domain.entity.ElectricType;
import com.firecontrol.mapper.iotmapper.ElectricTypeMapper;
import com.firecontrol.service.ElectricTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mariry on 2019/11/26.
 */
@Service
public class ElectricTypeServiceImpl implements ElectricTypeService{

    private static final Logger log = LoggerFactory.getLogger(ElectricTypeServiceImpl.class);
    @Autowired
    private ElectricTypeMapper electricTypeMapper;

    @Override
    public OpResult addType(String name, Integer level) {

        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        if(StringUtils.isEmpty(name)) {
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage("新类型名称不可为空！");
            return op;
        }

        try{
            if(level == null) level = 2;
            ElectricType type = new ElectricType(name, level);
            electricTypeMapper.insert(type);

            type = electricTypeMapper.getByFullName(name);
            op.setDataValue(type);


        }catch (Exception e) {
            log.error("addType ERROR! e={}", e);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            op.setStatus(OpResult.OP_FAILED);
            return op;
        }

        return op;
    }

    @Override
    public OpResult listDeviceName(String name) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        List<ElectricType> list = null;
        try{
            list = electricTypeMapper.getByName(name);
            op.setDataValue(list);

        }catch (Exception e) {
            log.error("addType ERROR! e={}", e);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            op.setStatus(OpResult.OP_FAILED);
            return op;
        }

        return op;
    }
}
