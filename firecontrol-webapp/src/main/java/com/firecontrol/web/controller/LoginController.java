package com.firecontrol.web.controller;

import com.firecontrol.boot.FirecontrolWebappApplication;
import com.firecontrol.common.OpResult;
import com.firecontrol.domain.entity.LoginParam;
import com.firecontrol.service.LoginService;
import com.firecontrol.service.UserLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

@Controller
@RequestMapping(value = "/")
@Api(description = "巡查接口")
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(FirecontrolWebappApplication.class);

    @Autowired
    protected HttpServletResponse response;
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    private UserLoginService userLoginService;

    /**
     * 登录
     */
    @RequestMapping(value = "index")
    public ModelAndView index(Model model) {
        return new ModelAndView("index");
    }


//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String login(Model model) {
//
//        return "mainPage";
//    }

    @ApiOperation(value = "login" ,  notes="login")
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    @ApiImplicitParams({
            @ApiImplicitParam(name="userName",value="userName",paramType = "query"),
            @ApiImplicitParam(name="password",value="md5(密码),32位小写",paramType = "query"),
            @ApiImplicitParam(name="vendorId",value="vendorId",paramType = "query"),
    })
    @ResponseBody
    public OpResult login(LoginParam model, HttpServletRequest request, HttpServletResponse response) {

        return userLoginService.userLogin(model);

    }






}
