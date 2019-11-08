package com.firecontrol.web.controller;

import com.firecontrol.common.OpResult;
import com.firecontrol.service.FloorService;
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
 * Created by mariry on 2019/11/8.
 */
@Api(description = "楼层接口")
@Controller
@RequestMapping(value = "/tainbo/floor")
public class FloorController {
    @Autowired
    private FloorService floorService;


    @ApiOperation(value = "测试多数据源" ,  notes="测试多数据源")
    @RequestMapping(value = "/testMultiDataSource",method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", paramType = "query", required = true),
    })
    @ResponseBody
    public OpResult testMultiDataSource(String id){

        return floorService.getFloorById(id);

    }
}
