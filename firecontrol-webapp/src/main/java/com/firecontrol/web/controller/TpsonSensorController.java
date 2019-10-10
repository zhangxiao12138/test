package com.firecontrol.web.controller;

import com.firecontrol.common.OpResult;
import com.firecontrol.domain.dto.SensorSearch;
import com.firecontrol.domain.entity.TpsonSensorEntity;
import com.firecontrol.service.TpsonSensorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by mariry on 2019/10/8.
 */
@Api(description = "设备传感器接口")
@Controller
@RequestMapping(value = "/tainbo/sensor")
public class TpsonSensorController {

    @Autowired
    private TpsonSensorService tpsonSensorService;

    @ApiOperation(value = "查询传感器(根据设备code)" ,  notes="查询传感器(根据设备code)")
    @RequestMapping(value = "/sensorList", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceCode", value = "设备code", paramType = "query", required = true),
            @ApiImplicitParam(name = "deviceType", value = "设备类型", paramType = "query"),
            @ApiImplicitParam(name = "faultStatus", value = "故障状态，1正常，2故障'", paramType = "query"),
    })
    @ResponseBody
    public OpResult getSensorBySearch(SensorSearch search){
        return tpsonSensorService.getSensorListBySearch(search);
    }

    @ApiOperation(value = "获取传感器信息(by id)" ,  notes="获取传感器信息(by id)")
    @RequestMapping(value = "/getSensorInfoById", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "设备id", paramType = "query", required = true)
    })
    @ResponseBody
    public OpResult getSensorInfoById(Long id){
        return tpsonSensorService.getSensorInfoById(id);
    }


    @ApiOperation(value = "启用(0)禁用(1)传感器" ,  notes="启用(0)禁用(1)传感器")
    @RequestMapping(value = "/changeState", method = {RequestMethod.POST})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "设备id", paramType = "query", required = true),
            @ApiImplicitParam(name = "status", value = "传感器状态", paramType = "query", required = true)
    })
    @ResponseBody
    public OpResult changeState(Long id, Integer status){

        return tpsonSensorService.changeState(id, status);
    }

    @ApiOperation(value = "更新传感器" ,  notes="更新传感器")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    @ResponseBody
    public OpResult udpate(TpsonSensorEntity sensor){
        return tpsonSensorService.updateSensorInfo(sensor);
    }








}
