package com.firecontrol.web.controller;

import com.firecontrol.common.OpResult;
import com.firecontrol.domain.entity.CameraEntity;
import com.firecontrol.domain.entity.TerminalEntity;
import com.firecontrol.service.TerminalService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by mariry on 2019/8/15.
 */
@Api(description = "通用终端接口")
@Controller
@RequestMapping(value = "/tainbo/terminal")
public class TerminalController {
    @Autowired
    private TerminalService terminalService;

    @RequestMapping(value = "/newTerminal")
    @ResponseBody
    public OpResult insert(TerminalEntity terminal){
        return terminalService.insertTerminal(terminal);
    }

    @RequestMapping(value = "/terminalList")
    @ResponseBody
    public OpResult termenalList(TerminalEntity terminal, Integer currentPage, Integer length){
        return terminalService.getTerminalList(terminal, currentPage, length);
    }

    @RequestMapping(value = "/terminalIdList")
    @ResponseBody
    public OpResult termenalIdList(TerminalEntity terminal){
        return terminalService.getTerminalIds(terminal);
    }

    @RequestMapping(value = "/updateHardward")
    @ResponseBody
    public OpResult terminalHardward(String terminalId, String hardwardId){
        return terminalService.updateTerminalHardward(terminalId, hardwardId);
    }

}
