package com.firecontrol.service.impl;

import com.firecontrol.common.OpResult;
import com.firecontrol.domain.entity.TerminalEntity;
import com.firecontrol.mapper.iotmapper.TerminalMapper;
import com.firecontrol.service.TerminalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by mariry on 2019/8/15.
 */
@Service
public class TerminalServiceImpl implements TerminalService {
    private static final Logger log = LoggerFactory.getLogger(TerminalService.class);

    @Autowired
    private TerminalMapper terminalMapper;

    @Override
    public OpResult insertTerminal(TerminalEntity terminal) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_FAIL);

        try{
            String terminalId = generageTerminalId();
            terminal.setId(terminalId);
            terminal.setStatus(0);//新建，状态均为未激活
            boolean result = terminalMapper.insert(terminal);
            if(!result){
                op.setStatus(OpResult.OP_FAILED);
                op.setMessage(OpResult.OpMsg.SAVE_FAIL);
                return op;
            }
        }catch (Exception e) {
            log.error("TerminalServiceImpl.insertTerminal Exception! e:{}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.SAVE_FAIL);
            return op;
        }
        return op;
    }

    @Override
    public OpResult updateTerminal(TerminalEntity terminal) {

        return null;
    }

    @Override
    public OpResult getTerminalList(TerminalEntity terminal, Integer currentPage, Integer length) {
        OpResult opResult = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        List<TerminalEntity> rtn = null;
        try{
            rtn = terminalMapper.getAllTerminalList();
            opResult.setDataValue(rtn);
        }catch (Exception e){
            log.error("TerminalServiceImpl.getTerminalList Exception! e:{}", e);
            opResult.setStatus(OpResult.OP_FAILED);
            opResult.setMessage(OpResult.OpMsg.OP_FAIL);
            return opResult;
        }

        return opResult;
    }

    @Override
    public OpResult getTerminalIds(TerminalEntity terminal) {
        OpResult opResult = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        try{
            List<String> terminalIdList = terminalMapper.getAllTerminalIds();
            opResult.setDataValue(terminalIdList);
        }catch (Exception e) {
            log.error("TerminalServiceImpl.insertTerminal Exception! e:{}", e);
            opResult.setStatus(OpResult.OP_FAILED);
            opResult.setMessage(OpResult.OpMsg.SAVE_FAIL);
            return opResult;
        }
        return opResult;
    }

    @Override
    public OpResult updateTerminalHardward(String terminalId, String hardwardId) {
        OpResult opResult = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        try{
            if(StringUtils.isEmpty(terminalId) || StringUtils.isEmpty(hardwardId)) {
                opResult.setStatus(OpResult.OP_FAILED);
                opResult.setMessage("终端ID或硬件ID不可为空！");
                return opResult;
            }
            int count = terminalMapper.updateHardwareId(terminalId, hardwardId);
            if(count <0) {
                opResult.setStatus(OpResult.OP_FAILED);
                opResult.setMessage("数据库更新失败!");
                return opResult;
            }
        }catch (Exception e) {
            log.error("TerminalServiceImpl.insertTerminal Exception! e:{}", e);
            opResult.setStatus(OpResult.OP_FAILED);
            opResult.setMessage(OpResult.OpMsg.SAVE_FAIL);
            return opResult;
        }
        return opResult;
    }

    private String generageTerminalId() {
        StringBuffer sb = new StringBuffer();
        sb.append("tb22");//固定prefix

        //获取周数和年份,
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        String year = String.valueOf(cal.get(Calendar.YEAR));

        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置周一为一周的第一天
        cal.setTime(date);
        int num = cal.get(Calendar.WEEK_OF_YEAR);
        sb.append(year.substring(2)).append(num);

        //获取当前周的最大terminalId,编码固定3位，001-999
        OpResult op = getCurrentTerminalCount(sb.toString());
        if(op.getStatus() != OpResult.OP_SUCCESS){
            return null;
        }else{
            sb.append(op.getDataValue());
            return sb.toString();
        }

    }

    private OpResult getCurrentTerminalCount(String prefix) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        String rtn = "";
        Integer newCount;
        Integer weekTerminalCount = terminalMapper.getWeekTerminalCount(prefix);
        newCount = weekTerminalCount + 1;
        if(weekTerminalCount < 0){
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage("数据库查询失败，count:"+weekTerminalCount);
        }else if(weekTerminalCount >=0 && weekTerminalCount < 9){
            rtn = "00" + newCount;
        }else if(weekTerminalCount >=9 && weekTerminalCount <99){
            rtn = "0" + newCount;
        }else if(weekTerminalCount >= 99 && weekTerminalCount <999) {
            rtn = newCount + "";
        }else if(weekTerminalCount >= 999){
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage("本周terminal已经达到上限，count:"+weekTerminalCount);
            log.error("本周terminal已经达到上限，count:"+weekTerminalCount);
        }
        return op;
    }
}
