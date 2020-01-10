package com.firecontrol.service.impl;

import com.firecontrol.common.OpResult;
import com.firecontrol.common.VideoOpResult;
import com.firecontrol.domain.dto.VideoUrlQueryDto;
import com.firecontrol.domain.entity.CameraEntity;
import com.firecontrol.domain.entity.DemoEntity;
import com.firecontrol.mapper.iotmapper.DemoMapper;
import com.firecontrol.service.CameraService;
import com.firecontrol.service.DemoService;
//import com.mycorp.message.Vod;
//import com.mycorp.vodplatform.server.service.impl.VodMsgServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
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

//    @Autowired
//    private VodMsgServiceImpl vodMsgService;
    @Autowired
    private CameraService cameraService;
    @Value("${tyterminal.pic}")
    private String pitPath;
    @Value("${tyterminal.header}")
    private String serverIp;
    @Value("${server.port}")
    private String port;


    @Override
    public List<DemoEntity> getAllDemoEntity() {

        return demoMapper.getAll();
    }


    @Override
    public VideoOpResult getVideoAddr(VideoUrlQueryDto query) {
        log.info("DemoServiceImpl.getVideoAddr: query param: {}", query);
        VideoOpResult opResult = new VideoOpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);

        query.setCameraId(14);
        query.setUserId("wangliji");
//        query.setTerminalId("TB1945T001");
//        query.setDestinationId(4);
//        query.setPlayTimeLimit(60);
        String url = "";
        if(query == null){
            query = new VideoUrlQueryDto("wangliji", "TB1945T001", 14, 4, 60);
        }else{
            if(query.getCameraId() == null){
                opResult.setStatus(OpResult.OP_FAILED);
                opResult.setMessage("摄像头id不可为空!");
                return opResult;
            }
            CameraEntity camera = cameraService.getCameraById(new Long(query.getCameraId()));
            if(camera == null){
                opResult.setStatus(OpResult.OP_FAILED);
                opResult.setMessage("摄像头不存在!");
                return opResult;
            }
            if(StringUtils.isEmpty(camera.getTerminalId())) {
                opResult.setStatus(OpResult.OP_FAILED);
                opResult.setMessage("摄像头尚未关联通用终端!");
                return opResult;
            }
            //destinationId  按照协议要求(rtmp.hls)要求，随便取一个vod_stream_destination 的id即可，及随机选一个终端服务器
            //TODO: 读数据库取id，添加负载均衡
//            url = vodMsgService.sendVod(query.getUserId(), camera.getTerminalId(), query.getCameraId(), 4, query.getPlayTimeLimit());

            StringBuffer sb = new StringBuffer().append(serverIp).append(":").append(port).append(pitPath).append(camera.getTerminalId()).append(File.separator)
                    .append(camera.getId()).append(".jpg");

            opResult.setDataValue(url);
            opResult.setImgSrc(sb.toString());
        }
        return opResult;
    }


    //恶劣环境测试方法
    @Override
    public String getVideoUrl() {

        //
        String url="";
//        url = vodMsgService.sendVod("wangliji", "tb221932T001", 1, 1, 60);
        return url;
    }


}
