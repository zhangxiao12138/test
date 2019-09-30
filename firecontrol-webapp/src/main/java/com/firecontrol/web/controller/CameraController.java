package com.firecontrol.web.controller;

import com.firecontrol.common.OpResult;
import com.firecontrol.domain.dto.VideoUrlQueryDto;
import com.firecontrol.domain.entity.CameraEntity;
import com.firecontrol.service.CameraService;
import com.firecontrol.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by mariry on 2019/8/12.
 */
@Api(description = "摄像头接口")
@Controller
@RequestMapping(value = "/tainbo/camera")
public class CameraController {

    @Autowired
    private DemoService demoService;
    @Autowired
    private CameraService cameraService;

    @ApiOperation(value = "获取摄像头播放url" ,  notes="获取摄像头播放url")
    @RequestMapping(value = "/videoUrl",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public OpResult getVideoUrl(VideoUrlQueryDto videoQuery){

        return demoService.getVideoAddr(videoQuery);
    }
    @ApiOperation(value = "新增摄像头" ,  notes="新增摄像头")
    @RequestMapping(value = "/newCamera",method = {RequestMethod.POST})
    @ResponseBody
    public OpResult insert(CameraEntity camera){
        return cameraService.insertCamera(camera);
    }

    @ApiOperation(value = "更新摄像头" ,  notes="更新摄像头")
    @RequestMapping(value = "/updateCamera",method = {RequestMethod.POST})
    @ResponseBody
    public OpResult update(CameraEntity camera) {
        return cameraService.updateCamera(camera);
    }

    @ApiOperation(value = "获取摄像头列表" ,  notes="获取摄像头列表")
    @RequestMapping(value = "/cameraList",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public OpResult cameraList(CameraEntity camera, Integer currentPage, Integer pagesize) {
        return cameraService.getCameraList(camera, currentPage, pagesize);
    }
    @ApiOperation(value = "物理删除摄像头" ,  notes="物理删除摄像头")
    @RequestMapping(value = "/deleteCamera",method = {RequestMethod.DELETE})
    @ResponseBody
    public OpResult deleteCamera(String ids) {
        return cameraService.deleteCamera(ids);
    }

    @ApiOperation(value = "获取所有摄像头（不分页）" ,  notes="获取所有摄像头（不分页）")
    @RequestMapping(value = "/allCamera",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public OpResult getAllCamera() {
        return cameraService.getAllCamera();
    }

    @ApiOperation(value = "检查是否存在重名摄像头" ,  notes="排除检查的摄像头需传id")
    @RequestMapping(value = "/dupCameraName",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public OpResult dupCameraName(CameraEntity c) {
        return cameraService.dupCameraName(c);
    }

}
