package com.firecontrol.web.controller;

import com.firecontrol.boot.FirecontrolWebappApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mariry on 2019/6/11.
 */

@RestController
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(FirecontrolWebappApplication.class);

    @Autowired
    protected HttpServletResponse response;
    @Autowired
    protected HttpServletRequest request;

    /**
     * 登录
     */
    @RequestMapping(value = "/")
    @PostMapping
    public ModelAndView index(Model model) {
        return new ModelAndView("hello");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @PostMapping
    public String login(Model model) {
        Map map = new HashMap<>();
        map.put("name", "mary");
        model.addAllAttributes(map);
        return "success";
    }


}
