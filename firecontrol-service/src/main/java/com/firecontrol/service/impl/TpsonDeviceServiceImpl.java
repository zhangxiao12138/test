package com.firecontrol.service.impl;

import com.firecontrol.common.OpResult;
import com.firecontrol.domain.entity.TpsonDeviceEntity;
import com.firecontrol.service.TpsonDeviceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mariry on 2019/9/30.
 */
@Service
public class TpsonDeviceServiceImpl implements TpsonDeviceService {

    @Transactional
    @Override
    public OpResult insertDevice(TpsonDeviceEntity device) {
        OpResult opResult = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);

        //TODO:事务 同步完成传感器和设备的添加

        return opResult;
    }

    @Override
    public OpResult getDeviceByPage() {
        OpResult opResult = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        return opResult;
    }

    @Override
    public OpResult getDeviceById() {
        OpResult opResult = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        return opResult;
    }


}
