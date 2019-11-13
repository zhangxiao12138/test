package com.firecontrol.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.firecontrol.common.OpResult;
import com.firecontrol.domain.dto.AlarmFaultCount;
import com.firecontrol.domain.dto.DeviceFaultDetail;
import com.firecontrol.domain.dto.FaultStatisticsDto;
import com.firecontrol.domain.entity.*;
import com.firecontrol.mapper.iotmapper.*;
import com.firecontrol.service.TpsonDeviceFaultService;
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
 * Created by mariry on 2019/10/10.
 */
@Service
public class TpsonDeviceFaultServiceImpl implements TpsonDeviceFaultService {
    private static final Logger log = LoggerFactory.getLogger(TpsonDeviceFaultService.class);

    @Autowired
    private TpsonDeviceFaultTypeMapper tpsonDeviceFaultTypeMapper;
    @Autowired
    private TpsonDeviceFaultMapper tpsonDeviceFaultMapper;
    @Autowired
    private TpsonDeviceMapper deviceMapper;
    @Autowired
    private TpsonDeviceTypeMapper deviceTypeMapper;
    @Autowired
    private TpsonDeviceVersionMapper versionMapper;


    @Override
    public OpResult getFaultStatistics(Integer systemType, Long companyId) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        Integer deviceNum = 0;
        if(systemType == null) {
            op.setMessage("systemType不可为空");
            op.setStatus(OpResult.OP_FAILED);
            return op;
        }
        try{
            //TODO: 先写死数据，后续改为查库
            //查询systemType下的设备总数
            List<Long> deviceTypeList = null;
            if(systemType != 0) {
                deviceTypeList = deviceTypeMapper.getDeviceTypeBySystemType(systemType);
            }

            if(!CollectionUtils.isEmpty(deviceTypeList)) {
                deviceNum = deviceMapper.countDeviceNumByDeviceType(deviceTypeList);
            }
            //当前未处理故障数量
            //TODO:传入companyId
            Integer unhandledFault = tpsonDeviceFaultMapper.countUnhandledFault(null);
            //本月处理故障数
            Integer monthHandledFault = tpsonDeviceFaultMapper.countMonthlyHandledFault(null, getTimeThisMonthMorning());
            //今天处理故障数
            Integer daylyHandledFault = tpsonDeviceFaultMapper.countDayleHandledFault(null, getTimeThisMorning());

            FaultStatisticsDto f = new FaultStatisticsDto(deviceNum, daylyHandledFault,monthHandledFault,unhandledFault);
            op.setDataValue(f);

        }catch (Exception e){
            log.error("TpsonDeviceFaultServiceImpl.getFaultStatistics ERROR! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }
        return op;
    }

    @Override
    public OpResult getFaultType() {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);

        try{
            List<TpsonDeviceFaultTypeEntity> typeList = tpsonDeviceFaultTypeMapper.getAll();
            op.setDataValue(typeList);
        }catch (Exception e){
            log.error("TpsonDeviceFaultServiceImpl.getFaultStatistics ERROR! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }
        return op;
    }


    @Override
    public OpResult getFaultTrendDiagram(Integer systemType, Integer companyId, Long start, Long end) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        List<Map> rtnList = null;
        List<Map> finalList = new ArrayList<>();
        log.info("getFaultTrendDiagram: systemType: " + systemType
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

//            if(!CollectionUtils.isEmpty(deviceTypeList)) {
                List<TpsonDeviceFaultEntity> faultList = tpsonDeviceFaultMapper.getFaultByTime(deviceTypeList, start, end);
                if(!CollectionUtils.isEmpty(faultList)){
                    for(TpsonDeviceFaultEntity fault : faultList){
                        for(Map m : rtnList){
                            Integer tempCount = 0;
                            for(TpsonDeviceFaultEntity f : faultList) {
                                if(f.getAddTime() > (Long)m.get("start") && f.getAddTime() < (Long)m.get("end")){
                                    tempCount ++;
                                }
                            }
                            m.put("count", tempCount);
                        }
                    }
                }
                if(!CollectionUtils.isEmpty(rtnList)) {
                    for(Map m : rtnList){
                        Map mf = new HashMap();
                        mf.put(m.get("time"), m.get("count"));
                        finalList.add(mf);
                    }
                }
                op.setDataValue(finalList);
//            }
        }catch (Exception e) {
            log.error("getFaultTrendDiagram ERROR! exception: {}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage("systemType和startTime不可为空!");
            return op;
        }
        return op;
    }

    @Override
    public OpResult getFaultList(Integer systemType, Integer faultType, Integer status, Integer isOutdoor, String deviceCode, Long startTime, Long endTime, Integer currentPage, Integer length) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        Integer total = 0;
        if(currentPage != null){
            currentPage = currentPage -1;
        }
        List<TpsonDeviceFaultEntity> faultList = new ArrayList<>();
        Map rtnMap = new HashMap();

        try{
            List<Long> deviceTypeList = null;
            if(systemType != 0) {
                deviceTypeList = deviceTypeMapper.getDeviceTypeBySystemType(systemType);
            }
//            if(!CollectionUtils.isEmpty(deviceTypeList)) {
                total = tpsonDeviceFaultMapper.getFaultCountBySearch(deviceTypeList,
                        faultType, status, isOutdoor, deviceCode, startTime, endTime);
                if(total > 0) {
                    faultList = tpsonDeviceFaultMapper.getFaultBySearch(deviceTypeList,
                            faultType, status, isOutdoor, deviceCode, startTime, endTime, currentPage, length);
                }
//            }
            rtnMap.put("total", total);
            rtnMap.put("faultList", faultList);
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
    public OpResult getFaultDetailById(Long id) {

        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        DeviceFaultDetail rtn = new DeviceFaultDetail();

        try{
            TpsonDeviceFaultEntity deviceFault = tpsonDeviceFaultMapper.selectById(id);
            //获取设备信息
            TpsonDeviceEntity device = deviceMapper.selectByDeviceCode(deviceFault.getDeviceCode());
            BeanUtils.copyProperties(device, rtn);
            //TODO：根据故障类型，赋值故障名称
            rtn.setType(deviceFault.getType());
            rtn.setType_name("通用传感器故障");
            rtn.setCount(deviceFault.getCount());
            rtn.setStatus(deviceFault.getStatus());
            rtn.setAddTime(deviceFault.getAddTime());
            rtn.setDealDetail(deviceFault.getDealDetail());
            rtn.setDeviceMan(StringUtils.isEmpty(device.getMan()) ? "" : device.getMan().split(",")[0]);
            rtn.setDevicePhone(StringUtils.isEmpty(device.getMan()) ? "" : device.getPhone().split(",")[0]);
            //versionName
            if(device.getVersion() != null){
                rtn.setDeviceVersionName(versionMapper.selectById(device.getVersion().longValue()).getName());
            }
            op.setDataValue(rtn);
        }catch (Exception e) {
            log.error("getFaultDetailById ERROR! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }

        return op;
    }

    @Override
    public OpResult updateFaultDetailById(Long id, String dealDetail) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        try{
            TpsonDeviceFaultEntity fault = new TpsonDeviceFaultEntity();
            fault.setId(id);
            Long updateTime = new Date().getTime();
            updateTime = updateTime/1000;
            fault.setUpdateTime(updateTime.intValue());
            fault.setDealTime(updateTime.intValue());
            fault.setDealDetail(dealDetail);
            fault.setStatus(new Byte((byte)1));
            tpsonDeviceFaultMapper.updateById(fault);

        }catch (Exception e){
            log.error("updateFaultDetailById ERROR! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }

        return op;
    }

    @Override
    public OpResult deleteHandledFaultByIds(String ids) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);

        try{
            //未处理的故障不可删除
            if(StringUtils.isEmpty(ids)){
                op.setMessage("ids不可为空");
                op.setStatus(OpResult.OP_SUCCESS);
                return op;
            }
            //查询待删除的id中是否有未处理的记录
            List<Long> idList =  tpsonDeviceFaultMapper.getUnhandledFaultByIds(Arrays.asList(ids.split(",")));
            if(!CollectionUtils.isEmpty(idList)){
                op.setStatus(OpResult.OP_FAILED);
                op.setMessage("未处理的故障信息不可删除!");
                return op;
            }

            tpsonDeviceFaultMapper.deleteHandledFaultByIds(Arrays.asList(ids.split(",")));

        }catch (Exception e){
            log.error("deleteHandledFaultByIds ERROR! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }

        return op;
    }

    @Override
    public OpResult batchDeal(String ids, String dealDetail) {

        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        try{
            if(StringUtils.isEmpty(ids) || StringUtils.isEmpty(dealDetail)){
                op.setStatus(OpResult.OP_FAILED);
                op.setMessage("ids和批处理意见不可为空!");
                return op;
            }

            tpsonDeviceFaultMapper.updateBatchDeal(Arrays.asList(ids.split(",")), dealDetail);

        }catch (Exception e){
            log.error("batchDeal ERROR! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }

        return op;
    }

    @Override
    public AlarmFaultCount homePageFaultCount(AlarmFaultCount rtn) {
        try{
            Calendar cal = Calendar.getInstance();

            cal.set(Calendar.HOUR_OF_DAY,0);
            cal.set(Calendar.MINUTE,0);
            cal.set(Calendar.SECOND,0);
            Long startTime = cal.getTimeInMillis()/1000;
            rtn.setTodayFault(tpsonDeviceFaultMapper.getFaultCountBySearch(null,null,null,null,null,startTime, null));

            cal.set(Calendar.DAY_OF_MONTH, 1);
            startTime = cal.getTimeInMillis()/1000;
            rtn.setThisMonthFault(tpsonDeviceFaultMapper.getFaultCountBySearch(null,null,null,null,null,startTime, null));

            cal.set(Calendar.MONTH, 1);
            startTime = cal.getTimeInMillis()/1000;
            rtn.setThisYearFault(tpsonDeviceFaultMapper.getFaultCountBySearch(null,null,null,null,null,startTime, null));

        }catch (Exception e){
            log.error("homePageFaultCount ERROR! e={}", e);
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
            cend.setTime(endTime);

            while(cstart.getTimeInMillis() < cend.getTimeInMillis()){
                Map map = new HashMap();
                map.put(dateFormat.format(cstart.getTime()), 0);
                map.put("time", dateFormat.format(cstart.getTime()));
                map.put("start", cstart.getTimeInMillis()/1000);
                cstart.add(Calendar.DAY_OF_MONTH,1);
                map.put("end", cstart.getTimeInMillis()/1000);
                map.put("count", 0);
                rtnList.add(map);
            }
            //Long diffDays = (cend.getTimeInMillis() - cstart.getTimeInMillis()) / (1000 * 60 * 60 * 24);

        }catch (Exception e) {
            log.error("initFaultResultList error! e={}", e);
            return null;
        }
        return rtnList;
    }

    private Integer getTimeThisMonthMorning(){
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0,0);
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return (int) (cal.getTimeInMillis()/1000);
    }

    private static int getTimeThisMorning(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (int) (cal.getTimeInMillis()/1000);
    }

}
