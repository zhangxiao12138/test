package com.firecontrol.web.controller;

import com.firecontrol.domain.entity.DemoEntity;
import com.firecontrol.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
     * 登录
     */
    @RequestMapping(value = "/data")
    @ResponseBody
    public List<DemoEntity> index() {

        return demoService.getAllDemoEntity();
    }



}
