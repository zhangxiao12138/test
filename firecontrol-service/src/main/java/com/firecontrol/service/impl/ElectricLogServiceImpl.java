package com.firecontrol.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.firecontrol.common.HttpRequestUtil;
import com.firecontrol.common.OpResult;
import com.firecontrol.domain.dto.ElectricAccess;
import com.firecontrol.domain.dto.ElectricPossible;
import com.firecontrol.domain.entity.ElectricLog;
import com.firecontrol.domain.entity.ElectricType;
import com.firecontrol.domain.entity.Response;
import com.firecontrol.mapper.iotmapper.ElectricLogMapper;
import com.firecontrol.mapper.iotmapper.ElectricTypeMapper;
import com.firecontrol.service.ElectricLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.management.relation.RelationTypeNotFoundException;
import java.util.*;

/**
 * Created by mariry on 2019/11/26.
 */
@Service
public class ElectricLogServiceImpl implements ElectricLogService{

    private static final Logger log = LoggerFactory.getLogger(ElectricLogServiceImpl.class);

    @Autowired
    private ElectricLogMapper electricLogMapper;
    @Autowired
    private ElectricTypeMapper electricTypeMapper;
    @Autowired
    private HttpRequestUtil httpRequestUtil;

    @Value("${tpson.ip}")
    private String tosonIP;

    @Value("${tpson.learn}")
    private String learnUrl;

    @Value("${tpson.getToken}")
    private String getToken;


    @Override
    public OpResult learn(Long id, Long type) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);

        try{
            if(id == null || type == null){
                op.setStatus(OpResult.OP_FAILED);
                op.setMessage("id 和 电器种类type均不可为空!");
                return op;
            }
            ElectricLog log = electricLogMapper.getById(id);
            if(log.getAction() != 1){
                //只有插入的动作才能学习，即action=1
                op.setStatus(OpResult.OP_FAILED);
                op.setMessage("只能识别[插入]动作的电器!");
                return op;
            }

            electricLogMapper.updateAllTypeByOldType(type, log.getType().longValue());


            //反向推送拓深学习结果
            //1. 获取token
            Map param = new HashMap();
            String token = httpRequestUtil.getTpsonToken(tosonIP + getToken, "000002");
            //2. 推送
            Map p = new HashMap();
            p.put("deviceCode", log.getDeviceCode());
            p.put("uuid", log.getUuid());//给拓深的参数：false:开电闸  true:关电闸
            p.put("newElecName", electricTypeMapper.getNameByType(type));
            p.put("syncToCommon", false);
            Response t = httpRequestUtil.sendPostRequest(tosonIP + learnUrl, p, token);

        }catch (Exception e) {
            log.error("learn ERROR! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }

        return op;
    }

    @Override
    public OpResult accessStatistic(Integer isOutdoor, Long deviceId, String deviceCode, Long companyId) {

        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        Map rtnMap = new HashMap();
        List<ElectricAccess> list;
        List<Integer> powerTypeList = new ArrayList<>();
        List<Integer> accessAmount = new ArrayList<>();

        //获取本月第一天时间
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        Long startTime = cal.getTimeInMillis()/1000;

        try{
            if(StringUtils.isEmpty(deviceCode)){
                deviceCode = null;
            }
            list = electricLogMapper.getPowerAccessCount(startTime,isOutdoor,companyId,deviceId,deviceCode);
            if(!CollectionUtils.isEmpty(list)) {
                for(Integer k=0; k <= 6; k=k+2){
                    powerTypeList.add(k);
                    Boolean tag = false;
                    for(ElectricAccess ea : list){
                        if(ea.getPowerType() == k){
                            accessAmount.add(ea.getAmount());
                            tag = true;
                            break;
                        }
                    }
                    if(!tag) {
                        accessAmount.add(0);
                    }
                }
            }else{
                for(Integer k = 0; k<= 6; k+=2) {
                    powerTypeList.add(k);
                    accessAmount.add(0);
                }
            }

            rtnMap.put("powerType", powerTypeList);
            rtnMap.put("access", accessAmount);

            op.setDataValue(rtnMap);
        }catch (Exception e) {
            log.error("getLog error! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }
        return op;
    }

    @Override
    public OpResult getLog(ElectricLog search) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        List<ElectricLog> list = new ArrayList<>();

        try{
            if(search.getCurrentPage() == null){
                search.setCurrentPage(1);
            }
            if(search.getLength() == null){
                search.setLength(15);
            }
            Map rtnMap = new HashMap();
            Map elecNameMap = new HashMap();

            Integer total = electricLogMapper.getCountBySearch(search);
            if(total <= 0) {
                rtnMap.put("total", 0);
                rtnMap.put("deviceList", list);
                op.setDataValue(rtnMap);
                return op;
            }else {
                rtnMap.put("total", total);
                search.setCurrentPage(search.getCurrentPage() - 1);
                list = electricLogMapper.getListBySearch(search);
                list.stream().forEach(l -> {
                    l.setActionName(fmtActionName(l.getAction()));
                    if(!StringUtils.isEmpty(l.getPossibleStr())){
                        List<ElectricPossible> pList = JSON.parseArray(l.getPossibleStr(), ElectricPossible.class);
                        l.setPossible(pList);
                    }else{
                        l.setPossible(new ArrayList<>());
                    }
                    //处理eleName
                    //TODO: 不要循环查库
                    if(l.getType() != null){
                        if(elecNameMap.containsKey(l.getType())){
                            l.setElecName((String)elecNameMap.get(l.getType()));
                        }else{
                            elecNameMap.put(l.getType(), electricTypeMapper.getNameByType(l.getType().longValue()));
                            l.setElecName((String)elecNameMap.get(l.getType()));
                        }
                    }
                });
                rtnMap.put("deviceList", list);
            }
            op.setDataValue(rtnMap);
        }catch (Exception e) {
            log.error("accessStatistic error! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }
        return op;
    }


    private String fmtActionName(Integer action){
        if(action == 1){
            return "接入";
        }else if(action == 0) {
            return "无动作";
        }else if(action == -1) {
            return "拔出";
        }else if(action == 2) {
            return "换挡或模式变化";
        }else{
            return "未知操作";
        }
    }
}
