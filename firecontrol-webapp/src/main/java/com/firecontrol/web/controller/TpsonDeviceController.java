package com.firecontrol.web.controller;

import com.firecontrol.common.OpResult;
import com.firecontrol.domain.dto.DeviceSearch;
import com.firecontrol.domain.entity.TpsonDeviceEntity;
import com.firecontrol.service.TpsonDeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
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
    @RequestMapping(value = "/newDevice", method = {RequestMethod.POST})
    @ApiImplicitParams({
            @ApiImplicitParam(name="name",value="设备名称",paramType = "query",required=true),
            @ApiImplicitParam(name="code",value="设备编码（唯一）",paramType = "query", required = true),
            @ApiImplicitParam(name="type",paramType = "query",value="设备类型",required = true),
            @ApiImplicitParam(name="buildingId",paramType = "query",value="楼宇编码"),
            @ApiImplicitParam(name="floorId",paramType = "query",value="楼层编码"),
            @ApiImplicitParam(name="version",paramType = "query",value="版本id"),
            @ApiImplicitParam(name="isSync",paramType = "query",value="是否同步过(新增为false)"),
            @ApiImplicitParam(name="isAlarmLinkage",paramType = "query",value="是否关联报警(新增为false)"),
            @ApiImplicitParam(name="sim",paramType = "query",value="sim卡号"),
            @ApiImplicitParam(name="isOutdoor",paramType = "query",value="是否室外(true/false)",required = true),
            @ApiImplicitParam(name="position",paramType = "query",value="位置信息"),
            @ApiImplicitParam(name="cameraId",paramType = "query",value="关联的摄像头id,多个逗号分隔"),
            @ApiImplicitParam(name="man",paramType = "query",value="负责人，多个负责人逗号分隔"),
            @ApiImplicitParam(name="phone",paramType = "query",value="负责人tel，多个电话逗号分隔"),
            @ApiImplicitParam(name="geographic_id",paramType = "query",value="室外设备地图id"),
            @ApiImplicitParam(name="pos_x",paramType = "query",value="室内图片坐标/室外经度"),
            @ApiImplicitParam(name="pos_y",paramType = "query",value="室内图片坐标/室外纬度"),
            @ApiImplicitParam(name="position",paramType = "query",value="位置描述"),
    })
    @ResponseBody
    public OpResult insert(TpsonDeviceEntity device){
        return tpsonDeviceService.insertDevice(device);
    }


    @ApiOperation(value = "查询设备分页" ,  notes="查询设备分页")
    @RequestMapping(value = "/deviceList", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name="deviceType",value="设备类型",paramType = "query",required=true),
            @ApiImplicitParam(name="searchStr",value="名称",paramType = "query"),
            @ApiImplicitParam(name="searchCode",paramType = "query",value="设备编号"),
            @ApiImplicitParam(name="runningState",paramType = "query",value="运行状态0未激活，1离线，2正常，3故障，4报警，5禁用"),
            @ApiImplicitParam(name="currentPage",paramType = "query",value="当前页 从1开始"),
            @ApiImplicitParam(name="length",paramType = "query",value="分页大小"),
    })
    @ResponseBody
    public OpResult getDeviceByPage(DeviceSearch device){
        return tpsonDeviceService.getDeviceByPage(device);
    }



    @ApiOperation(value = "根据设备编码获取设备info" ,  notes="根据设备编码获取设备info")
    @RequestMapping(value = "/getDeviceByCode", method = {RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name="deviceCode",value="设备编码",paramType = "query", required=true),
    })
    @ResponseBody
    public OpResult getDeviceByCode(String deviceCode){
        return tpsonDeviceService.getDeviceByCode(deviceCode);
    }



    @ApiOperation(value = "编辑设备时获取设备详情" ,  notes="编辑设备时获取设备详情")
    @RequestMapping(value = "/getDeviceInfoById", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "设备id", paramType = "query", required = true),
    })
    @ResponseBody
    public OpResult getDeviceInfoById(Long id){
        return tpsonDeviceService.getDeviceById(id);
    }

    @ApiOperation(value = "更新设备信息" ,  notes="更新设备信息")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    @ResponseBody
    public OpResult update(TpsonDeviceEntity device){
        return tpsonDeviceService.updateDeviceInfo(device);
    }





    @ApiOperation(value = "更改设备状态(0 启用 1停用)" ,  notes="更改设备状态(0 启用 1停用)")
    @RequestMapping(value = "/changeDeviceStatus", method = {RequestMethod.POST})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "设备ids,多个逗号分隔", paramType = "query", required = true),
            @ApiImplicitParam(name = "status", value = "状态类型0启用1禁用", paramType = "query", required = true),
    })
    @ResponseBody
    public OpResult changeDeviceStatus(String id, Integer status){
        return tpsonDeviceService.changeDeviceStatus(id, status);
    }

    @ApiOperation(value = "（物理）删除设备状态" ,  notes="查询设备")
    @RequestMapping(value = "/deleteDevice", method = {RequestMethod.DELETE})
    @ApiImplicitParams({
            @ApiImplicitParam(name="ids",value="逗号分隔的设备id",paramType = "query",required=true),
    })
    @ResponseBody
    public OpResult deleteDeviceByIds(String ids){
        return tpsonDeviceService.deleteDeviceByIds(ids);
    }


    @ApiOperation(value = "消防联网-主机配置-tab页报警数据查询" ,  notes="消防联网-主机配置-tab页报警数据查询")
    @RequestMapping(value = "/faultCount", method = {RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name="companyId",value="公司id",paramType = "query"),
    })
    @ResponseBody
    public OpResult faultCount(Long companyId){
        return tpsonDeviceService.faultCount(companyId);
    }


    @ApiOperation(value = "获取设备类型常量列表" ,  notes="获取设备类型常量列表")
    @RequestMapping(value = "/type/list", method = {RequestMethod.GET})
    @ResponseBody
    public OpResult getDeviceTypeList(){
        return tpsonDeviceService.getDeviceTypeList();
    }

    @ApiOperation(value = "根据设备类型获取版本列表" ,  notes="根据设备类型获取版本列表")
    @RequestMapping(value = "/version", method = {RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name="deviceType",value="设备类型",paramType = "query", required=true),
    })
    @ResponseBody
    public OpResult getDeviceVersionList(Long deviceType){
        return tpsonDeviceService.getDeviceVersionList(deviceType);
    }

    @ApiOperation(value = "设备状态runningState统计" ,  notes="设备状态runningState统计")
    @RequestMapping(value = "/deviceStatusStatic", method = {RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name="systemType",value="系统类型1消控联网2消防用水3用电安全5动态监测8燃气监测",paramType = "query", required=true),
    })
    @ResponseBody
    public OpResult deviceStatusStatic(Integer systemType){
        return tpsonDeviceService.getDeviceStatusStatic(systemType);
    }


    @ApiOperation(value = "设备(离线/总数)信息" ,  notes="设备(离线/总数)信息")
    @RequestMapping(value = "/offLineStat", method = {RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name="systemType",value="系统类型1消控联网2消防用水3用电安全5动态监测8燃气监测",paramType = "query", required=true),
            @ApiImplicitParam(name="companyId",value="公司id",paramType = "query"),
    })
    @ResponseBody
    public OpResult offLineStat(Integer systemType, Long companyId){
        return tpsonDeviceService.offLineStat(systemType, companyId);
    }

    @ApiOperation(value = "获取设备传感器日志list" ,  notes="获取设备传感器日志list")
    @RequestMapping(value = "/deviceLog", method = {RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name="deviceCode",value="设备编码",paramType = "query",required=true),
            @ApiImplicitParam(name="logData",value="搜索关键字",paramType = "query"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", paramType = "query"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", paramType = "query"),
            @ApiImplicitParam(name="currentPage",paramType = "query",value="当前页 从1开始"),
            @ApiImplicitParam(name="length",paramType = "query",value="分页大小"),
    })
    @ResponseBody
    public OpResult deviceLog(String deviceCode, String logData, Integer startTime, Integer endTime, Integer currentPage, Integer length){
        return tpsonDeviceService.deviceLog(deviceCode, logData, startTime, endTime, currentPage, length);
    }


}
