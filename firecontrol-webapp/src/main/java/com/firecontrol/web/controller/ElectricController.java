package com.firecontrol.web.controller;

import com.firecontrol.common.OpResult;
import com.firecontrol.domain.dto.DeviceSearch;
import com.firecontrol.domain.entity.ElectricLog;
import com.firecontrol.domain.entity.ElectricType;
import com.firecontrol.mapper.iotmapper.ElectricTypeMapper;
import com.firecontrol.service.ElectricLogService;
import com.firecontrol.service.ElectricTypeService;
import com.firecontrol.service.TpsonDeviceService;
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
 * Created by mariry on 2019/11/25.
 */
@Api(description = "用电设备（拓深）接口")
@Controller
@RequestMapping(value = "/tainbo/electric")
public class ElectricController {

    @Autowired
    private TpsonDeviceService tpsonDeviceService;
    @Autowired
    private ElectricTypeService electricTypeService;
    @Autowired
    private ElectricLogService electricLogService;




    @ApiOperation(value = "选择主机（不分页）" ,  notes="选择主机（不分页）")
    @RequestMapping(value = "/deviceList", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name="deviceType",value="设备类型，用电3",paramType = "query",required=true),
            @ApiImplicitParam(name="searchStr",value="名称模糊匹配项",paramType = "query"),
    })
    @ResponseBody
    public OpResult getElectricList(DeviceSearch device){
        device.setDeviceType(3);

        return tpsonDeviceService.getDeviceList(device);
    }


    @ApiOperation(value = "获取总能耗曲线图数据list" ,  notes="获取总能耗曲线图数据list")
    @RequestMapping(value = "/diagram", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "开始时间", paramType = "query", required = true),
            @ApiImplicitParam(name = "end", value = "结束时间", paramType = "query"),
            @ApiImplicitParam(name = "deviceCode", value = "用电设备code", paramType = "query"),
            @ApiImplicitParam(name = "isOutdoor", value = "是否室外0否1是", paramType = "query",required = true),
            @ApiImplicitParam(name = "companyId", value = "companyId", paramType = "query"),

    })
    @ResponseBody
    public OpResult getElectricDiagram(Integer companyId, Long start, Long end, String deviceCode){
        return tpsonDeviceService.getElectricDiagram( companyId, start, end, deviceCode);
    }


    @ApiOperation(value = "总功率/电器数/离线设备" ,  notes="总功率/电器数/离线设备")
    @RequestMapping(value = "/statistic", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isOutdoor", value = "是否室外0否1是", paramType = "query",required = true),
            @ApiImplicitParam(name = "deviceCode", value = "用电设备code", paramType = "query"),
            @ApiImplicitParam(name = "companyId", value = "companyId", paramType = "query"),

    })
    @ResponseBody
    public OpResult getElectricDeviceStatistic(DeviceSearch search){
        return tpsonDeviceService.getElectricDeviceStatistic(search);
    }


    @ApiOperation(value = "历史与学习" ,  notes="历史与学习")
    @RequestMapping(value = "/logLearn", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isOutdoor", value = "是否室外0否1是", paramType = "query",required = true),
            @ApiImplicitParam(name = "deviceCode", value = "设备编码，模糊匹配", paramType = "query"),
            @ApiImplicitParam(name = "powerType", value = "0普通类型，2大功率电器，4发热电器，6大功率发热电器", paramType = "query"),
            @ApiImplicitParam(name = "action", value = "电器状态：-1拔出，0无动作，1接入，2换挡或模式变化", paramType = "query"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", paramType = "query"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", paramType = "query"),
            @ApiImplicitParam(name = "companyId", value = "companyId", paramType = "query"),
            @ApiImplicitParam(name = "currentPage", value = "当前页从1开始", paramType = "query"),
            @ApiImplicitParam(name = "length", value = "分页大小", paramType = "query"),

    })
    @ResponseBody
    public OpResult getLog(ElectricLog search){
        return electricLogService.getLog(search);
    }


    @ApiOperation(value = "获取所有电器名称" ,  notes="获取所有电器名称")
    @RequestMapping(value = "/listDeviceName", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "电器名称，模糊查询", paramType = "query"),
    })
    @ResponseBody
    public OpResult listDeviceName(String name){
        return electricTypeService.listDeviceName(name);
    }


    @ApiOperation(value = "添加一种电器类型" ,  notes="添加一种电器类型")
    @RequestMapping(value = "/addType", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "新增的电器类型名称", paramType = "query",required = true),
            @ApiImplicitParam(name = "level", value = "等级，默认2", paramType = "query"),
    })
    @ResponseBody
    public OpResult addType(String name, Integer level){
        return electricTypeService.addType(name, level);
    }



    @ApiOperation(value = "学习一个设备" ,  notes="学习一个设备")
    @RequestMapping(value = "/learn", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "选中的记录的id", paramType = "query",required = true),
            @ApiImplicitParam(name = "type", value = "更改为的电器种类的id", paramType = "query",required = true),
    })
    @ResponseBody
    public OpResult learn(Long id, Long type){
        return electricLogService.learn(id, type);
    }


    //electrical_access_count
    @ApiOperation(value = "接入电器数据统计" ,  notes="接入电器数据统计")
    @RequestMapping(value = "/accessStatistic", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isOutdoor", value = "是否室外 1是 0否", paramType = "query",required = true),
            @ApiImplicitParam(name = "deviceId", value = "电气设备id", paramType = "query"),
            @ApiImplicitParam(name = "deviceCode", value = "电气设备Code", paramType = "query"),
            @ApiImplicitParam(name = "companyId", value = "商家id", paramType = "query"),
    })
    @ResponseBody
    public OpResult accessStatistic(Integer isOutdoor, Long deviceId, String deviceCode, Long companyId){
        return electricLogService.accessStatistic(isOutdoor, deviceId, deviceCode, companyId);
    }

    @ApiOperation(value = "关电闸" ,  notes="关电闸")
    @RequestMapping(value = "/setRelay", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceCode", value = "电气设备Code", paramType = "query"),
            @ApiImplicitParam(name = "switchOn", value = "目标状态1开 0关", paramType = "query"),
    })
    @ResponseBody
    public OpResult setRelay(String deviceCode, Integer switchOn){
        return tpsonDeviceService.setRelay(deviceCode, switchOn);
    }


    @ApiOperation(value = "实时监测-获取设备数据" ,  notes="实时监测-获取设备数据")
    @RequestMapping(value = "/realTimeWatch", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isOutdoor", value = "是否室外 1是 0否", paramType = "query"),
            @ApiImplicitParam(name = "currentPage", value = "当前页，从1开始", paramType = "query"),
            @ApiImplicitParam(name = "length", value = "分页大小", paramType = "query"),
            @ApiImplicitParam(name = "companyId", value = "公司编码", paramType = "query"),
    })
    @ResponseBody
    public OpResult realTimeWatch(DeviceSearch search){
        return tpsonDeviceService.realTimeWatch(search);
    }


}
