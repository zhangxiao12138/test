package com.firecontrol.service;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.firecontrol.common.SpringBeanUtil;
import com.firecontrol.common.TBConstants;
import com.firecontrol.common.WebSocketService;
import com.firecontrol.domain.dto.SMR3002Dto;
import com.firecontrol.domain.dto.WebSocketMsg;
import com.firecontrol.domain.dto.WsAlarmPush;
import com.firecontrol.domain.entity.ElectricConfig;
import com.firecontrol.domain.entity.Floor;
import com.firecontrol.domain.entity.TpsonAlarmEntity;
import com.firecontrol.domain.entity.TpsonDeviceEntity;
import com.firecontrol.mapper.iotmapper.TpsonAlarmMapper;
import com.firecontrol.mapper.iotmapper.TpsonDeviceMapper;
import com.firecontrol.mapper.ydmapper.FloorMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;

/**
 * Created by mariry on 2019/12/24.
 */
public class TpsonSMR3002AlarmService implements Runnable{

//    @Autowired
//    private TpsonAlarmMapper tpsonAlarmMapper;
//    @Autowired
//    private FloorMapper floorMapper;
//    @Autowired
//    private WebSocketService webSocketService;
//    @Autowired
//    private TpsonDeviceService tpsonDeviceService;
//    @Autowired
//    private TpsonDeviceMapper deviceMapper;

    private TpsonAlarmMapper tpsonAlarmMapper;
    private FloorMapper floorMapper;
    private WebSocketService webSocketService;
    private TpsonDeviceService tpsonDeviceService;
    private TpsonDeviceMapper deviceMapper;


    private ElectricConfig config;
    private SMR3002Dto dto;
    private String deviceCode;

    public ElectricConfig getConfig() {
        return config;
    }

    public void setConfig(ElectricConfig config) {
        this.config = config;
    }

    public SMR3002Dto getDto() {
        return dto;
    }

    public void setDto(SMR3002Dto dto) {
        this.dto = dto;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public TpsonSMR3002AlarmService() {
    }

    public TpsonSMR3002AlarmService(ElectricConfig config, SMR3002Dto dto, String deviceCode) {
        this.config = config;
        this.deviceCode = deviceCode;
        this.dto = dto;
    }

    @Override
    public void run() {

        try{
            //注入诸个mapper Bean
            deviceMapper= SpringBeanUtil.getBean(TpsonDeviceMapper.class);
            tpsonAlarmMapper = SpringBeanUtil.getBean(TpsonAlarmMapper.class);
            floorMapper = SpringBeanUtil.getBean(FloorMapper.class);
            tpsonDeviceService = SpringBeanUtil.getBean(TpsonDeviceService.class);
            webSocketService = SpringBeanUtil.getBean(WebSocketService.class);


            //TODO：主机功率过载,添加报警信息，websocket 推送，判断是否需要断电
            insertOrUpdateAlarm(dto, deviceCode);
            if(config.getDeviceOverloadIsOutage() == true) {
                tpsonDeviceService.setRelay(deviceCode, 0);
            }

        }catch (Exception e) {
            throw new RuntimeException("TpsonSMR3002AlarmService thread error! e={}"+ e);
        }
    }

    private void insertOrUpdateAlarm(SMR3002Dto dto, String deviceCode) {
        //1. 有未处理的同设备同类型报警，则不增加新纪录，在原有的记录上，count 加1
        //2. 没有未处理的，则新建记录

        //获取device信息
        System.out.println("here");

        TpsonDeviceEntity device = deviceMapper.selectByDeviceCode(deviceCode);

        //查询是否有已存在的同设备同类型未处理报警
        Boolean rtn = true;
        try{
            TpsonAlarmEntity alarm = tpsonAlarmMapper.getUnhandleAlarmByDeviceInfo(dto.getDeviceCode(), dto.getAlarmType());
            if(alarm != null){
                tpsonAlarmMapper.updateAlarmCountById(alarm.getId());

                //websocket 推送页面
                WsAlarmPush wAlarm = new WsAlarmPush();
                BeanUtils.copyProperties(alarm, wAlarm);
                wAlarm.setMsgType(1);
                WebSocketMsg wsmsg = new WebSocketMsg();
                wsmsg.setMsgType(1);
                wsmsg.setShowWindow(1);
                wsmsg.setData(wAlarm);
                webSocketService.sendInfo(JSON.toJSONString(wsmsg), null);

            }else{
                //新增记录
                //通用报警
                Floor floor = null;
                alarm = new TpsonAlarmEntity();
                alarm.setAddTime(dto.getTime());
                alarm.setType(dto.getAlarmType());//主机功率过载报警，type =22
                alarm.setLevel(1);//hardcode， 默认1:低
                alarm.setDeviceCode(dto.getDeviceCode());
                alarm.setDeviceId(device.getId());
                alarm.setDeviceName(device.getName());
                alarm.setDeviceType(device.getType());
                alarm.setDeviceVersion(device.getVersion());
                alarm.setIsOutdoor(device.getIsOutdoor());
                alarm.setIsReview(false);

                //TODO:设置传感器id
                // alarm.setSensorId();
                alarm.setSensorName(device.getName() + "传感器");
                alarm.setStatus((byte)0);


                //获取设备地址信息
                if(!StringUtils.isEmpty(device.getFloorId())){
                    floor = floorMapper.selectById(device.getFloorId());
                    if(floor == null){
                        floor = new Floor();
                        floor.setName("");
                    }
                }

                alarm.setDetail(floor.getName() + dto.getAlarmName());
                alarm.setFloorName(floor.getName());
                alarm.setPosition(floor.getName());
                alarm.setSiteName(floor.getName());
                alarm.setIsPending(false);
                alarm.setCameraId(device.getCameraId());
                alarm.setCount(1);
                alarm.setRecoverTime(0);
                alarm.setIsDelete(false);
                //TODO:全部building_id由long改为string
                alarm.setBuildingId(device.getBuildingId());
                tpsonAlarmMapper.insert(alarm);


                //websocket 推送页面
                WsAlarmPush wAlarm = new WsAlarmPush();
                BeanUtils.copyProperties(alarm, wAlarm);
                wAlarm.setMsgType(1);
                WebSocketMsg wsmsg = new WebSocketMsg();
                wsmsg.setMsgType(1);
                wsmsg.setShowWindow(1);
                wsmsg.setData(wAlarm);

                webSocketService.sendInfo(JSON.toJSONString(wsmsg), null);

            }


        }catch (Exception e) {
            throw new RuntimeException("TpsonSMR3002AlarmService thread error! e={}", e);
        }

    }
}
