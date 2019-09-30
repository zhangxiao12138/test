package com.firecontrol.web.controller;

import com.firecontrol.common.OpResult;
import com.firecontrol.domain.dto.SCU200Dto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by mariry on 2019/8/27.
 */
@Controller
@RequestMapping(value = "/tpson")
public class TpsonPushController {

    /**
     * SCU200 烟温一体机报警数据接收
     * @param dto
     * @return
     */
    @RequestMapping(value = "/open/device/log/scu200")
    @ResponseBody
    public OpResult receiveSCU200Info(SCU200Dto dto){
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);

        return op;
    }

    /**
     * SMR 1410 烟感中级主机 中继数据 与
     * 0故障，1正常，2报警报警数据
     * 中继数据： dataType = DEVICE_DATA
     * 报警数据： dataType = SENSOR_DATA
     */
    @RequestMapping(value = "/tpson/open/device/log/smr1410")
    @ResponseBody
    public OpResult receiveSMR1410Info() {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);

        return op;
    }




}
