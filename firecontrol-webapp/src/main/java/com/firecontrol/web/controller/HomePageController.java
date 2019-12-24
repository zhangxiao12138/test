package com.firecontrol.web.controller;

import com.firecontrol.common.OpResult;
import com.firecontrol.common.WebSocketService;
import com.firecontrol.domain.dto.AlarmFaultCount;
import com.firecontrol.mapper.ydmapper.UserMapper;
import com.firecontrol.service.TpsonAlarmService;
import com.firecontrol.service.TpsonDeviceFaultService;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by mariry on 2019/11/12.
 */
@Api(description = "首页数据接口")
@Controller
@RequestMapping(value = "/tainbo/homepage")
public class HomePageController {
    @Autowired
    private TpsonDeviceService tpsonDeviceService;

    @Autowired
    private TpsonDeviceFaultService tpsonDeviceFaultService;

    @Autowired
    private TpsonAlarmService tpsonAlarmService;

    @Autowired
    private WebSocketService webSocketService;
    @Autowired
    private UserMapper userMapper;





    @ApiOperation(value = "设备状态统计信息" ,  notes="设备状态统计信息")
    @RequestMapping(value = "/deviceStat", method = {RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name="companyId",value="公司id",paramType = "query"),
    })
    @ResponseBody
    public OpResult deviceStat(Long companyId){
        return tpsonDeviceService.deviceStat(companyId);
    }



    @ApiOperation(value = "报警类型Top5" ,  notes="报警类型Top5")
    @RequestMapping(value = "/alarmTop", method = {RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name="startTime",value="开始时间",paramType = "query", required = true),
            @ApiImplicitParam(name="endTime",value="结束时间",paramType = "query"),
            @ApiImplicitParam(name="companyId",value="公司id",paramType = "query"),
    })
    @ResponseBody
    public OpResult alarmTop5(Integer startTime, Integer endTime, Long companyId){
        return tpsonAlarmService.alarmTop5(startTime, endTime, companyId);

    }

    @ApiOperation(value = "本年、本月、本日报警及故障" ,  notes="本年、本月、本日报警及故障")
    @RequestMapping(value = "/alarmFaultCount", method = {RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name="companyId",value="公司id",paramType = "query"),
    })
    @ResponseBody
    public OpResult alarmFaultCount(Long companyId){
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        AlarmFaultCount rtn = new AlarmFaultCount();
        rtn = tpsonAlarmService.homePageAlarmCount(rtn);
        rtn = tpsonDeviceFaultService.homePageFaultCount(rtn);


        //TODO:
        rtn.setTodayLogin(webSocketService.getVisitCount());

        rtn.setCurrentLogin(webSocketService.getOnlineCount());
        rtn.setTotalUserAmount(userMapper.getTotal());
        op.setDataValue(rtn);

        return op;
    }


    @ApiOperation(value = "有效、未处理、巡查" ,  notes="有效、未处理、巡查")
    @RequestMapping(value = "/sumRateStat", method = {RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name="startTime",value="开始时间",paramType = "query", required = true),
            @ApiImplicitParam(name="endTime",value="结束时间",paramType = "query"),
            @ApiImplicitParam(name="companyId",value="公司id",paramType = "query"),
    })
    @ResponseBody
    public OpResult sumRateStat(Long startTime, Long endTime, Long companyId){
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        Map rtn = new HashMap<>();
        rtn = tpsonAlarmService.homePageSumRateStat(rtn, startTime, endTime, companyId);

        //TODO:补充巡查数据
        rtn.put("patrolCount", 0);
        rtn.put("patrolRage", 0.00);

        op.setDataValue(rtn);

        return op;
    }


    @ApiOperation(value = "当前接入电器数据" ,  notes="当前接入电器数据")
    @RequestMapping(value = "/currentElesticStat", method = {RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name="companyId",value="公司id",paramType = "query"),
    })
    @ResponseBody
    public OpResult currentElesticStat(Long startTime, Long endTime, Long companyId){
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        Map rtn = new HashMap<>();
        //TODO:

        rtn.put("totalAmount", 0);
        rtn.put("onlineAmount", 0);
        rtn.put("totalPower", 0);

        return op;
    }

    @ApiOperation(value = "用电量图表数据" ,  notes="用电量图表数据")
    @RequestMapping(value = "/elesticPowerStat", method = {RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name="startTime",value="开始时间",paramType = "query", required = true),
            @ApiImplicitParam(name="endTime",value="结束时间",paramType = "query"),
            @ApiImplicitParam(name="companyId",value="公司id",paramType = "query"),
    })
    @ResponseBody
    public OpResult elesticPowerStat(Long startTime, Long endTime, Long companyId){
        //TODO:
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        Map rtn = new HashMap<>();

        List<Map> rtnList = new ArrayList<>();
        List<String> timeList = new ArrayList<>();
        Date start = new Date(startTime*1000);
        Date end = (endTime==null)? new Date() : new Date(endTime*1000);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            try{
                Calendar cstart = Calendar.getInstance();
                Calendar cend = Calendar.getInstance();
                cstart.setTime(start);
                cend.setTime(end);

                while(cstart.getTimeInMillis() < cend.getTimeInMillis()){
                    Map map = new HashMap();
                    map.put("time", dateFormat.format(cstart.getTime()));
                    map.put("count", 0);
                    map.put("start", cstart.getTimeInMillis()/1000);
                    cstart.add(Calendar.DAY_OF_MONTH,1);
                    map.put("end", cstart.getTimeInMillis()/1000);
                    rtnList.add(map);
                    timeList.add((String)map.get("time"));
                }
                //Long diffDays = (cend.getTimeInMillis() - cstart.getTimeInMillis()) / (1000 * 60 * 60 * 24);

            }catch (Exception e) {
                return null;
            }

        rtn.put("timeStr", timeList);
        rtn.put("dataList",rtnList);
        op.setDataValue(rtn);

        return op;
    }


}
