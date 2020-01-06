package com.firecontrol.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.firecontrol.common.OpResult;
import com.firecontrol.common.StringMD5Util;
import com.firecontrol.domain.entity.LoginParam;
import com.firecontrol.domain.entity.UserEntity;
import com.firecontrol.mapper.iotmapper.UserEntityMapper;
import com.firecontrol.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mariry on 2019/6/11.
 */


@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private UserEntityMapper userEntityMapper;


    @Override
    public OpResult login(LoginParam param) {
        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_LOGIN_SUCCESS);
        Map rtnMap = new HashMap<>();

        try{

            if(param.getVendorId() == null) {
                op.setStatus(OpResult.OP_FAILED);
                op.setMessage(OpResult.OpMsg.OP_LOGIN_FAIL +":公司编号不可为空!");
                return op;
            }
            if(StringUtils.isEmpty(param.getPassword()) || StringUtils.isEmpty(param.getUserName())){
                op.setStatus(OpResult.OP_FAILED);
                op.setMessage(OpResult.OpMsg.OP_LOGIN_FAIL +":用户名或密码不可为空!");
                return op;
            }

            UserEntity user = userEntityMapper.getUserByUserName(param.getUserName(), param.getVendorId());

            //param上传已经md5加密的密码
            //secret = vendorId + "#" + addTime
            if(param.getPassword().equals(user.getPassword())) {
                //token = secret + "#" + timestamp
                String token = StringMD5Util.stringMD5(user.getSalt() + "#" + System.currentTimeMillis());
                userEntityMapper.updateUserToken(user.getId(), token);
                user.setToken(token);
                rtnMap.put("userInfo", user);
                op.setDataValue(rtnMap);
            }else{
                op.setStatus(OpResult.OP_FAILED);
                op.setMessage(OpResult.OpMsg.OP_LOGIN_FAIL + ":用户名或密码错误!");
                return op;
            }


        }catch (Exception e) {
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_LOGIN_FAIL);
            return op;
        }

        return op;
    }
}
