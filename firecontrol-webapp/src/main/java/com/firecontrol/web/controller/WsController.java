package com.firecontrol.web.controller;

import com.firecontrol.common.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.TreeMap;

/**
 * Created by mariry on 2019/11/14.
 * websocket Controller
 */
@Controller
@RequestMapping(value = "/tainbo/endPoint")
public class WsController {
    @Autowired
    private WebSocketService webSocketService;

    @RequestMapping(value = "/chatMsg", method = RequestMethod.POST)
    @ResponseBody
    public TreeMap<String, String> add(@Autowired HttpServletRequest request) {
        TreeMap tm = new TreeMap();
        tm.put("toUser", request.getParameter("toUser"));
        tm.put("fromUser", request.getParameter("fromUser"));
        tm.put("message", request.getParameter("message"));
        return tm;
    }


    //页面请求
    @GetMapping("/socket/{cid}")
    public ModelAndView socket(@PathVariable String cid) {
        ModelAndView mav=new ModelAndView("/socket");
        mav.addObject("cid", cid);
        return mav;
    }
    //推送数据接口
    @ResponseBody
    @RequestMapping("/socket/push/{cid}")
    public String pushToWeb(@PathVariable String cid,String message) {
        try {
            WebSocketService.sendInfo(message,cid);
        } catch (Exception e) {
            e.printStackTrace();
            return cid+"#"+e.getMessage();
        }
        return cid+"成功";
    }



}
