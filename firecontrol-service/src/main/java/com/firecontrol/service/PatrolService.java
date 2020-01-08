package com.firecontrol.service;

import com.firecontrol.common.OpResult;

/**
 * Created by mariry on 2020/1/6.
 */
public interface PatrolService {

    OpResult newTask(Long vendorId, Long userId, String description);

    Boolean hasAuthority(Long userId, String token);

    Boolean isSystemManger(Long userId, String token);

    OpResult getTopNode(Long vendorId, Long userId, String token);

    OpResult getListByParentNodeId(Long vendorId, Long parentNodeId);

    OpResult getCheckItemsByNodeId(Long vendorId, Long nodeId);

    OpResult finishTask(Long vendorId, Long userId, Long taskId);

    OpResult taskList(Long vendorId, Long userId);

    OpResult setCheckItemAmount(Long vendorId, Long userId, Long checkItemId, Integer amount);





}
