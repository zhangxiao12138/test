package com.firecontrol.web.controller;

import com.firecontrol.common.OpResult;
import com.firecontrol.domain.dto.DeviceSearch;
import com.firecontrol.service.TpsonDeviceFaultService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by mariry on 2019/10/9.
 */
@Controller
@Api(description = "设备（拓深）故障接口")
@RequestMapping(value = "/tainbo/deviceFault")
public class TpsonDeviceFaultController {

    @Autowired
    private TpsonDeviceFaultService tpsonDeviceFaultService;

    @ApiOperation(value = "消防故障页-上方4中数据" ,  notes="消防故障页-上方4中数据")
    @RequestMapping(value = "/faultStatistics", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "systemType", value = "系统类型1消控联网2消防用水3用电安全5动态监测8燃气监测", paramType = "query", required = true),
            @ApiImplicitParam(name = "companyId", value = "", paramType = "query"),
    })
    @ResponseBody
    public OpResult getFaultStatistics(Integer systemType, Long companyId){
        return tpsonDeviceFaultService.getFaultStatistics(systemType, companyId);
    }

    @ApiOperation(value = "获取故障类型list" ,  notes="获取故障类型list")
    @RequestMapping(value = "/faultType", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "systemType", value = "系统类型1消控联网2消防用水3用电安全5动态监测8燃气监测", paramType = "query", required = true),
    })
    @ResponseBody
    public OpResult getDeviceFaultType(){
        return tpsonDeviceFaultService.getFaultType();
    }


    @ApiOperation(value = "获取故障趋势图数据list" ,  notes="获取故障趋势图数据list")
    @RequestMapping(value = "/faultTrendDiagram", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "systemType", value = "系统类型1消控联网2消防用水3用电安全5动态监测8燃气监测", paramType = "query", required = true),
            @ApiImplicitParam(name = "start", value = "开始时间", paramType = "query", required = true),
            @ApiImplicitParam(name = "end", value = "结束时间", paramType = "query"),
            @ApiImplicitParam(name = "companyId", value = "companyId", paramType = "query"),
    })
    @ResponseBody
    public OpResult getFaultTrendDiagram(Integer systemType, Integer companyId, Long start, Long end){
        return tpsonDeviceFaultService.getFaultTrendDiagram(systemType, companyId, start, end);
    }

    @ApiOperation(value = "获取故障list" ,  notes="获取故障list")
    @RequestMapping(value = "/faultList", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "systemType", value = "系统类型1消控联网2消防用水3用电安全5动态监测8燃气监测", paramType = "query", required = true),
            @ApiImplicitParam(name = "startTime", value = "开始时间", paramType = "query"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", paramType = "query"),
            @ApiImplicitParam(name = "faultType", value = "故障类型", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "处理状态0未处理1已处理", paramType = "query"),
            @ApiImplicitParam(name = "deviceCode", value = "设备编号", paramType = "query"),
            @ApiImplicitParam(name = "isOutdoor", value = "是否室外", paramType = "query"),
            @ApiImplicitParam(name="currentPage",paramType = "query",value="当前页 从1开始"),
            @ApiImplicitParam(name="length",paramType = "query",value="分页大小"),
    })
    @ResponseBody
    public OpResult getFaultList(Integer systemType, Integer faultType, Integer status, Integer isOutdoor, String deviceCode, Long startTime, Long endTime, Integer currentPage, Integer length){
        return tpsonDeviceFaultService.getFaultList(systemType, faultType, status, isOutdoor, deviceCode, startTime, endTime, currentPage, length);
    }


    @ApiOperation(value = "消防故障-故障详情页" ,  notes="消防故障-故障详情页")
    @RequestMapping(value = "/faultDetail", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "故障信息id", paramType = "query", required = true),
    })
    @ResponseBody
    public OpResult getFaultStatistics(Long id){
        return tpsonDeviceFaultService.getFaultDetailById(id);
    }

    @ApiOperation(value = "消防故障-更新处理结果" ,  notes="消防故障-更新处理结果")
    @RequestMapping(value = "/updatefaultDetail", method = {RequestMethod.POST})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "故障信息id", paramType = "query", required = true),
            @ApiImplicitParam(name = "dealDetail", value = "故障处理意见", paramType = "query", required = true),
    })
    @ResponseBody
    public OpResult getFaultStatistics(Long id, String dealDetail){
        return tpsonDeviceFaultService.updateFaultDetailById(id, dealDetail);
    }


    @ApiOperation(value = "消防故障-批量删除By Ids" ,  notes="消防故障-批量删除By Ids")
    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "故障信息ids,多个id逗号分隔", paramType = "query", required = true),
    })
    @ResponseBody
    public OpResult delete(String ids){
        return tpsonDeviceFaultService.deleteHandledFaultByIds(ids);
    }


    @ApiOperation(value = "消防故障-批量处理" ,  notes="消防故障-批量处理 Ids")
    @RequestMapping(value = "/batchDeal", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "故障信息ids,多个id逗号分隔", paramType = "query", required = true),
            @ApiImplicitParam(name = "dealDetail", value = "处理意见", paramType = "query", required = true),
    })
    @ResponseBody
    public OpResult batchDeal(String ids, String dealDetail){
        return tpsonDeviceFaultService.batchDeal(ids, dealDetail);
    }







}
