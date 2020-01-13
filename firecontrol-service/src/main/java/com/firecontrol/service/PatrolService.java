package com.firecontrol.service;

import com.firecontrol.common.OpResult;

/**
 * Created by mariry on 2020/1/6.
 */
public interface PatrolService {

    OpResult newTask(Long vendorId, Long userId, String description, Long floorId, String floorName);

    Boolean hasAuthority(Long userId, String token);

    Boolean isSystemManger(Long userId, String token);

    OpResult getTopNode(Long vendorId, Long userId, String token);

    OpResult getListByParentNodeId(Long vendorId, Long parentNodeId);

    OpResult getCheckItemsByNodeId(Long vendorId, Long nodeId);

    OpResult finishTask(Long vendorId, Long userId, Long taskId);

    OpResult taskList(Long vendorId, Long userId);

    OpResult setCheckItemAmount(Long vendorId, Long userId, Long checkItemId, Integer amount, Long floorId);

    OpResult addTaskDetail(Long vendorId, Long userId, String userName, Long taskId, Long checkItemId, String checkItemName,
                           String description, Integer totalCount, Integer deviceCount, Integer state);

    OpResult taskDetailList(Long vendorId, Long userId, Long taskId);

    OpResult updateTaskDetail(Long vendorId, Long userId,Long taskDetailId, String description, Integer deviceCount, Integer state);

    OpResult getTaskDetailById(Long vendorId, Long taskDetailId);

}
