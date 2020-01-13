package com.firecontrol.service.impl;

import com.firecontrol.common.OpResult;
import com.firecontrol.domain.entity.*;
import com.firecontrol.mapper.iotmapper.*;
import com.firecontrol.service.PatrolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mariry on 2020/1/6.
 */
@Service
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

    @Autowired
    private TaskDetailMapper taskDetailMapper;


    @Override
    public OpResult newTask(Long vendorId, Long userId, String description, Long floorId, String floorName) {
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
            task.setFloorId(floorId);
            task.setFloorName(floorName);
            patrolTaskMapper.insert(task);
            rtnMap.put("taskId", task.getId());

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
    public Boolean isSystemManger(Long userId, String token) {

        Boolean rtn = true;

        UserEntity userEntity = userEntityMapper.getById(userId);

        if(userEntity == null) {
            log.error("hasAuthority error, userId: " + userId);
            return false;
        }
        if(!token.equals(userEntity.getToken())) {
            log.error("token失效! 入参token: " + token + ", normal token： " + userEntity.getToken());
            return false;
        }else if(userEntity.getRoleId() == null ||userEntity.getRoleId() == 0){
            return false;
        }else{
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
//TODO:条件查询
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

    @Override
    public OpResult addTaskDetail(Long vendorId, Long userId, String userName, Long taskId,
                                  Long checkItemId, String checkItemName, String description,
                                  Integer totalCount, Integer deviceCount, Integer state) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        Map rtnMap = new HashMap();

        if(vendorId == null || userId == null || taskId == null) {
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage("vendorId or userId or taskId can not be null!");
            return op;
        }

        try{

            //检查是否已存在
            Long taskDetailId = taskDetailMapper.getDetailId(vendorId, taskId, checkItemId);
            if(taskDetailId != null) {
                //已存在，更新
                taskDetailMapper.updateTaskDetail(vendorId, taskDetailId, description, deviceCount, state);
            }else{
                TaskDetail detail = new TaskDetail();
                detail.setCompanyFk(vendorId);
                detail.setCheckItemId(checkItemId + "");
                detail.setCheckItemName(checkItemName);
                detail.setDescription(description);
                detail.setCreateDate((int) (System.currentTimeMillis() / 1000));
                detail.setDeviceCount(deviceCount);
                //TODO: total需要根据floor和checkItem获取
                detail.setTotalCount(totalCount);
                detail.setUserFk(userId);
                detail.setUserName(userName);
                detail.setTaskId(taskId);
                detail.setState(state);
                taskDetailMapper.insert(detail);
            }

        }catch (Exception e){
            log.error("PatrolServiceImpl.addTaskDetail error! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }

        op.setDataValue(rtnMap);
        return op;
    }

    @Override
    public OpResult taskDetailList(Long vendorId, Long userId, Long taskId) {

        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        Map rtnMap = new HashMap();

        if(vendorId == null || userId == null || taskId == null) {
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage("vendorId or userId or taskId can not be null!");
            return op;
        }

        try{
            List<TaskDetail> list = taskDetailMapper.getDetailByTaskId(vendorId, taskId);
            rtnMap.put("taskDetail", list);


        }catch (Exception e){
            log.error("PatrolServiceImpl.addTaskDetail error! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }

        op.setDataValue(rtnMap);
        return op;
    }



    @Override
    public OpResult updateTaskDetail(Long vendorId, Long userId, Long taskDetailId, String description, Integer deviceCount, Integer state) {

        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        Map rtnMap = new HashMap();

        if(vendorId == null || userId == null || taskDetailId == null) {
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage("vendorId or userId or taskDetail can not be null!");
            return op;
        }
        try{

            taskDetailMapper.updateTaskDetail(vendorId, taskDetailId, description, deviceCount, state);

        }catch (Exception e){
            log.error("PatrolServiceImpl.updateTaskDetail error! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }

        op.setDataValue(rtnMap);
        return op;
    }

    @Override
    public OpResult getTaskDetailById(Long vendorId, Long taskDetailId) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        Map rtnMap = new HashMap();

        if(vendorId == null || taskDetailId == null) {
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage("vendorId or taskDetail can not be null!");
            return op;
        }
        try{
            TaskDetail td = taskDetailMapper.getById(taskDetailId);
            rtnMap.put("detail", td);
        }catch (Exception e){
            log.error("PatrolServiceImpl.getTaskDetailById error! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }

        op.setDataValue(rtnMap);
        return op;
    }


    @Override
    public OpResult setCheckItemAmount(Long vendorId, Long userId, Long checkItemId, Integer amount, Long floorId) {

        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);

        if(vendorId == null || userId == null || checkItemId == null) {
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage("vendorId or userId or checkItemId can not be null!");
            return op;
        }
        try{
            if(amount != null) {
                checkItemMapper.updateItemAmount(vendorId, userId, checkItemId, amount);


            }
        }catch (Exception e) {
            log.error("PatrolServiceImpl.setCheckItemAmount error! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }


        return op;
    }

}
