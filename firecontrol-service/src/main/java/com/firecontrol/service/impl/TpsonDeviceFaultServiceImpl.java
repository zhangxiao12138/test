package com.firecontrol.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.firecontrol.common.OpResult;
import com.firecontrol.domain.dto.DeviceFaultDetail;
import com.firecontrol.domain.dto.FaultStatisticsDto;
import com.firecontrol.domain.entity.TpsonDeviceEntity;
import com.firecontrol.domain.entity.TpsonDeviceFaultEntity;
import com.firecontrol.domain.entity.TpsonDeviceFaultTypeEntity;
import com.firecontrol.mapper.TpsonDeviceFaultMapper;
import com.firecontrol.mapper.TpsonDeviceFaultTypeMapper;
import com.firecontrol.mapper.TpsonDeviceMapper;
import com.firecontrol.mapper.TpsonDeviceTypeMapper;
import com.firecontrol.service.TpsonDeviceFaultService;
import io.swagger.models.auth.In;
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


    @Override
    public OpResult getFaultStatistics(Integer systemType) {
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
            List<Long> deviceTypeList = deviceTypeMapper.getDeviceTypeBySystemType(systemType);
            if(!CollectionUtils.isEmpty(deviceTypeList)) {
                deviceNum = deviceMapper.countDeviceNumByDeviceType(deviceTypeList);
            }
            FaultStatisticsDto f = new FaultStatisticsDto(deviceNum, 0,0,0);
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
            List<Long> deviceTypeList = deviceTypeMapper.getDeviceTypeBySystemType(systemType);
            if(!CollectionUtils.isEmpty(deviceTypeList)) {
                List<TpsonDeviceFaultEntity> faultList = tpsonDeviceFaultMapper.getFaultByTime(deviceTypeList, start, end);
                if(!CollectionUtils.isEmpty(faultList)){
                    //TODO: 往map里塞数据


                }
                op.setDataValue(rtnList);
            }
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
        List<TpsonDeviceFaultEntity> faultList = new ArrayList<>();
        Map rtnMap = new HashMap();

        try{
            List<Long> deviceTypeList = deviceTypeMapper.getDeviceTypeBySystemType(systemType);
            if(!CollectionUtils.isEmpty(deviceTypeList)) {
                total = tpsonDeviceFaultMapper.getFaultCountBySearch(deviceTypeList,
                        faultType, status, isOutdoor, deviceCode, startTime, endTime);
                if(total > 0) {
                    faultList = tpsonDeviceFaultMapper.getFaultBySearch(deviceTypeList,
                            faultType, status, isOutdoor, deviceCode, startTime, endTime, currentPage, length);
                }
            }
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




        }catch (Exception e) {
            log.error("getFaultDetailById ERROR! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }




        return null;
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
                cstart.add(Calendar.DAY_OF_MONTH,1);
                rtnList.add(map);
            }
            //Long diffDays = (cend.getTimeInMillis() - cstart.getTimeInMillis()) / (1000 * 60 * 60 * 24);

        }catch (Exception e) {
            log.error("initFaultResultList error! e={}", e);
            return null;
        }
        return rtnList;
    }

}
