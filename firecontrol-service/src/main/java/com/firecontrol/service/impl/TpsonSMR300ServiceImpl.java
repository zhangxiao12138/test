package com.firecontrol.service.impl;

import com.firecontrol.common.OpResult;
import com.firecontrol.common.StringMD5Util;
import com.firecontrol.domain.dto.SMR3002Dto;
import com.firecontrol.service.TpsonSMR3002Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mariry on 2019/11/11.
 */
@Service
public class TpsonSMR300ServiceImpl implements TpsonSMR3002Service {
    private static final Logger log = LoggerFactory.getLogger(TpsonSensorServiceImpl.class);

    @Override
    public OpResult saveSMR3002Data(SMR3002Dto dto) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_SUCCESS);
        log.info("SMR3002 data received: {}", dto);
        String company = "000002";
        String secret = StringMD5Util.stringMD5(company + "#" + company);
        String token = StringMD5Util.stringMD5(company + "#" + secret + "#" + 1800 + "#" + System.currentTimeMillis());

        Map rtnMap = new HashMap();
        rtnMap.put("secret", secret);
        rtnMap.put("token", token);
        op.setDataValue(rtnMap);
        return op;
    }
}
