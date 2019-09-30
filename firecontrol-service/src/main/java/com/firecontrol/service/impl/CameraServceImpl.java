package com.firecontrol.service.impl;

import com.alibaba.fastjson.JSON;
import com.firecontrol.common.OpResult;
import com.firecontrol.domain.entity.CameraEntity;
import com.firecontrol.mapper.CameraMapper;
import com.firecontrol.service.CameraService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by mariry on 2019/8/14.
 */
@Service
public class CameraServceImpl implements CameraService {

    private static final Logger log = LoggerFactory.getLogger(CameraService.class);

    @Autowired
    private CameraMapper cameraMapper;

    @Override
    public OpResult insertCamera(CameraEntity camera) {
        OpResult opResult = new OpResult(OpResult.OP_FAILED, OpResult.OpMsg.SAVE_FAIL);
        String cameraCheckRst = "";

        if(camera == null){
            opResult.setStatus(OpResult.OP_FAILED);
            opResult.setMessage("上传实体为空!");
            return opResult;
        }
        //校验是否符合新增条件
        cameraCheckRst = validCameraInfo(camera, true);
        if(!StringUtils.isEmpty(cameraCheckRst)){
            opResult.setMessage(cameraCheckRst);
            return opResult;
        }
        cameraMapper.insert(camera);
        opResult.setStatus(OpResult.OP_SUCCESS);
        opResult.setMessage(OpResult.OpMsg.SAVE_SUCCESS);
        return opResult;
    }

    @Override
    public CameraEntity getCameraById(Long id) {
        CameraEntity camera = cameraMapper.getCameraById(id);
        return camera;
    }

    @Override
    public OpResult getCameraListByTerminalId(String terminalId) {
        OpResult opResult = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);

        List<CameraEntity> cameraList = cameraMapper.getCameraListByTerminalId(terminalId);
        opResult.setDataValue(cameraList);

        return opResult;
    }

    @Override
    public OpResult updateCamera(CameraEntity camera) {
        OpResult opResult = new OpResult(OpResult.OP_FAILED, OpResult.OpMsg.OP_FAIL);
        //TODO: 校验数值有效性
        String cameraCheckRst = "";
        cameraCheckRst = validCameraInfo(camera, false);
        if(!StringUtils.isEmpty(cameraCheckRst)){
            opResult.setMessage(cameraCheckRst);
            return opResult;
        }

        cameraMapper.update(camera);

        opResult.setStatus(OpResult.OP_SUCCESS);
        opResult.setMessage(OpResult.OpMsg.OP_SUCCESS);
        return opResult;
    }

    @Override
    public Integer getCameraIpCountByTerminalId(String ip, String terminalId) {
        Integer count = 0;
        count = cameraMapper.getCameraIpCountByTerminalId(ip, terminalId);
        return count;
    }

    @Override
    public OpResult getCameraList(CameraEntity camera, Integer currentPage, Integer length) {
        OpResult opResult = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        Map result = new HashMap<>();
        List<CameraEntity> list = null;

        Integer total = cameraMapper.getTotal(camera);

        if(total <= 0) {
            result.put("total", 0);
            opResult.setDataValue(result);
            return opResult;
        }else{
            result.put("total", total);
            if(currentPage == null || currentPage <1){
                currentPage = 1;
            }
            if(length == null){
                length = 15;
            }
            list = cameraMapper.getAllCameraList((currentPage-1)*length, length);
        }
        result.put("cameraList", JSON.toJSON(list));

        opResult.setDataValue(result);
        return opResult;
    }

    @Override
    public OpResult deleteCamera(String ids) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        try{
            if(StringUtils.isEmpty(ids)){
                return op;
            }
            List idList = Arrays.asList(ids.split(","));
            cameraMapper.deleteCamera(idList);

            return op;
        }catch (Exception e) {
            log.error("deleteCamera,ids ={}, e = {}", ids, e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }

    }

    @Override
    public OpResult getAllCamera() {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);

        try{
            List<CameraEntity> cameraList = cameraMapper.getAllCamera();
            op.setDataValue(cameraList);
            return op;

        }catch(Exception e) {
            log.error("getAllCamera, e = {}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }
    }

    @Override
    public OpResult dupCameraName(CameraEntity c) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        Integer nameCount = 0;
        try{
            if(StringUtils.isEmpty(c.getName())){
                op.setStatus(OpResult.OP_FAILED);;
                op.setMessage("摄像头名称不可为空!");
                return op;
            }

            List<CameraEntity> dupCameraList = cameraMapper.getCameraListByName(c);
            if(CollectionUtils.isEmpty(dupCameraList)){
                //无重名，名称可用
                op.setDataValue(1);
            }else{
                //有重名，不可用
                op.setDataValue(0);
            }
            return op;
        }catch (Exception e){
            log.error("dupCameraName, e = {}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_FAIL);
            return op;
        }
    }

    private String validCameraInfo(CameraEntity camera, boolean isNew) {
        StringBuffer rtn = new StringBuffer();
        if(!isNew && StringUtils.isEmpty(camera.getId())) {
            rtn.append("/id不可为空");
        }
        //校验ip
        if(StringUtils.isEmpty(camera.getIp())){
            rtn.append("/ip不可为空");
        }else if(!checkIpAddr(camera.getIp())){
            rtn.append("/ip地址格式错误");
        }
        //校验terminal是否存在
        //terminalId 默认值为空
        if(!StringUtils.isEmpty(camera.getTerminalId())) {
            //terminalId不为空，则需校验
            // 1. TODO:terminalId是否存在

            // 2. 同一个terminalId下的摄像头ip不可重复
            if(isNew && terminalIpCount(camera.getIp(), camera.getTerminalId()) > 0){
                rtn.append("/同一接入终端下的ip地址已被占用，请更换");
            }
            //
            if(!isNew && !terminalIpCheckForUpdate(camera.getIp(), camera.getTerminalId(), camera.getId())){
                rtn.append("/同一接入终端下的ip地址已被占用，请更换");
            }
            //校验端口号不为空，且为1~65535之间
            if(StringUtils.isEmpty(camera.getPort())){
                rtn.append("/端口号不可为空");
            }else if(!checkPort(camera.getPort())) {
                rtn.append("/端口号应是1-65535之间的正整数");
            }
        }

        //校验不为空字段 e.g. sub 子码流，
        if(StringUtils.isEmpty(camera.getBitstream())) {
            rtn.append("/码流类型不可为空");
        }
        if(StringUtils.isEmpty(camera.getBuffer())) {
            rtn.append("/源缓冲文件不可为空");
        }
        if(StringUtils.isEmpty(camera.getChannel())) {
            rtn.append("/通道号不可为空");
        }
        if(StringUtils.isEmpty(camera.getCodingProtocal())) {
            rtn.append("/视频协议不可为空");
        }

        return rtn.toString();
    }



    private Integer terminalIpCount(String ip, String terminalId) {
        if(!StringUtils.isEmpty(ip) && !StringUtils.isEmpty(terminalId)) {
            return cameraMapper.getCameraIpCountByTerminalId(ip, terminalId);
        }
        return -1;
    }

    private Boolean terminalIpCheckForUpdate(String ip, String terminalId, Long id) {
        Boolean rtn = false;
        if(!StringUtils.isEmpty(ip) && !StringUtils.isEmpty(terminalId)) {
            CameraEntity camera = cameraMapper.getCameraByIpTerminalId(ip, terminalId);
            if(camera == null || camera != null && camera.getId() == id){
                rtn = true;
            }
        }
        return rtn;
    }

    private Boolean checkPort(Integer port) {
        Boolean result = false;

        //校验port为正整数
        String pattern = "^[1-9]\\d*$";
        try{
            result = Pattern.matches(pattern, port+"");
        }catch (Exception e) {
            log.error("checkPort Exception!  e: {}", e);
            return false;
        }
        //校验区间为0-65535
        if(result){
            if(port < 0 || port > 65535){
                result = false;
            }
        }
        return result;
    }

    private Boolean checkIpAddr(String ip) {
        Boolean result = false;
        String pattern = "(?=(\\b|\\D))(((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))(?=(\\b|\\D))";
        try {
            result = Pattern.matches(pattern, ip);
        } catch (Exception e) {
            log.error("checkIpAddr Exception! e: {}", e);
            result = false;
        }
        return result;
    }
}
