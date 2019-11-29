package com.firecontrol.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.firecontrol.common.OpResult;
import com.firecontrol.common.RunningStateCount;
import com.firecontrol.common.TBConstants;
import com.firecontrol.domain.dto.DeviceSearch;
import com.firecontrol.domain.dto.DeviceStatMap;
import com.firecontrol.domain.dto.FaultCountDto;
import com.firecontrol.domain.entity.*;
import com.firecontrol.mapper.iotmapper.*;
import com.firecontrol.service.TpsonDeviceService;
import com.firecontrol.service.TpsonSensorService;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    @Autowired
    private TpsonDeviceTypeMapper deviceTypeMapper;
    @Autowired
    private ElectricLogMapper electricLogMapper;




    @Transactional
    @Override
    public OpResult insertDevice(TpsonDeviceEntity device) {
        OpResult opResult = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        try{
            if(device.getType() == null){
                opResult.setStatus(OpResult.OP_FAILED);
                opResult.setMessage("新增设备类型不可为空type!");
                return opResult;
            }

            //初始化不可为空属性值
            device.setOnline(false);
            device.setStatus(new Byte((byte)(0)));
            device.setRunningState(new Byte((byte)(0)));
            device.setStyle(0);
            device.setEnableCall(false);
            device.setEnableSms(false);
            device.setFaultStatus(new Long(0));
            device.setIsOutdoor(false);


            String opError = null;
            switch (device.getType().intValue()) {
                case TBConstants.DEVICE_TYPE.YG:
                    //烟感
                    createYG(device);
                    break;
                case TBConstants.DEVICE_TYPE.YD:
                    //用电
                    createElectrical(device);
                    break;
                case TBConstants.DEVICE_TYPE.XFS:
                    //消防栓主机
                    break;
                case TBConstants.DEVICE_TYPE.YS:
                    //消防用水主机
                    break;
                case TBConstants.DEVICE_TYPE.ZNSBX:
                    //智能设备箱监测主机
                case TBConstants.DEVICE_TYPE.YC:
                    //用传
                    break;
                case TBConstants.DEVICE_TYPE.SXDQ:
                    //三相电气监测主机
                    break;
                case TBConstants.DEVICE_TYPE.WXSD:
                    //无线手动报警主机
                    break;
                default: opError = "参数不合法";
            }
            return opResult;

        }catch (Exception e){
            log.info("insertDevice error! e={}", e);
            opResult.setStatus(OpResult.OP_FAILED);
            opResult.setMessage(OpResult.OpMsg.OP_FAIL);
        }
        return opResult;
    }

    //新增烟感及传感器
    private String createYG(TpsonDeviceEntity device){
        device.setType(new Long(7));
        tpsonDeviceMapper.insert(device);
        insertTempSensor(device);
        insertFlogSensor(device);
        insertUnpackSensor(device);

        //TODO: transaction
        return "success";
    }

    //新增用电及传感器
    private String createElectrical(TpsonDeviceEntity device){
        device.setType((long)TBConstants.DEVICE_TYPE.YD);
        tpsonDeviceMapper.insert(device);
        //添加4个温度传感器
        insertETempSensor(device);
        //添加剩余电流传感器
        insertASensor(device);
        //添加故障电弧传感器
        insertGZDHSensor(device);

        //TODO: transaction
        return "success";
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
    public OpResult getDeviceList(DeviceSearch device) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        Map result = new HashMap();
        List<TpsonDeviceEntity> list = new ArrayList<>();
        try{
                list = tpsonDeviceMapper.getDeviceListBySearch(device);
                result.put("deviceList", list);
                op.setDataValue(result);


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
    public OpResult changeDeviceStatus(String ids, Integer status) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        try{
            if(StringUtils.isEmpty(ids) || status == null){
                op.setMessage("ids或者status不可为空!");
                op.setStatus(OpResult.OP_FAILED);
                return op;
            }
            tpsonDeviceMapper.changeDeviceStatus(Arrays.asList(ids.split(",")), status);
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
            op.setDataValue(device);

        }catch (Exception e){
            log.error("getDeviceByCode ERROR! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }
        return op;

    }

    @Override
    public OpResult getDeviceStatusStatic(Integer systemType) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        List<RunningStateCount> rtnList = new ArrayList<>();
        List<RunningStateCount> finalList = null;
        try{
            finalList = initFinalList();
            List<Long> deviceTypeList = null;
            if(systemType != 0) {
                deviceTypeList = deviceTypeMapper.getDeviceTypeBySystemType(systemType);
            }
//            if(!CollectionUtils.isEmpty(deviceTypeList)) {
                rtnList = tpsonDeviceMapper.getRunningStateDistByDeviceType(deviceTypeList);
//            }

            //补充缺少的状态
            for(RunningStateCount f : finalList){
                for(RunningStateCount r : rtnList){
                    if(f.getRunningState() == r.getRunningState()) {
                        f.setStateCount(r.getStateCount());
                        continue;
                    }
                }
            }
            op.setDataValue(finalList);

        }catch (Exception e){
            log.error("getDeviceByCode ERROR! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }
        return op;
    }



    @Override
    public OpResult offLineStat(Integer systemType, Long companyId) {

        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        Map rtnMap = new HashMap();
        Integer offLineCount = 0;
        Integer total = 0;
        try{
            List<Long> deviceTypeList = null;
            if(systemType != 0) {
                deviceTypeList = deviceTypeMapper.getDeviceTypeBySystemType(systemType);
            }

//            if(!CollectionUtils.isEmpty(deviceTypeList)) {
                offLineCount = tpsonDeviceMapper.getOfflineDeviceCountByDeviceType(deviceTypeList);
                total = tpsonDeviceMapper.getTotalBySystemType(deviceTypeList);
//            }
            rtnMap.put("offLine", offLineCount);
            rtnMap.put("total", total);
            rtnMap.put("systemType", systemType);
            op.setDataValue(rtnMap);

        }catch (Exception e){
            log.error("getDeviceByCode ERROR! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }
        return op;
    }

    @Override
    public OpResult deviceLog(String deviceCode, String logData, Integer startTime, Integer endTime, Integer currentPage, Integer length) {
        //临时写死，后续根据业务逻辑修改
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);

        Map rtnMap = new HashMap();
        rtnMap.put("total", 1);

        List<Map> rtnList = new ArrayList<>();
        Map tmp = new HashMap();
        tmp.put("deviceCode", deviceCode);
        tmp.put("deviceName","临时");
        tmp.put("id", 111);
        tmp.put("logData","一些日志内容");
        tmp.put("logTime", (int) (System.currentTimeMillis() / 1000));
//        device_code: "869029034554278"
//        device_id: 67
//        device_name: "测试1"
//        device_type: 7
//        device_type_name: "无线烟感监测主机"
//        id: 788
//        log_data: "报警记录删除"
//        log_time: 1570846625
//        log_type: 2
//        name: "凤凰城25号楼二单元八楼楼道东侧"
        rtnList.add(tmp);
        rtnMap.put("list", rtnList);
        op.setDataValue(rtnMap);

        return op;
    }

    /**
     * 首页获取设备状态统计数据
     * @param companyId
     * @return
     */
    @Override
    public OpResult deviceStat(Long companyId) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        Map rtnMap = new HashMap();
        List<DeviceStatMap> rtnList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        //running_state: 0未激活，1离线，2正常，3故障，4报警，5禁用',
        //14中类型的主机，循环查询
        Integer type = 0;
        try{
            for(type = 1; type< 14; type++) {
                DeviceStatMap dmap = new DeviceStatMap();
                dmap.setOnline(tpsonDeviceMapper.getDeviceStatInfo(type, 2, companyId));
                dmap.setOffline(tpsonDeviceMapper.getDeviceStatInfo(type, 1, companyId));
                dmap.setFault(tpsonDeviceMapper.getDeviceStatInfo(type, 3,  companyId));
                dmap.setAlarm(tpsonDeviceMapper.getDeviceStatInfo(type,4,  companyId));
                dmap.setUnActive(tpsonDeviceMapper.getDeviceStatInfo(type,0,  companyId));
                dmap.setName(deviceTypeMapper.getDeviceTypeNameByType(type));
                rtnList.add(dmap);
                nameList.add(dmap.getName());
            }
            rtnMap.put("systemName", nameList);
            rtnMap.put("dataList", rtnList);
            op.setDataValue(rtnMap);
        }catch (Exception e){
            log.error("deviceStat ERROR! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }
        return op;
    }

    @Override
    public OpResult getElectricDiagram(Integer companyId, Long start, Long end, String deviceCode) {

        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        List<Map> rtnList = null;
        List<String> timeList = new ArrayList<>();
        List<Double> dataList = new ArrayList<>();
        Map rtnMap = new HashMap();


        log.info("getElectricDiagram: campanyId:" + companyId + ", startTime:" + start + "endTime:" + end);

        if(start == null){
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage("startTime不可为空!");
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

            for(Map m : rtnList){
                timeList.add((String)m.get("time"));
                dataList.add(((Integer)m.get("count")).doubleValue());
            }

            rtnMap.put("timeList", timeList);
            rtnMap.put("dataList", dataList);



            //获取起止时间之内的所有报警记录
            //List<Long> deviceTypeList = null;







            //if(!CollectionUtils.isEmpty(deviceTypeList)) {
//            List<TpsonAlarmEntity> alarmList = tpsonAlarmMapper.getAlarmByTime(deviceTypeList, start, end);
//            if(!CollectionUtils.isEmpty(alarmList)){
//                //往list里塞数据
//                for(Map m : rtnList){
//                    Integer tempCount = 0;
//                    for(TpsonAlarmEntity alarm : alarmList) {
//                        if(alarm.getAddTime() > (Long)m.get("start") && alarm.getAddTime() < (Long)m.get("end")){
//                            tempCount ++;
//                        }
//                    }
//                    m.put("count", tempCount);
//                }
//            }
            op.setDataValue(rtnMap);
            // }
        }catch (Exception e) {
            log.error("getElectricDiagram ERROR! exception: {}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }
        return op;

    }

    @Override
    public OpResult getElectricDeviceStatistic(DeviceSearch search) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);

        Map rtnMap = new HashMap();
        if(search.getIsOutdoor() == null){
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage("是否室外选项为必选项！");
            return op;
        }

        try{

            search.setDeviceType(3);

            Integer all=0;
            Integer access=0;
            Integer online=0;
            Double totalPower = 0D;


            List<TpsonDeviceEntity> list = tpsonDeviceMapper.getDeviceListBySearch(search);
            for(TpsonDeviceEntity device : list){
                all ++;
                if(device.getRunningState() == TBConstants.RunningState.online){
                    online ++;
                }
            }

            rtnMap.put("allDeviceAmount", all);
            rtnMap.put("electricAccessAmount", access);
            rtnMap.put("onlineDeviceAmount", online);
            rtnMap.put("totalPower", totalPower);

            op.setDataValue(rtnMap);

        }catch (Exception e) {
            log.error("getElectricDeviceStatistic error! e = {}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }

        return op;
    }

    @Override
    public OpResult realTimeWatch(DeviceSearch search) {

        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        Map rtnMap = new HashMap();

        try{
            if(search.getCurrentPage() == null){
                search.setCurrentPage(1);
            }
            if(search.getLength() == null) {
                search.setLength(15);
            }
            search.setDeviceType(3);

            List<TpsonDeviceEntity> list = tpsonDeviceMapper.getDeviceListBySearch(search);
            for(TpsonDeviceEntity device : list){



            }
            //TODO:


            op.setDataValue(rtnMap);

        }catch (Exception e) {
            log.error("getElectricDeviceStatistic error! e = {}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }

        return op;



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

    //添加故障电弧传感器
    private boolean insertGZDHSensor(TpsonDeviceEntity device){
        boolean b = true;

        TpsonSensorEntity sensor = new TpsonSensorEntity();
        sensor.setCode("6");
        sensor.setSensorType(new Long(15));
        sensor.setSensorTypeName(formatSensorTypeName(sensor.getSensorType()));
        sensor.setName(device.getName() + sensor.getSensorTypeName() + sensor.getCode());
        sensor.setIsOutdoor(device.getIsOutdoor());
        sensor.setDeviceCode(device.getCode());
        sensor.setFaultStatus(new Byte((byte)1));
        sensor.setOnline(false);
        sensor.setDeviceType(device.getType());
        sensor.setStatus(new Byte((byte)0));
        sensor.setAlarmLevel(new Byte((byte)1));
        sensor.setCompanyId(device.getCompanyId());
        sensor.setPosX(device.getPosX());
        sensor.setPosY(device.getPosY());
        sensor.setLimitUp("14");
        sensor.setPreLimitUp("100");
        sensor.setChannelSwitch(true);
        sensor.setWarningSwitch(true);
        sensor.setTriggerSwitch(true);
        sensor.setMan(device.getMan());
        sensor.setPhone(device.getPhone());
        sensor.setPosition(device.getPosition());
        tpsonSensorService.insertSensor(sensor);

        return b;
    }

    //添加剩余电流传感器
    private boolean insertASensor(TpsonDeviceEntity device){
        boolean b = true;

        TpsonSensorEntity sensor = new TpsonSensorEntity();
        sensor.setCode("5");
        sensor.setSensorType(new Long(8));
        sensor.setSensorTypeName(formatSensorTypeName(sensor.getSensorType()));
        sensor.setName(device.getName() + sensor.getSensorTypeName() + sensor.getCode());
        sensor.setIsOutdoor(device.getIsOutdoor());
        sensor.setDeviceCode(device.getCode());
        sensor.setFaultStatus(new Byte((byte)1));
        sensor.setOnline(false);
        sensor.setDeviceType(device.getType());
        sensor.setStatus(new Byte((byte)0));
        sensor.setAlarmLevel(new Byte((byte)1));
        sensor.setCompanyId(device.getCompanyId());
        sensor.setPosX(device.getPosX());
        sensor.setPosY(device.getPosY());
        sensor.setLimitUp("500");
        sensor.setPreLimitUp("80");
        sensor.setChannelSwitch(true);
        sensor.setWarningSwitch(true);
        sensor.setTriggerSwitch(true);
        sensor.setMan(device.getMan());
        sensor.setPhone(device.getPhone());
        sensor.setPosition(device.getPosition());
        tpsonSensorService.insertSensor(sensor);

        return b;
    }

    //新增用电设备温度传感器
    private void insertETempSensor(TpsonDeviceEntity device) {
        TpsonSensorEntity sensor = new TpsonSensorEntity();
        sensor.setCode("1");
        sensor.setSensorType(new Long(7));
        sensor.setSensorTypeName(formatSensorTypeName(sensor.getSensorType()));
        sensor.setName(device.getName() + sensor.getSensorTypeName() + sensor.getCode());
        sensor.setIsOutdoor(device.getIsOutdoor());
        sensor.setDeviceCode(device.getCode());
        sensor.setFaultStatus(new Byte((byte)1));
        sensor.setOnline(false);
        sensor.setDeviceType(device.getType());
        sensor.setStatus(new Byte((byte)0));
        sensor.setAlarmLevel(new Byte((byte)1));
        sensor.setCompanyId(device.getCompanyId());
        sensor.setPosX(device.getPosX());
        sensor.setPosY(device.getPosY());
        sensor.setLimitUp("100");
        sensor.setPreLimitUp("80");
        sensor.setChannelSwitch(true);
        sensor.setWarningSwitch(true);
        sensor.setTriggerSwitch(true);
        sensor.setMan(device.getMan());
        sensor.setPhone(device.getPhone());
        sensor.setPosition(device.getPosition());
        tpsonSensorService.insertSensor(sensor);

        sensor.setCode("2");
        tpsonSensorService.insertSensor(sensor);
        sensor.setCode("3");
        tpsonSensorService.insertSensor(sensor);
        sensor.setCode("4");
        tpsonSensorService.insertSensor(sensor);
    }

    private void insertTempSensor(TpsonDeviceEntity device) {
        log.info("insertTempSensor, device={}", device);
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
        }else if(type == 7) {
            rtn = "温度传感器";
        }else if(type == 8) {
            rtn = "剩余电流传感器";
        }else if(type == 15) {
            rtn = "故障电弧传感器";
        }
        return rtn;
    }

    private List<RunningStateCount> initFinalList() {
        List<RunningStateCount> list = new ArrayList<>();
        list.add(new RunningStateCount(0, 0));
        list.add(new RunningStateCount(1, 0));
        list.add(new RunningStateCount(2, 0));
        list.add(new RunningStateCount(3, 0));
        list.add(new RunningStateCount(4, 0));
        list.add(new RunningStateCount(5, 0));

        return list;
    }


}
