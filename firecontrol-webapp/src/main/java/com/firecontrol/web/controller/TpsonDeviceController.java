package com.firecontrol.web.controller;

import com.firecontrol.common.OpResult;
import com.firecontrol.domain.entity.TpsonDeviceEntity;
import com.firecontrol.service.TpsonDeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by mariry on 2019/9/30.
 */
@Api(description = "设备（拓深）接口")
@Controller
@RequestMapping(value = "/tainbo/device")
public class TpsonDeviceController {
    @Autowired
    private TpsonDeviceService tpsonDeviceService;


    @ApiOperation(value = "新增设备" ,  notes="新增设备")
    @RequestMapping(value = "/newDevice", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public OpResult insert(TpsonDeviceEntity device){
        return tpsonDeviceService.insertDevice(device);
    }

}
