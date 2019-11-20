package com.firecontrol.web.controller;

import com.alibaba.fastjson.JSON;
import com.firecontrol.common.OpResult;
import com.firecontrol.domain.dto.SCU200Dto;
import com.firecontrol.domain.dto.SMR3002Dto;
import com.firecontrol.service.TpsonSMR3002Service;
import com.firecontrol.service.impl.TpsonSCU200ServiceImpl;
import com.firecontrol.service.impl.TpsonSMR300ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by mariry on 2019/8/27.
 */
@Controller
@RequestMapping(value = "/tpson")
public class TpsonPushController {

    private static final Logger log = LoggerFactory.getLogger(TpsonPushController.class);

    @Autowired
    private TpsonSCU200ServiceImpl tpsonSCU200Service;

    @Autowired
    private TpsonSMR300ServiceImpl tpsonSMR3002Service;

    /**
     * SCU200 烟温一体机报警数据接收
     * @param dto
     * @return
     */
    @RequestMapping(value = "/open/device/log/scu200")
    @ResponseBody
    public OpResult receiveSCU200Info(@RequestBody SCU200Dto dto){
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);

        log.info("scu200dto: " + JSON.toJSONString(dto));

        return tpsonSCU200Service.saveSCU200Data(dto);
    }

    /**
     * SMR 1410 烟感中级主机 中继数据 与
     * 0故障，1正常，2报警报警数据
     * 中继数据： dataType = DEVICE_DATA
     * 报警数据： dataType = SENSOR_DATA
     */
    @RequestMapping(value = "/open/device/log/smr1410")
    @ResponseBody
    public OpResult receiveSMR1410Info() {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);

        return op;
    }


    /**
     * SMR 3002 电气识别系统
     */
    @RequestMapping(value = "/open/device/log/smr3002")
    @ResponseBody
    public OpResult receiveSMR3002Info(@RequestBody SMR3002Dto dto) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        op = tpsonSMR3002Service.saveSMR3002Data(dto);


        return op;
    }



}
