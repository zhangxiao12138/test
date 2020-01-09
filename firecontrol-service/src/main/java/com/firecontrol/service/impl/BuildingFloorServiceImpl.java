package com.firecontrol.service.impl;

import com.firecontrol.common.OpResult;
import com.firecontrol.domain.entity.BuildingFloor;
import com.firecontrol.mapper.iotmapper.BuildingFloorMapper;
import com.firecontrol.mapper.iotmapper.UserEntityMapper;
import com.firecontrol.service.BuildingFloorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mariry on 2020/1/9.
 */
@Service
public class BuildingFloorServiceImpl implements BuildingFloorService {

    private static final Logger log = LoggerFactory.getLogger(BuildingFloorServiceImpl.class);

    @Autowired
    private UserEntityMapper userEntityMapper;
    @Autowired
    private BuildingFloorMapper buildingFloorMapper;

    @Override
    public OpResult addFloor(Long vendorId, String floorName) {

        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);

        try{
            //检查建筑是否重名
            Integer count = buildingFloorMapper.checkFloorName(vendorId, floorName);
            if(count > 0) {
                op.setStatus(OpResult.OP_FAILED);
                op.setMessage("已存在同名建筑物!");
                return op;
            }
            BuildingFloor bf = new BuildingFloor();
            bf.setCompanyId(vendorId);
            bf.setName(floorName);
            bf.setDf(0);
            buildingFloorMapper.insert(bf);

        }catch (Exception e) {
            log.error("BuildingFloorServiceImpl.addFloor: e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }

        return op;
    }

    @Override
    public OpResult getFloorById(Long floorId, Long vendorId) {


        return null;

    }

    @Override
    public OpResult getFloorByVendor(Long vendorId) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        Map rtnMap = new HashMap();

        try{
            List<BuildingFloor> floorList = buildingFloorMapper.getFloorByCompanyId(vendorId);
            rtnMap.put("floorList", floorList);
            op.setDataValue(rtnMap);

        }catch (Exception e) {
            log.error("BuildingFloorServiceImpl.addFloor: e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }

        return op;
    }
}
