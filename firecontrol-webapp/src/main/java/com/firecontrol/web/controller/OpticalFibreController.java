package com.firecontrol.web.controller;

import com.firecontrol.domain.entity.DemoEntity;
import com.firecontrol.domain.entity.OpticalFibreTempEntity;
import com.firecontrol.service.OpticalFibreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by mariry on 2019/7/18.
 */
@Controller
@RequestMapping(value = "/fibre")
public class OpticalFibreController {

    @Autowired
    private OpticalFibreService opticalFibreService;

    @RequestMapping(value = "/demo")
    @ResponseBody
    public List<OpticalFibreTempEntity> index() {
        return opticalFibreService.getData(1);
    }

}
