package com.firecontrol.web.controller;

import com.firecontrol.common.OpResult;
import com.firecontrol.service.TpsonAlarmService;
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
 * Created by mariry on 2019/10/11.
 */
@Api(description = "报警相关接口")
@Controller
@RequestMapping(value = "/tainbo/alarm")
public class TpsonAlarmController {

    @Autowired
    private TpsonAlarmService tpsonAlarmService;


    @ApiOperation(value = "消防报警-获取报警总数、未处理报警数、已处理报警数" ,  notes="消防报警-获取报警总数、未处理报警数、已处理报警数")
    @RequestMapping(value = "/alarmStatistics", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "systemType", value = "系统类型1消控联网2消防用水3用电安全5动态监测8燃气监测", paramType = "query", required = true),
            @ApiImplicitParam(name = "companyId", value = "", paramType = "query"),
    })
    @ResponseBody
    public OpResult getAlarmStatistics(Integer systemType, Long companyId){
        return tpsonAlarmService.getAlarmStatistics(systemType, companyId);
    }

    @ApiOperation(value = "根据获取报警类型list" ,  notes="根据获取报警类型list")
    @RequestMapping(value = "/alarmType", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "systemType", value = "系统类型1消控联网2消防用水3用电安全5动态监测8燃气监测", paramType = "query", required = true),
            @ApiImplicitParam(name = "deviceType", value = "设备类型", paramType = "query"),
    })
    @ResponseBody
    public OpResult getAlarmTypeBySystemType(Integer systemType, Long deviceType){
        return tpsonAlarmService.getAlarmTypeBySystemType(systemType, deviceType);
    }


    @ApiOperation(value = "获取报警趋势图数据list" ,  notes="获取报警趋势图数据list")
    @RequestMapping(value = "/alarmTrendDiagram", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "systemType", value = "系统类型1消控联网2消防用水3用电安全5动态监测8燃气监测", paramType = "query", required = true),
            @ApiImplicitParam(name = "start", value = "开始时间", paramType = "query", required = true),
            @ApiImplicitParam(name = "end", value = "结束时间", paramType = "query"),
            @ApiImplicitParam(name = "companyId", value = "companyId", paramType = "query"),
    })
    @ResponseBody
    public OpResult getFaultTrendDiagram(Integer systemType, Integer companyId, Long start, Long end){
        return tpsonAlarmService.getAlarmTrendDiagram(systemType, companyId, start, end);
    }


    @ApiOperation(value = "获取报警list" ,  notes="获取报警list")
    @RequestMapping(value = "/alarmList", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "systemType", value = "系统类型1消控联网2消防用水3用电安全5动态监测8燃气监测", paramType = "query", required = true),
            @ApiImplicitParam(name = "startTime", value = "开始时间", paramType = "query"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", paramType = "query"),
            @ApiImplicitParam(name = "alarmType", value = "故障类型", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "处理状态0未处理1已处理", paramType = "query"),
            @ApiImplicitParam(name = "deviceCode", value = "设备编号", paramType = "query"),
            @ApiImplicitParam(name = "isOutdoor", value = "是否室外", paramType = "query"),
            @ApiImplicitParam(name="currentPage",paramType = "query",value="当前页 从1开始"),
            @ApiImplicitParam(name="length",paramType = "query",value="分页大小"),
    })
    @ResponseBody
    public OpResult getAlarmList(Integer systemType, Integer alarmType, Integer status, Integer isOutdoor, String deviceCode, Long startTime, Long endTime, Integer currentPage, Integer length){
        return tpsonAlarmService.getAlarmList(systemType, alarmType, status, isOutdoor, deviceCode, startTime, endTime, currentPage, length);
    }


    @ApiOperation(value = "报警页-批量删除By Ids" ,  notes="报警页-批量删除By Ids")
    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "故障信息ids,多个id逗号分隔", paramType = "query", required = true),
    })
    @ResponseBody
    public OpResult delete(String ids){
        return tpsonAlarmService.deleteHandledAlarmByIds(ids);
    }


    @ApiOperation(value = "报警页-批量处理" ,  notes="报警页-批量处理 Ids")
    @RequestMapping(value = "/batchDeal", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "故障信息ids,多个id逗号分隔", paramType = "query", required = true),
            @ApiImplicitParam(name = "status", value = "处理状态1有效处理2误报3消防测试4维保检修5其他", paramType = "query", required = true),
            @ApiImplicitParam(name = "dealDetail", value = "处理意见", paramType = "query", required = true),
    })
    @ResponseBody
    public OpResult batchDeal(String ids, Integer status, String dealDetail){
        return tpsonAlarmService.batchHandle(ids, status, dealDetail);
    }


    @ApiOperation(value = "报警-单个处理" ,  notes="报警-单个处理")
    @RequestMapping(value = "/updateAlarmDetail", method = {RequestMethod.POST})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "报警信息id", paramType = "query", required = true),
            @ApiImplicitParam(name = "status", value = "报警处理状态", paramType = "query", required = true),
            @ApiImplicitParam(name = "dealDetail", value = "报警处理意见", paramType = "query", required = true),
    })
    @ResponseBody
    public OpResult updateAlarmDetailById(Long id, Integer status, String dealDetail){
        return tpsonAlarmService.updateAlarmDetailById(id, status, dealDetail);
    }



    @ApiOperation(value = "报警页-获取处理状态常量列表" ,  notes="报警页-获取处理状态常量列表")
    @RequestMapping(value = "/dealStatus", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public OpResult getDealStatus(){
        return tpsonAlarmService.getDealStatus();
    }


    @ApiOperation(value = "报警 - 获取报警详情By Id" ,  notes="报警 - 获取报警详情By Id")
    @RequestMapping(value = "/alarmDetail", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "报警信息id", paramType = "query", required = true),
    })
    @ResponseBody
    public OpResult getAlarmDetailById(Long id){
        return tpsonAlarmService.getAlarmDetailById(id);
    }


    @ApiOperation(value = "报警 - 获取处理类型统计数据" ,  notes="报警 - 获取处理类型统计数据")
    @RequestMapping(value = "/dealTypeCount", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "systemType", value = "系统类型1消控联网2消防用水3用电安全5动态监测8燃气监测", paramType = "query", required = true),
            @ApiImplicitParam(name = "startTime", value = "开始时间 10位时间戳", paramType = "query", required = true),
            @ApiImplicitParam(name = "endTime", value = "结束时间 10位时间戳", paramType = "query"),
            @ApiImplicitParam(name = "companyId", value = "公司id", paramType = "query"),
    })
    @ResponseBody
    public OpResult dealTypeCount(Integer systemType, Integer startTime, Integer endTime, String companyId){
        return tpsonAlarmService.dealTypeCount(systemType, startTime, endTime, companyId);
    }



    @ApiOperation(value = "报警 - 获取报警类型统计数据" ,  notes="报警 - 获取报警类型统计数据")
    @RequestMapping(value = "/alarmCountByType", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "systemType", value = "系统类型1消控联网2消防用水3用电安全5动态监测8燃气监测", paramType = "query", required = true),
            @ApiImplicitParam(name = "startTime", value = "开始时间 10位时间戳", paramType = "query", required = true),
            @ApiImplicitParam(name = "endTime", value = "结束时间 10位时间戳", paramType = "query"),
            @ApiImplicitParam(name = "companyId", value = "公司id", paramType = "query"),
    })
    @ResponseBody
    public OpResult alarmCountByType(Integer systemType, Integer startTime, Integer endTime, String companyId){
        return tpsonAlarmService.alarmCountByType(systemType, startTime, endTime, companyId);
    }



    @ApiOperation(value = "报警 - 获取报警楼栋分布图- 还没写" ,  notes="报警 - 获取报警楼栋分布图 - 还没写")
    @RequestMapping(value = "/getBuildingAlarmCount", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "systemType", value = "系统类型1消控联网2消防用水3用电安全5动态监测8燃气监测", paramType = "query", required = true),
            @ApiImplicitParam(name = "startTime", value = "开始时间 10位时间戳", paramType = "query", required = true),
            @ApiImplicitParam(name = "endTime", value = "结束时间 10位时间戳", paramType = "query"),
            @ApiImplicitParam(name = "companyId", value = "公司id", paramType = "query"),
            @ApiImplicitParam(name = "unhandled", value = "查询未处理报警时为1", paramType = "query"),
    })
    @ResponseBody
    public OpResult getBuildingAlarmCount(Integer systemType, Integer startTime, Integer endTime, String companyId){
        return tpsonAlarmService.getBuildingAlarmCount(systemType, startTime, endTime, companyId);
    }


    @ApiOperation(value = "报警 - 报警处理排行榜" ,  notes="报警 - 报警处理排行榜")
    @RequestMapping(value = "/getAlarmHandleRank", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "systemType", value = "系统类型1消控联网2消防用水3用电安全5动态监测8燃气监测", paramType = "query", required = true),
            @ApiImplicitParam(name = "startTime", value = "开始时间 10位时间戳", paramType = "query", required = true),
            @ApiImplicitParam(name = "endTime", value = "结束时间 10位时间戳", paramType = "query"),
            @ApiImplicitParam(name = "companyId", value = "公司id", paramType = "query"),
    })
    @ResponseBody
    public OpResult getAlarmHandleRank(Integer systemType, Integer startTime, Integer endTime, String companyId){
        return tpsonAlarmService.getAlarmHandleRank(systemType, startTime, endTime, companyId);
    }



}
