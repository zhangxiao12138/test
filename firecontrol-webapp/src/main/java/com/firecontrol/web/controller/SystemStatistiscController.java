package com.firecontrol.web.controller;

import com.firecontrol.common.OpResult;
import com.firecontrol.service.PatrolService;
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
 * Created by mariry on 2020/1/8.
 */


@Api(description = "管理员系统参数设置接口")
@Controller
@RequestMapping(value = "/sys")
public class SystemStatistiscController {

    @Autowired
    private PatrolService patrolService;

    @ApiOperation(value = "设置巡检项总数量接口" ,  notes="设置巡检项总数量接口")
    @RequestMapping(value = "/checkItemAmount", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name="vendorId",value="公司id",paramType = "query"),
            @ApiImplicitParam(name="userId",value="userId",paramType = "query"),
            @ApiImplicitParam(name="token",value="token",paramType = "query"),
            @ApiImplicitParam(name="checkItemId",value="检查项id",paramType = "query"),
            @ApiImplicitParam(name="amount",value="检查项数量",paramType = "query"),
    })
    @ResponseBody
    public OpResult checkItemAmount(Long vendorId, Long userId, Long checkItemId, Integer amount, String token){
        //TODO:AOP校验token有效性
        //暂时调用service方法
        if(patrolService.isSystemManger(userId, token)){
            return patrolService.setCheckItemAmount(vendorId, userId, checkItemId, amount);
        }else{
            OpResult op = new OpResult(OpResult.OP_FAILED, "无权限!");
            return op;
        }
    }










}
