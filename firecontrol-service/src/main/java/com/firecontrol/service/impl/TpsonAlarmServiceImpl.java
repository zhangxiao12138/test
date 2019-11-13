package com.firecontrol.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.firecontrol.common.OpResult;
import com.firecontrol.domain.dto.*;
import com.firecontrol.domain.entity.*;
import com.firecontrol.mapper.iotmapper.*;
import com.firecontrol.service.TpsonAlarmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by mariry on 2019/10/11.
 */
@Service
public class TpsonAlarmServiceImpl implements TpsonAlarmService{
    private static final Logger log = LoggerFactory.getLogger(TpsonAlarmService.class);

    @Autowired
    private TpsonAlarmMapper tpsonAlarmMapper;
    @Autowired
    private TpsonDeviceTypeMapper deviceTypeMapper;
    @Autowired
    private TpsonAlarmTypeMapper tpsonAlarmTypeMapper;
    @Autowired
    private TpsonDeviceMapper tpsonDeviceMapper;
    @Autowired
    private TpsonDeviceVersionMapper versionMapper;



    @Override
    public OpResult getAlarmStatistics(Integer systemType, Long companyId) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        Map rtnMap = new HashMap<>();

        try{
            List<Long> deviceTypeList = null;
            if(systemType != 0) {
                deviceTypeList = deviceTypeMapper.getDeviceTypeBySystemType(systemType);
            }
            List<Integer> totalAndUnhandled = null;
            totalAndUnhandled = tpsonAlarmMapper.getAlarmCountByDeviceType(deviceTypeList);

            rtnMap.put("total", totalAndUnhandled.get(0));
            rtnMap.put("unhandled", totalAndUnhandled.get(0) - totalAndUnhandled.get(1));
            rtnMap.put("handled", totalAndUnhandled.get(1));
            op.setDataValue(rtnMap);

        }catch (Exception e){
            log.error("getAlarmStatistics ERROR! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }
        return op;
    }


    @Override
    public OpResult getAlarmTypeBySystemType(Integer systemType, Long deviceType) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        List<TpsonAlarmType> rtnList = new ArrayList<>();

        try{
            rtnList = tpsonAlarmTypeMapper.getBySystemDeviceType(systemType,deviceType);
            op.setDataValue(rtnList);
        }catch (Exception e) {
            log.error("getAlarmTypeBySystemType ERROR! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }
        return op;
    }


    @Override
    public OpResult getAlarmTypeByDeviceType(Long deviceType) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        List<TpsonAlarmType> rtnList = new ArrayList<>();

        try{
            rtnList = tpsonAlarmTypeMapper.getByDeviceType(deviceType);
            op.setDataValue(rtnList);
        }catch (Exception e) {
            log.error("getAlarmTypeByDeviceType ERROR! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }
        return op;
    }

    @Override
    public OpResult getAlarmTrendDiagram(Integer systemType, Integer companyId, Long start, Long end) {

        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        List<Map> rtnList = null;
        log.info("getAlarmTrendDiagram: systemType: " + systemType
                + ", campanyId:" + companyId + ", startTime:" + start + "endTime:" + end);

        if(systemType == null || start == null){
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage("systemType和startTime不可为空!");
            return op;
        }
        try{
            Date startTime = new Date(start*1000);
            Date endTime = (end==null)? new Date() : new Date(end*1000);
            //初始化list
            rtnList = initFaultResultList(startTime, endTime);
            if(CollectionUtils.isEmpty(rtnList)) {
                op.setStatus(OpResult.OP_FAILED);
                op.setMessage("未知错误，一定是打开姿势不对");
                return op;
            }
            //获取起止时间之内的所有报警记录
            List<Long> deviceTypeList = null;
            if(systemType != 0){
                deviceTypeList = deviceTypeMapper.getDeviceTypeBySystemType(systemType);
            }

            //if(!CollectionUtils.isEmpty(deviceTypeList)) {
                List<TpsonAlarmEntity> alarmList = tpsonAlarmMapper.getAlarmByTime(deviceTypeList, start, end);
                if(!CollectionUtils.isEmpty(alarmList)){
                    //往list里塞数据
                    for(Map m : rtnList){
                        Integer tempCount = 0;
                        for(TpsonAlarmEntity alarm : alarmList) {
                            if(alarm.getAddTime() > (Long)m.get("start") && alarm.getAddTime() < (Long)m.get("end")){
                                tempCount ++;
                            }
                        }
                        m.put("count", tempCount);
                    }
                }
                op.setDataValue(rtnList);
           // }
        }catch (Exception e) {
            log.error("getFaultTrendDiagram ERROR! exception: {}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage("systemType和startTime不可为空!");
            return op;
        }
        return op;
    }

    @Override
    public OpResult getAlarmList(Integer systemType, Integer faultType, Integer status, Integer isOutdoor, String deviceCode, Long startTime, Long endTime, Integer currentPage, Integer length) {

        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        Integer total = 0;
        if(currentPage != null && currentPage > 0){
            currentPage = currentPage -1;
        }
        List<TpsonAlarmEntity> alarmList = new ArrayList<>();
        Map rtnMap = new HashMap();

        try{
            List<Long> deviceTypeList = null;
            if(systemType != 0){
                deviceTypeList = deviceTypeMapper.getDeviceTypeBySystemType(systemType);
            }

//            if(!CollectionUtils.isEmpty(deviceTypeList)) {
                total = tpsonAlarmMapper.getAlarmCountBySearch(deviceTypeList,
                        faultType, status, isOutdoor, deviceCode, startTime, endTime);
                if(total > 0) {
                    alarmList = tpsonAlarmMapper.getAlarmBySearch(deviceTypeList,
                            faultType, status, isOutdoor, deviceCode, startTime, endTime, currentPage, length);
                    if(!CollectionUtils.isEmpty(alarmList)){
                        //TODO: 从多数据源根据id获取用户名
                        for(TpsonAlarmEntity a : alarmList) {
                            a.setDealUserName("学习中");
                        }
                    }
                }
//            }
            rtnMap.put("total", total);
            rtnMap.put("alarmList", alarmList);
            op.setDataValue(rtnMap);

        }catch (Exception e) {
            log.error("getFaultList ERROR! exception: {}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }
        return op;

    }

    @Override
    public OpResult deleteHandledAlarmByIds(String ids) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);

        try{
            //未处理的故障不可删除
            if(StringUtils.isEmpty(ids)){
                op.setMessage("ids不可为空");
                op.setStatus(OpResult.OP_SUCCESS);
                return op;
            }
            //查询待删除的id中是否有未处理的记录
            List<Long> idList =  tpsonAlarmMapper.getUnhandledAlarmByIds(Arrays.asList(ids.split(",")));
            if(!CollectionUtils.isEmpty(idList)){
                op.setStatus(OpResult.OP_FAILED);
                op.setMessage("未处理的报警信息不可删除!");
                return op;
            }
            tpsonAlarmMapper.deleteHandledAlarmByIds(Arrays.asList(ids.split(",")));

        }catch (Exception e){
            log.error("deleteHandledFaultByIds ERROR! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }
        return op;
    }

    @Override
    public OpResult batchHandle(String ids, Integer status, String dealDetail) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        try{
            if(StringUtils.isEmpty(ids) || StringUtils.isEmpty(dealDetail) || status == null){
                op.setStatus(OpResult.OP_FAILED);
                op.setMessage("ids/处理状态/批处理意见均不可为空!");
                return op;
            }

            //只能处理“未处理”的-----前端应过滤


            tpsonAlarmMapper.updateBatchDeal(Arrays.asList(ids.split(",")), status, dealDetail);
        }catch (Exception e){
            log.error("batchHandle ERROR! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }

        return op;
    }

    @Override
    public OpResult getDealStatus() {

        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        try{
            List<AlarmDealStatus> rtnList = new ArrayList<>();
            rtnList.add(new AlarmDealStatus(0, "未处理"));
            rtnList.add(new AlarmDealStatus(1, "有效处理"));
            rtnList.add(new AlarmDealStatus(2, "误报"));
            rtnList.add(new AlarmDealStatus(3, "消防测试"));
            rtnList.add(new AlarmDealStatus(4, "维保检修"));
            rtnList.add(new AlarmDealStatus(5, "其他"));

            op.setDataValue(rtnList);

        }catch (Exception e){
            log.error("getDealStatus ERROR! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }
        return op;
    }


    @Override
    public OpResult getAlarmDetailById(Long id) {

        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        try{
            AlarmDetailDto detail = new AlarmDetailDto();
            TpsonAlarmEntity alarm = tpsonAlarmMapper.selectById(id);
            TpsonDeviceEntity device = tpsonDeviceMapper.selectByDeviceCode(alarm.getDeviceCode());
            BeanUtils.copyProperties(alarm, detail);
            detail.setDeviceCode(device.getCode());
            detail.setDeviceName(device.getName());
            detail.setBuildingId(device.getBuildingId());
            detail.setDeviceVersion(device.getVersion());
            detail.setDeviceVersionName(versionMapper.selectById(device.getVersion().longValue()).getName());

            detail.setLimitDown(alarm.getLimitDown());
            detail.setLevel(alarm.getLevel());
            detail.setLevelName(fmtLevelName(alarm.getLevel()));
            detail.setDealUserId(alarm.getDealUserId());
            detail.setDealUserName(fmtDealUserName(alarm.getDealUserId()));

            //TODO: 完善各种楼宇信息

            op.setDataValue(detail);


        }catch (Exception e){
            log.error("getDealStatus ERROR! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }
        return op;

    }


    @Override
    public OpResult updateAlarmDetailById(Long id, Integer status, String dealDetail) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        try{
            if(id == null || status == null || StringUtils.isEmpty(dealDetail)) {
                op.setStatus(OpResult.OP_FAILED);
                op.setMessage("id/处理状态/处理备注均不可为空!");
                return op;
            }
            tpsonAlarmMapper.updateAlarmDeal(id, status, dealDetail);

        }catch (Exception e){
            log.error("updateAlarmDetailById ERROR! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }
        return op;

    }

    @Override
    public OpResult dealTypeCount(Integer systemType, Integer startTime, Integer endTime, String companyId) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        List<AlarmDealCountDto> list = null;
        try{
            if(systemType == null || startTime == null) {
                op.setStatus(OpResult.OP_FAILED);
                op.setMessage("系统类型/开始时间不可为空!");
                return op;
            }
            List<AlarmDealCountDto> rtnList = initAlarmDealCountList();

            List<Long> deviceTypeList = null;
            if(systemType != 0){
                deviceTypeList = deviceTypeMapper.getDeviceTypeBySystemType(systemType);
            }

//            if(!CollectionUtils.isEmpty(deviceTypeList)){
                list = tpsonAlarmMapper.dealTypeCount(deviceTypeList, startTime, endTime, companyId);
//            }

            if(!CollectionUtils.isEmpty(list)){
                for(AlarmDealCountDto d : list){
                    for(AlarmDealCountDto r : rtnList){
                        if(r.getStatus() == d.getStatus()){
                            r.setAmount(d.getAmount());
                        }
                    }
                }
            }
            op.setDataValue(rtnList);
        }catch (Exception e){
            log.error("dealTypeCount ERROR! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }
        return op;
    }

    @Override
    public OpResult getBuildingAlarmCount(Integer systemType, Integer startTime, Integer endTime, String companyId) {

        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        try{
            if(systemType == null || startTime == null) {
                op.setStatus(OpResult.OP_FAILED);
                op.setMessage("系统类型/开始时间均不可为空!");
                return op;
            }

            //TODO: 多数据源，自己获取楼宇信息；
            // 获取楼宇信息之后，再看看这里怎么做好
            List<Map> list = new ArrayList<>();
            Map tmp = new HashMap();

            tmp.put("buildingId", "wwM75Oa0");
            tmp.put("name", "凤凰城");
            tmp.put("total", 12);
            tmp.put("unhandle", 0);
            list.add(tmp);
            op.setDataValue(list);



        }catch (Exception e){
            log.error("getBuildingAlarmCount ERROR! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }
        return op;


    }

    @Override
    public OpResult alarmCountByType(Integer systemType, Integer startTime, Integer endTime, String companyId) {

        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);

        List<AlarmTypeCountDto> list = null;
        try{
            if(systemType == null || startTime == null) {
                op.setStatus(OpResult.OP_FAILED);
                op.setMessage("系统类型/开始时间不可为空!");
                return op;
            }
            List<AlarmTypeCountDto> rtnList = initAlarmCountByType(systemType);

            List<Long> deviceTypeList = null;
            if(systemType != 0) {
                deviceTypeList = deviceTypeMapper.getDeviceTypeBySystemType(systemType);
            }

//            if(!CollectionUtils.isEmpty(deviceTypeList)){
                list = tpsonAlarmMapper.alarmTypeCount(deviceTypeList, startTime, endTime, companyId);
//            }

            if(!CollectionUtils.isEmpty(list)){
                for(AlarmTypeCountDto d : list){
                    for(AlarmTypeCountDto r : rtnList){
                        if(r.getAlarmType() == d.getAlarmType()){
                            r.setAmount(d.getAmount());
                        }
                    }
                }
            }
            op.setDataValue(rtnList);
        }catch (Exception e){
            log.error("dealTypeCount ERROR! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }
        return op;

    }

    @Override
    public OpResult getAlarmHandleRank(Integer systemType, Integer startTime, Integer endTime, String companyId) {

        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);

        List<AlarmHandleCountDto> list = null;
        try{
            if(systemType == null || startTime == null) {
                op.setStatus(OpResult.OP_FAILED);
                op.setMessage("系统类型/开始时间不可为空!");
                return op;
            }
            List<Long> deviceTypeList = null;
            if(systemType != 0) {
                deviceTypeList = deviceTypeMapper.getDeviceTypeBySystemType(systemType);
            }

//            if(!CollectionUtils.isEmpty(deviceTypeList)){
                list = tpsonAlarmMapper.getAlarmHandleRank(deviceTypeList, startTime, endTime, companyId);
//            }

            op.setDataValue(list);
        }catch (Exception e){
            log.error("dealTypeCount ERROR! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }
        return op;
    }


    @Override
    public OpResult alarmTop5(Integer startTime, Integer endTime, Long companyId) {

        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);

        List<AlarmTypeCountDto> list = null;
        try{
            if(startTime == null) {
                op.setStatus(OpResult.OP_FAILED);
                op.setMessage("开始时间不可为空!");
                return op;
            }

            list = tpsonAlarmMapper.getAlarmTypeRank(startTime, endTime, companyId);
            if(!CollectionUtils.isEmpty(list)) {
                list.stream().forEach(a -> {
                    a.setAlarmTypeName(tpsonAlarmMapper.getAlarmTypeNameByType(a.getAlarmType()));
                });
            }

            op.setDataValue(list);
        }catch (Exception e){
            log.error("dealTypeCount ERROR! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }
        return op;

    }

    @Override
    public AlarmFaultCount homePageAlarmCount(AlarmFaultCount rtn) {
        try{
            Calendar cal = Calendar.getInstance();

            cal.set(Calendar.HOUR_OF_DAY,0);
            cal.set(Calendar.MINUTE,0);
            cal.set(Calendar.SECOND,0);
            Long startTime = cal.getTimeInMillis()/1000;
            rtn.setTodayAlarm(tpsonAlarmMapper.getAlarmCountBySearch(null,null,null,null,null,startTime, null));

            cal.set(Calendar.DAY_OF_MONTH, 1);
            startTime = cal.getTimeInMillis()/1000;
            rtn.setThisMonthAlarm(tpsonAlarmMapper.getAlarmCountBySearch(null,null,null,null,null,startTime, null));

            cal.set(Calendar.MONTH, 1);
            startTime = cal.getTimeInMillis()/1000;
            rtn.setThisYearAlarm(tpsonAlarmMapper.getAlarmCountBySearch(null,null,null,null,null,startTime, null));

        }catch (Exception e){
            log.error("AlarmFaultCount ERROR! e={}", e);
            return rtn;
        }
        return rtn;
    }

    /**
     * 为首页下方图表提供接口数据
     * 查询30天之前到当天的数据及比例
     * @param rtn
     * @param startTime
     * @param endTime
     * @param companyId
     * @return
     */
    @Override
    public Map homePageSumRateStat(Map rtn, Long startTime, Long endTime, Long companyId) {

        try{
            List<SumRateStatDto> dbresult = tpsonAlarmMapper.homePageSumRateStat(startTime, endTime, companyId);
            Integer total = tpsonAlarmMapper.getAlarmCountBySearch(null, null,null, null, null,startTime,endTime);
            if(!CollectionUtils.isEmpty(dbresult)) {
                for(SumRateStatDto s : dbresult){
                    //0:unhandle 1:handled 2:misAlarm
                    if(s.getStatus() == 0){
                        rtn.put("unhandleCount", s.getAmount());
                        rtn.put("unhandleRate", s.getAmount().doubleValue()/total);
                    }
                    if(s.getStatus() == 1){
                        rtn.put("handledCount", s.getAmount());
                        rtn.put("handleRate", s.getAmount().doubleValue()/total);
                    }
                    if(s.getStatus() == 2){
                        rtn.put("misAlarmCount", s.getAmount());
                        rtn.put("misAlarmRate", s.getAmount().doubleValue()/total);
                    }
                }
            }
        }catch (Exception e){
            log.error("homePageSumRateStat ERROR! e= {}", e);
            return rtn;
        }

        return rtn;
    }


    private List<Map> initFaultResultList(Date startTime, Date endTime) {
        List<Map> rtnList = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try{
            Calendar cstart = Calendar.getInstance();
            Calendar cend = Calendar.getInstance();
            cstart.setTime(startTime);
            //防止前端传的时间不是0点0分，这里重置一遍
            cstart.set(Calendar.HOUR, 0);
            cstart.set(Calendar.MINUTE, 0);
            cstart.set(Calendar.SECOND, 0);
            cend.setTime(endTime);

            while(cstart.getTimeInMillis() < cend.getTimeInMillis()){
                Map map = new HashMap();
                map.put("time", dateFormat.format(cstart.getTime()));
                map.put("count", 0);
                map.put("start", cstart.getTimeInMillis()/1000);
                cstart.add(Calendar.DAY_OF_MONTH,1);
                map.put("end", cstart.getTimeInMillis()/1000);
                rtnList.add(map);
            }
            //Long diffDays = (cend.getTimeInMillis() - cstart.getTimeInMillis()) / (1000 * 60 * 60 * 24);

        }catch (Exception e) {
            log.error("initFaultResultList error! e={}", e);
            return null;
        }
        return rtnList;
    }



    private List<AlarmDealCountDto> initAlarmDealCountList() {

        List<AlarmDealCountDto> list = new ArrayList<>();
        list.add(new AlarmDealCountDto(0, "未处理", 0));
        list.add(new AlarmDealCountDto(1, "有效处理", 0));
        list.add(new AlarmDealCountDto(2, "误报", 0));
        list.add(new AlarmDealCountDto(3, "消防测试", 0));
        list.add(new AlarmDealCountDto(4, "维保检修", 0));
        list.add(new AlarmDealCountDto(5, "其他", 0));

        return list;
    }

    private List<AlarmTypeCountDto> initAlarmCountByType(Integer systemType) {
        List<AlarmTypeCountDto> list = new ArrayList<>();
        List<TpsonAlarmType> typeList = tpsonAlarmTypeMapper.getBySystemDeviceType(systemType, null);
        if(!CollectionUtils.isEmpty(typeList)){
            typeList.stream().forEach(t -> {
                list.add(new AlarmTypeCountDto(t.getId().intValue(), t.getName(), 0));
            });
        }
        return list;
    }

    private String fmtLevelName(Integer level) {
        String rtn = null;
        if(level == 1) rtn = "低";
        if(level == 2) rtn = "中";
        if(level == 3) rtn = "高";
        return rtn;
    }


    private String fmtDealUserName(Long id) {
        //TODO: 多数据源根据id直接查询
        return "学习中";
    }

}
