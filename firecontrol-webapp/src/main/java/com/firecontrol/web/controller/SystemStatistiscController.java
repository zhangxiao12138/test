package com.firecontrol.web.controller;

import com.firecontrol.common.OpResult;
import com.firecontrol.service.BuildingFloorService;
import com.firecontrol.service.PatrolService;
import com.firecontrol.service.UserLoginService;
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
    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private BuildingFloorService buildingFloorService;

    @ApiOperation(value = "设置巡检项总数量接口" ,  notes="设置巡检项总数量接口")
    @RequestMapping(value = "/checkItemAmount", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name="vendorId",value="公司id",paramType = "query"),
            @ApiImplicitParam(name="userId",value="userId",paramType = "query"),
            @ApiImplicitParam(name="token",value="token",paramType = "query"),
            @ApiImplicitParam(name="checkItemId",value="检查项id",paramType = "query"),
            @ApiImplicitParam(name="amount",value="检查项数量",paramType = "query"),
            @ApiImplicitParam(name="floorId",value="建筑物id",paramType = "query"),
    })
    @ResponseBody
    public OpResult checkItemAmount(Long vendorId, Long userId, Long checkItemId, Integer amount, String token, Long floorId){
        //TODO:AOP校验token有效性
        //暂时调用service方法
        if(patrolService.isSystemManger(userId, token)){
            return patrolService.setCheckItemAmount(vendorId, userId, checkItemId, amount, floorId);
        }else{
            OpResult op = new OpResult(OpResult.OP_FAILED, "无权限!");
            return op;
        }
    }


    @ApiOperation(value = "管理员新增用户" ,  notes="管理员新增用户")
    @RequestMapping(value = "/newUser", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name="vendorId",value="公司id",paramType = "query"),
            @ApiImplicitParam(name="userId",value="管理员id",paramType = "query"),
            @ApiImplicitParam(name="token",value="管理员token",paramType = "query"),
            @ApiImplicitParam(name="userName",value="新用户的用户名(仅支持数字和英文字母)",paramType = "query"),
            @ApiImplicitParam(name="password",value="新用户的密码(32位md5加密后字符串，小写)",paramType = "query"),
    })
    @ResponseBody
    public OpResult newUser(Long vendorId, Long userId, String userName, String password, String token){
        //TODO:AOP校验token有效性
        //暂时调用service方法
        if(patrolService.isSystemManger(userId, token)){
            return userLoginService.newUser(vendorId, userName, password);
        }else{
            OpResult op = new OpResult(OpResult.OP_FAILED, "无权限!");
            return op;
        }
    }

    @ApiOperation(value = "管理员新增建筑物" ,  notes="管理员新增建筑物")
    @RequestMapping(value = "/newArch", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name="vendorId",value="公司id",paramType = "query"),
            @ApiImplicitParam(name="userId",value="管理员id",paramType = "query"),
            @ApiImplicitParam(name="token",value="管理员token",paramType = "query"),
            @ApiImplicitParam(name="name",value="建筑物名称",paramType = "query"),
    })
    @ResponseBody
    public OpResult newArch(Long vendorId, Long userId, String name, String token){
        //TODO:AOP校验token有效性
        //暂时调用service方法
        if(patrolService.isSystemManger(userId, token)){
            return buildingFloorService.addFloor(vendorId, name);
        }else{
            OpResult op = new OpResult(OpResult.OP_FAILED, "无权限!");
            return op;
        }
    }

    @ApiOperation(value = "管理员查看本公司巡检员列表" ,  notes="管理员查看本公司巡检员列表")
    @RequestMapping(value = "/userList", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name="vendorId",value="公司id",paramType = "query"),
            @ApiImplicitParam(name="userId",value="管理员id",paramType = "query"),
            @ApiImplicitParam(name="token",value="管理员token",paramType = "query"),
            @ApiImplicitParam(name="roleId",value="角色 0：巡检员 1：管理员",paramType = "query"),
    })
    @ResponseBody
    public OpResult userList(Long vendorId, Long userId, Long roleId, String token){
        //TODO:AOP校验token有效性
        //暂时调用service方法
        if(patrolService.isSystemManger(userId, token)){
            return userLoginService.getUserList(vendorId, roleId);
        }else{
            OpResult op = new OpResult(OpResult.OP_FAILED, "无权限!");
            return op;
        }
    }


    @ApiOperation(value = "管理员查看本公司巡检员列表" ,  notes="管理员查看本公司巡检员列表")
    @RequestMapping(value = "/resetPw", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name="vendorId",value="公司id",paramType = "query"),
            @ApiImplicitParam(name="userId",value="管理员id",paramType = "query"),
            @ApiImplicitParam(name="token",value="管理员token",paramType = "query"),
            @ApiImplicitParam(name="targetUserId",value="需更改密码的用户的id",paramType = "query"),
            @ApiImplicitParam(name="targetPw",value="新密码（32位md5加密后）",paramType = "query"),
    })
    @ResponseBody
    public OpResult resetPw(Long vendorId, Long userId, String token, Long targetUserId, String targetPw){
        //TODO:AOP校验token有效性
        //暂时调用service方法
        if(patrolService.isSystemManger(userId, token)){
            return userLoginService.resetPw(vendorId, targetUserId, targetPw);
        }else{
            OpResult op = new OpResult(OpResult.OP_FAILED, "无权限!");
            return op;
        }
    }







}
