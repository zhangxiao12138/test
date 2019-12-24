package com.firecontrol.web.controller;

import com.firecontrol.domain.entity.DemoEntity;
import com.firecontrol.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by mariry on 2019/6/21.
 */
@Controller
@RequestMapping(value = "/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    /**
     * 数据获取demo
     * @return
     */
    @RequestMapping(value = "/data")
    @ResponseBody
    public List<DemoEntity> index() {

        return demoService.getAllDemoEntity();
    }



    /**
     * 动态
     * @return
     */


    @RequestMapping(value = "/hkws")
    public String buttonJumpHKWS() {
        return "video";
    }


    @RequestMapping(value = "/3Ddemo")
    public String buttonJump3DDemo() {
        return "3Dframe";
    }

    @RequestMapping(value = "/frontPage")
    public String buttonJumpTablePage() {
        return "tablePage";
    }

    @RequestMapping(value = "/echartsDemo")
    public String buttonJumpEchartsDemo() {
        return "echartsDemo";
    }

    @RequestMapping(value = "/videoUrl")
    @ResponseBody
    public String getVideoUrl(){
        String rtn = demoService.getVideoUrl();
        return rtn;
    }


}
