package com.firecontrol.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.firecontrol.common.OpResult;
import com.firecontrol.domain.dto.DeviceSearch;
import com.firecontrol.domain.dto.FaultCountDto;
import com.firecontrol.domain.entity.*;
import com.firecontrol.mapper.CameraMapper;
import com.firecontrol.mapper.TpsonDeviceMapper;
import com.firecontrol.mapper.TpsonDeviceVersionMapper;
import com.firecontrol.mapper.TpsonSensorMapper;
import com.firecontrol.service.CameraService;
import com.firecontrol.service.TpsonDeviceService;
import com.firecontrol.service.TpsonSensorService;
import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by mariry on 2019/9/30.
 */
@Service
public class TpsonDeviceServiceImpl implements TpsonDeviceService {

    private static final Logger log = LoggerFactory.getLogger(TpsonDeviceService.class);

    @Autowired
    private TpsonDeviceMapper tpsonDeviceMapper;
    @Autowired
    private TpsonSensorService tpsonSensorService;
    @Autowired
    private TpsonDeviceVersionMapper tpsonDeviceVersionMapper;
    @Autowired
    private CameraMapper cameraMapper;


    @Transactional
    @Override
    public OpResult insertDevice(TpsonDeviceEntity device) {
        OpResult opResult = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        try{
            //初始化不可为空属性值
            device.setOnline(false);
            device.setStatus(new Byte((byte)(0)));
            device.setRunningState(new Byte((byte)(0)));
            device.setStyle(0);
            device.setEnableCall(false);
            device.setEnableSms(false);
            device.setFaultStatus(new Long(0));
            device.setIsOutdoor(false);

            //TODO:类型落库查询
            //烟感deviceType=7
            device.setType(new Long(7));
            tpsonDeviceMapper.insert(device);
            insertTempSensor(device);
            insertFlogSensor(device);
            insertUnpackSensor(device);
            return opResult;

        }catch (Exception e){
            log.info("insertDevice error! e={}", e);
            opResult.setStatus(OpResult.OP_FAILED);
            opResult.setMessage(OpResult.OpMsg.OP_FAIL);
        }
        return opResult;
    }

    @Override
    public OpResult updateDeviceInfo(TpsonDeviceEntity device) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        try{
            if(device.getId() == null) {
                op.setMessage("设备id不可为空!");
                op.setStatus(OpResult.OP_FAILED);
                return op;
            }
            tpsonDeviceMapper.updateById(device);

        }catch (Exception e) {
            log.info("updateDeviceInfo error! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
        }
        return op;
    }

    @Override
    public OpResult getDeviceByPage(DeviceSearch device) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        Map result = new HashMap();
        List<TpsonDeviceEntity> list = new ArrayList<>();
        try{
            if(device.getCurrentPage() == null){
                device.setCurrentPage(1);
            }
            if(device.getLength() == null){
                device.setLength(15);
            }

            Integer total = tpsonDeviceMapper.getTotal(device);

            if(total <= 0) {
                result.put("total", 0);
                result.put("deviceList", list);
                op.setDataValue(result);
                return op;
            }else{
                result.put("total", total);
                device.setCurrentPage(device.getCurrentPage() -1);
                list = tpsonDeviceMapper.getDeviceListBySearch(device);
                if(!CollectionUtils.isEmpty(list)){
//                    List.stream().forEach(sku -> {
//                        Sku ss = new StoreSku();
//                        BeanUtils.copyProperties(sku, ss);
//                        rtnList.add(ss);
//                    });
                    //给每个设备补充摄像头信息
                    list.stream()
                            .filter(d ->{return !StringUtils.isEmpty(d.getCameraId());})
                            .forEach(d -> {
                                List<CameraEntity> cameraList = new ArrayList<>();
                                cameraList = cameraMapper.getCameraListByIds(Arrays.asList(d.getCameraId().split(",")));
                                d.setCameras(cameraList);
                            });
                    log.info("deviceList: " + JSON.toJSONString(list));
                }
                result.put("deviceList", list);
                op.setDataValue(result);
            }

        }catch (Exception e){
            log.info("TpsonDeviceServiceImpl.getDeviceByPage error! param ={}, e={}", device, e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }

        return op;
    }

    @Override
    public OpResult getDeviceById(Long id) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        try{
            TpsonDeviceEntity device = tpsonDeviceMapper.selectById(id);
            op.setDataValue(device);
        }catch (Exception e) {
            log.info("TpsonDeviceServiceImpl.getDeviceById error! id ={}, e={}", id, e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }
        return op;
    }

    @Override
    public OpResult changeDeviceStatus(Long id, Integer status) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        try{
            tpsonDeviceMapper.changeDeviceStatus(id, status);
        }catch (Exception e) {
            log.info("TpsonDeviceServiceImpl.changeDeviceStatus error! e={}",e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }
        return op;
    }

    @Override
    @Transactional
    public OpResult deleteDeviceByIds(String ids) {

        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        try{
            if(StringUtils.isEmpty(ids)){
                return op;
            }
            List idList = Arrays.asList(ids.split(","));
            List<String> deviceCodeList = tpsonDeviceMapper.getDeviceCodeByIds(idList);

            tpsonDeviceMapper.deleteDevice(idList);
            //删除关联传感器
            if(!CollectionUtils.isEmpty(deviceCodeList)) {
                tpsonSensorService.deleteSensorByDevice(deviceCodeList);
            }

        }catch (Exception e) {
            log.error("deleteDeviceByIds,ids ={}, e = {}", ids, e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }
        return op;
    }

    @Override
    public OpResult faultCount(Long companyId) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        //TODO:目前没有数据，返回假数据；接入设备后完善
        List<FaultCountDto> list = new ArrayList<>();
        list.add(new FaultCountDto(7,7,1));
        list.add(new FaultCountDto(6,6,0));
        list.add(new FaultCountDto(1,1,0));
        list.add(new FaultCountDto(14,14,3));
        op.setDataValue(list);
        return op;
    }

    @Override
    public OpResult getDeviceTypeList() {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);

        //TODO: 先写死，稍后添加数据库

        List<TpsonDeviceType> list = new ArrayList<>();
        list.add(new TpsonDeviceType(1 , "用户信息传输装置",	 1	));
        list.add(new TpsonDeviceType(2 , "消防用水主机", 	     2  ));
        list.add(new TpsonDeviceType(3 , "电气监测主机", 	     3  ));
        list.add(new TpsonDeviceType(4 , "消防栓主机", 	     4  ));
        list.add(new TpsonDeviceType(5 , "动态监测主机", 	     5  ));
        list.add(new TpsonDeviceType(6 , "物联中继主机",	     1  ));
        list.add(new TpsonDeviceType(7 , "无线烟感监测主机",   1  ));
        list.add(new TpsonDeviceType(8 , "无线燃气监测主机",   8  ));
        list.add(new TpsonDeviceType(9 , "窨井动态监测主机",   9  ));
        list.add(new TpsonDeviceType(10, "窨井环境监测主机",   9  ));
        list.add(new TpsonDeviceType(11, "多通道电气监测主机", 3  ));
        list.add(new TpsonDeviceType(12, "三相电气监测主机",   3  ));
        list.add(new TpsonDeviceType(13, "智能设备箱监测主机", 10 ));
        list.add(new TpsonDeviceType(14, "无线手动报警主机",   1  ));

        op.setDataValue(list);

        return op;
    }

    @Override
    public OpResult getDeviceVersionList(Long deviceType) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        List<TpsonDeviceVersion> list = tpsonDeviceVersionMapper.selectByDeviceType(deviceType);
        op.setDataValue(list);

        return op;
    }

    @Override
    public OpResult getDeviceByCode(String deviceCode) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        try{
            TpsonDeviceEntity device = tpsonDeviceMapper.selectByDeviceCode(deviceCode);
            op.setDataValue(deviceCode);

        }catch (Exception e){
            log.error("getDeviceByCode ERROR! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }


        return op;

    }


    private void insertTempSensor(TpsonDeviceEntity device) {
        TpsonSensorEntity sensor = new TpsonSensorEntity();
        sensor.setCode("000001");
        sensor.setSensorType(new Long(25));
        sensor.setSensorTypeName(formatSensorTypeName(sensor.getSensorType()));
        sensor.setName(device.getName() + sensor.getSensorTypeName() + sensor.getCode());
        sensor.setIsOutdoor(device.getIsOutdoor());
        sensor.setDeviceCode(device.getCode());
        sensor.setFaultStatus(new Byte((byte)0));
        sensor.setOnline(false);
        sensor.setDeviceType(device.getType());
        sensor.setStatus(new Byte((byte)0));
        sensor.setAlarmLevel(new Byte((byte)1));
        tpsonSensorService.insertSensor(sensor);
    }

    private void insertFlogSensor(TpsonDeviceEntity device) {
        TpsonSensorEntity sensor = new TpsonSensorEntity();
        sensor.setCode("000003");
        sensor.setSensorType(new Long(24));
        sensor.setSensorTypeName(formatSensorTypeName(sensor.getSensorType()));
        sensor.setName(device.getName() + sensor.getSensorTypeName() + sensor.getCode());

        sensor.setIsOutdoor(device.getIsOutdoor());
        sensor.setDeviceCode(device.getCode());
        sensor.setFaultStatus(new Byte((byte)0));
        sensor.setOnline(false);
        sensor.setDeviceType(device.getType());
        sensor.setStatus(new Byte((byte)0));
        sensor.setAlarmLevel(new Byte((byte)1));
        tpsonSensorService.insertSensor(sensor);
    }

    private void insertUnpackSensor(TpsonDeviceEntity device) {
        TpsonSensorEntity sensor = new TpsonSensorEntity();
        sensor.setCode("0");
        sensor.setSensorType(new Long(32));
        sensor.setSensorTypeName(formatSensorTypeName(sensor.getSensorType()));
        sensor.setName(device.getName() + sensor.getSensorTypeName() + sensor.getCode());

        sensor.setIsOutdoor(device.getIsOutdoor());
        sensor.setDeviceCode(device.getCode());
        sensor.setFaultStatus(new Byte((byte)0));
        sensor.setOnline(false);
        sensor.setDeviceType(device.getType());
        sensor.setStatus(new Byte((byte)0));
        sensor.setAlarmLevel(new Byte((byte)1));
        tpsonSensorService.insertSensor(sensor);
    }

    private String formatSensorTypeName(Long type) {
        //TODO: 传感器类型与名称待入库维护
        String rtn = "";
        if(type == 24){
            rtn = "NB烟雾传感器";
        }else if(type == 25){
            rtn = "NB温度传感器";
        }else if(type == 32) {
            rtn = "防拆传感器";
        }
        return rtn;
    }


}
