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
 * Created by mariry on 2020/1/2.
 */
@Api(description = "巡查接口")
@Controller
@RequestMapping(value = "/patrol")
public class PatrolController {

    @Autowired
    private PatrolService patrolService;


    @ApiOperation(value = "新增巡查任务" ,  notes="新增巡查任务")
    @RequestMapping(value = "/newTask", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name="vendorId",value="公司id",paramType = "query"),
            @ApiImplicitParam(name="userId",value="userId",paramType = "query"),
            @ApiImplicitParam(name="token",value="token",paramType = "query"),
            @ApiImplicitParam(name="description",value="本次任务描述",paramType = "query"),
    })
    @ResponseBody
    public OpResult newTask(Long vendorId, Long userId, String description, String token){
        //TODO:AOP校验token有效性
        //暂时调用service方法

        if(patrolService.hasAuthority(userId, token)){
            return patrolService.newTask(vendorId, userId, description);
        }else{
            OpResult op = new OpResult(OpResult.OP_LOGIN_EXP, "请登录!");
            return op;
        }

    }


    @ApiOperation(value = "结束巡查任务" ,  notes="结束巡查任务")
    @RequestMapping(value = "/finishTask", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name="vendorId",value="公司id",paramType = "query"),
            @ApiImplicitParam(name="userId",value="userId",paramType = "query"),
            @ApiImplicitParam(name="token",value="token",paramType = "query"),
            @ApiImplicitParam(name="taskId",value="本次任务id",paramType = "query"),
    })
    @ResponseBody
    public OpResult finishTask(Long vendorId, Long userId, Long taskId, String token){
        //TODO:AOP校验token有效性
        //暂时调用service方法

        if(patrolService.hasAuthority(userId, token)){
            return patrolService.finishTask(vendorId, userId, taskId);
        }else{
            OpResult op = new OpResult(OpResult.OP_LOGIN_EXP, "请登录!");
            return op;
        }

    }


    @ApiOperation(value = "获取所有巡查任务列表" ,  notes="获取所有巡查任务列表")
    @RequestMapping(value = "/taskList", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name="vendorId",value="公司id",paramType = "query"),
            @ApiImplicitParam(name="userId",value="userId",paramType = "query"),
            @ApiImplicitParam(name="token",value="token",paramType = "query"),
    })
    @ResponseBody
    public OpResult taskList(Long vendorId, Long userId, String token){
        //TODO:AOP校验token有效性
        //暂时调用service方法

        if(patrolService.hasAuthority(userId, token)){
            return patrolService.taskList(vendorId, userId);
        }else{
            OpResult op = new OpResult(OpResult.OP_LOGIN_EXP, "请登录!");
            return op;
        }

    }



    @ApiOperation(value = "获取巡查一级节点" ,  notes="获取巡查一级节点")
    @RequestMapping(value = "/topNode", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name="vendorId",value="公司id",paramType = "query"),
            @ApiImplicitParam(name="userId",value="userId",paramType = "query"),
            @ApiImplicitParam(name="token",value="token",paramType = "query"),
    })
    @ResponseBody
    public OpResult topNode(Long vendorId, Long userId, String token){
        //TODO:AOP校验token有效性
        //暂时调用service方法

        if(patrolService.hasAuthority(userId, token)){
            return patrolService.getTopNode(vendorId, userId, token);
        }else{
            OpResult op = new OpResult(OpResult.OP_LOGIN_EXP, "请登录!");
            return op;
        }

    }


    @ApiOperation(value = "根据父节点查询子节点list" ,  notes="根据父节点查询子节点list")
    @RequestMapping(value = "/childreNodes", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name="vendorId",value="公司id",paramType = "query"),
            @ApiImplicitParam(name="parentNodeId",value="parentNodeId",paramType = "query"),
            @ApiImplicitParam(name="userId",value="userId",paramType = "query"),
            @ApiImplicitParam(name="token",value="token",paramType = "query"),
    })
    @ResponseBody
    public OpResult getListByParentNodeId(Long vendorId, Long parentNodeId, Long userId, String token){
        //TODO:AOP校验token有效性
        //暂时调用service方法

        if(patrolService.hasAuthority(userId, token)){
            return patrolService.getListByParentNodeId(vendorId,parentNodeId);
        }else{
            OpResult op = new OpResult(OpResult.OP_LOGIN_EXP, "请登录!");
            return op;
        }

    }

    @ApiOperation(value = "根据末级节点id获取检查项" ,  notes="根据末级节点获取检查项")
    @RequestMapping(value = "/checkItems", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name="vendorId",value="公司id",paramType = "query"),
            @ApiImplicitParam(name="leafNodeId",value="leafNodeId",paramType = "query"),
            @ApiImplicitParam(name="userId",value="userId",paramType = "query"),
            @ApiImplicitParam(name="token",value="token",paramType = "query"),
    })
    @ResponseBody
    public OpResult getCheckItemsByNodeId(Long vendorId, Long leafNodeId, Long userId, String token){
        //TODO:AOP校验token有效性
        //暂时调用service方法

        if(patrolService.hasAuthority(userId, token)){
            return patrolService.getCheckItemsByNodeId(vendorId,leafNodeId);
        }else{
            OpResult op = new OpResult(OpResult.OP_LOGIN_EXP, "请登录!");
            return op;
        }


    }




}
