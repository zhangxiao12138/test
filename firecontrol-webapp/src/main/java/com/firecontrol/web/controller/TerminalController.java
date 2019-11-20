package com.firecontrol.web.controller;

import com.firecontrol.common.OpResult;
import com.firecontrol.domain.entity.CameraEntity;
import com.firecontrol.domain.entity.TerminalEntity;
import com.firecontrol.service.TerminalService;
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
 * Created by mariry on 2019/8/15.
 */
@Api(description = "通用终端接口")
@Controller
@RequestMapping(value = "/tainbo/terminal")
public class TerminalController {
    @Autowired
    private TerminalService terminalService;

    @ApiOperation(value = "新增终端" ,  notes="新增终端")
    @RequestMapping(value = "/newTerminal" , method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public OpResult insert(TerminalEntity terminal){
        return terminalService.insertTerminal(terminal);
    }


    @ApiOperation(value = "查询终端列表(分页)" ,  notes="查询终端列表(分页)")
    @RequestMapping(value = "/terminalList", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "状态0:未激活 1:已激活", paramType = "query"),
            @ApiImplicitParam(name = "currentPage", value = "当前页,从1开始", paramType = "query"),
            @ApiImplicitParam(name = "length", value = "分页大小", paramType = "query"),
    })
    @ResponseBody
    public OpResult termenalList(TerminalEntity terminal){
        return terminalService.getTerminalList(terminal);
    }

    @ApiOperation(value = "查询终端id list" ,  notes="查询终端id list")
    @RequestMapping(value = "/terminalIdList", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "状态0:未激活 1:已激活", paramType = "query"),
    })
    @ResponseBody
    public OpResult termenalIdList(Integer status){
        return terminalService.getTerminalIds(status);
    }


    @ApiOperation(value = "更新终端的hardwareId" ,  notes="更新终端的hardwareId")
    @RequestMapping(value = "/updateHardward", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "terminalId", value = "终端id", paramType = "query"),
            @ApiImplicitParam(name = "hardwardId", value = "硬件id", paramType = "query"),
    })
    @ResponseBody
    public OpResult terminalHardward(String terminalId, String hardwardId){
        return terminalService.updateTerminalHardward(terminalId, hardwardId);
    }

}
