package com.firecontrol.service.impl;

import com.firecontrol.common.FireSocket;
import com.firecontrol.common.FireSocketService;
import com.firecontrol.common.OpResult;
import com.firecontrol.domain.dto.VideoUrlQueryDto;
import com.firecontrol.domain.entity.DemoEntity;
import com.firecontrol.mapper.iotmapper.DemoMapper;
import com.firecontrol.service.CameraService;
import com.firecontrol.service.DemoService;
//import com.mycorp.message.Vod;
import com.mycorp.vodplatform.server.service.impl.VodMsgServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mariry on 2019/6/21.
 */
@Service
public class DemoServiceImpl implements DemoService {
    private static final Logger log = LoggerFactory.getLogger(DemoServiceImpl.class);

    private static String[] urlArr = {
            "rtmp://media3.sinovision.net:1935/live/livestream",
            "rtmp://58.200.131.2:1935/livetv/hunantv",
            "rtmp://media3.scctv.net/live/scctv_800"};

    private static Integer videoUrlCount = 0;

    @Autowired
    private DemoMapper demoMapper;
    @Autowired
    private FireSocketService fireSocketService;
    @Autowired
    private VodMsgServiceImpl vodMsgService;
    @Autowired
    private CameraService cameraService;

    @Override
    public List<DemoEntity> getAllDemoEntity() {

        return demoMapper.getAll();
    }

    @Override
    public String setDirection(String d) {
        FireSocket fs = new FireSocket(1, 1, d);
        try{
            fireSocketService.send(fs);
        }catch (Exception e) {
            log.info("socket sent exception! e: " + e);
            return "failure";
        }
        return "success";
    }

    @Override
    public OpResult getVideoAddr(VideoUrlQueryDto query) {
        log.info("DemoServiceImpl.getVideoAddr: query param: {}", query);
        OpResult opResult = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);

        query.setCameraId(14);
        query.setTerminalId("TB1945T001");
        query.setDestinationId(4);
        query.setPlayTimeLimit(60);
        String url = "";
        if(query == null){
            query = new VideoUrlQueryDto("wangliji", "TB1945T001", 14, 4, 60);
        }else{
            if(query.getCameraId() == null){
                opResult.setStatus(OpResult.OP_FAILED);
                opResult.setMessage("摄像头id不可为空!");
                return opResult;
            }
//            CameraEntity camera = cameraService.getCameraById(new Long(query.getCameraId()));
//            if(camera == null){
//                opResult.setStatus(OpResult.OP_FAILED);
//                opResult.setMessage("摄像头不存在！");
//                return opResult;
//            }
//            if(StringUtils.isEmpty(camera.getTerminalId())) {
//                opResult.setStatus(OpResult.OP_FAILED);
//                opResult.setMessage("摄像头尚未关联通用终端！");
//                return opResult;
//            }
            if(query.getPlayTimeLimit() == null) {
                query.setPlayTimeLimit(60);
            }
            url = vodMsgService.sendVod(query.getUserId(), query.getTerminalId(), query.getCameraId(), query.getDestinationId(), query.getPlayTimeLimit());

            //TODO: 测试用，待删除
//            log.info("real url: " + url);
//            url = urlArr[videoUrlCount%3];
//            videoUrlCount ++;

            opResult.setDataValue(url);
        }
        return opResult;
    }

    @Override
    public String getVideoUrl() {

        //



        String url;
        url = vodMsgService.sendVod("wangliji", "tb221932T001", 1, 1, 60);
        return url;
    }


}
