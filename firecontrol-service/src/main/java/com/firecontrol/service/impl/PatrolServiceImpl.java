package com.firecontrol.service.impl;

import com.firecontrol.common.OpResult;
import com.firecontrol.domain.entity.*;
import com.firecontrol.mapper.iotmapper.PatrolCheckItemMapper;
import com.firecontrol.mapper.iotmapper.PatrolDeviceTypeMapper;
import com.firecontrol.mapper.iotmapper.PatrolTaskMapper;
import com.firecontrol.mapper.iotmapper.UserEntityMapper;
import com.firecontrol.service.PatrolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mariry on 2020/1/6.
 */
public class PatrolServiceImpl implements PatrolService{

    private static final Logger log = LoggerFactory.getLogger(PatrolServiceImpl.class);

    @Autowired
    private UserEntityMapper userEntityMapper;

    @Autowired
    private PatrolTaskMapper patrolTaskMapper;

    @Autowired
    private PatrolCheckItemMapper checkItemMapper;

    @Autowired
    private PatrolDeviceTypeMapper deviceTypeMapper;


    @Override
    public OpResult newTask(Long vendorId, Long userId, String description) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        Map rtnMap = new HashMap();
        Long resultId = 0L;

        try{
            PatrolTask task = new PatrolTask();
            task.setCompanyFk(vendorId);
            task.setCreateDate((int) (System.currentTimeMillis() / 1000));
            task.setCreateUserFk(userId);
            task.setUserFk(userId);
            task.setIsStop(false);
            task.setDescription(description);

            resultId = patrolTaskMapper.insert(task);
            rtnMap.put("taskId", resultId);

        }catch (Exception e){
            log.error("PatrolServiceImpl.newTask error! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }

        op.setDataValue(rtnMap);
        return op;
    }



    @Override
    public Boolean hasAuthority(Long userId, String token) {
        Boolean rtn = true;

        UserEntity userEntity = userEntityMapper.getById(userId);

        if(userEntity == null) {
            log.error("hasAuthority error, userId: " + userId);
            return false;
        }
        if(!token.equals(userEntity.getToken())) {
            log.error("token失效! 入参token: " + token + ", normal token： " + userEntity.getToken());
            return false;
        }else {
            return true;
        }

    }

    @Override
    public OpResult getTopNode(Long vendorId, Long userId, String token) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        Map rtnMap = new HashMap();
        Long resultId = 0L;

        try{
            List<PatrolDeviceType> list = deviceTypeMapper.getTopNode(vendorId);
            rtnMap.put("deviceTypeList", list);

        }catch (Exception e){
            log.error("PatrolServiceImpl.getTopNode error! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }

        op.setDataValue(rtnMap);
        return op;
    }

    @Override
    public OpResult getListByParentNodeId(Long vendorId, Long parentNodeId) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        Map rtnMap = new HashMap();
        Long resultId = 0L;

        if(vendorId == null || parentNodeId == null) {
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage("vendorId or  parentNodeId can not be null!");
            return op;
        }

        try{
            List<PatrolDeviceType> list = deviceTypeMapper.getListByParentNodeId(vendorId, parentNodeId);
            rtnMap.put("deviceTypeList", list);

        }catch (Exception e){
            log.error("PatrolServiceImpl.getListByParentNodeId error! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }

        op.setDataValue(rtnMap);
        return op;
    }

    @Override
    public OpResult getCheckItemsByNodeId(Long vendorId, Long nodeId) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        Map rtnMap = new HashMap();
        Long resultId = 0L;

        if(vendorId == null || nodeId == null) {
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage("vendorId or  nodeId can not be null!");
            return op;
        }

        try{
            List<PatrolCheckItem> list = checkItemMapper.getCheckItemsByNodeId(vendorId, nodeId);
            rtnMap.put("checkItemList", list);

        }catch (Exception e){
            log.error("PatrolServiceImpl.getCheckItemsByNodeId error! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }

        op.setDataValue(rtnMap);
        return op;
    }

    @Override
    public OpResult finishTask(Long vendorId, Long userId, Long taskId) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        Map rtnMap = new HashMap();
        Long resultId = 0L;

        if(vendorId == null || taskId == null) {
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage("vendorId or taskId can not be null!");
            return op;
        }

        try{
            PatrolTask task = patrolTaskMapper.getById(taskId);
            if(task == null) {
                op.setStatus(OpResult.OP_FAILED);
                op.setMessage("未找到对应任务，请确认taskId!");
                return op;
            }
            //TODO: 计算task完成率等等操作


            //更新task状态
            patrolTaskMapper.updateTaskStop(taskId);

        }catch (Exception e){
            log.error("PatrolServiceImpl.finishTask error! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }

        op.setDataValue(rtnMap);
        return op;
    }

    @Override
    public OpResult taskList(Long vendorId, Long userId) {

        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        Map rtnMap = new HashMap();
        Long resultId = 0L;

        if(vendorId == null || userId == null) {
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage("vendorId or userId can not be null!");
            return op;
        }

        try{
            List<PatrolTask> taskList = patrolTaskMapper.getTaskListByVendorId(vendorId);
            rtnMap.put("taskList", taskList);
            op.setDataValue(rtnMap);

        }catch (Exception e){
            log.error("PatrolServiceImpl.finishTask error! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }

        op.setDataValue(rtnMap);
        return op;
    }


}
