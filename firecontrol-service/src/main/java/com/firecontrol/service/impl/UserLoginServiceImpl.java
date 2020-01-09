package com.firecontrol.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.firecontrol.common.OpResult;
import com.firecontrol.common.StringMD5Util;
import com.firecontrol.domain.entity.LoginParam;
import com.firecontrol.domain.entity.UserEntity;
import com.firecontrol.mapper.iotmapper.UserEntityMapper;
import com.firecontrol.service.UserLoginService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mary on 2020/1/6.
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {

    private static final Logger log = LoggerFactory.getLogger(UserLoginServiceImpl.class);

    @Autowired
    private UserEntityMapper userEntityMapper;


    @Override
    public OpResult userLogin(LoginParam param) {
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
            log.error("exception! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_LOGIN_FAIL);
            return op;
        }

        return op;
    }

    @Override
    public OpResult newUser(Long vendorId, String userName, String password) {

        OpResult op = new OpResult(OpResult.OP_SUCCESS, OpResult.OpMsg.OP_LOGIN_SUCCESS);
        Map rtnMap = new HashMap<>();

        try{

            if(vendorId == null) {
                op.setStatus(OpResult.OP_FAILED);
                op.setMessage(OpResult.OpMsg.OP_LOGIN_FAIL +":公司编号不可为空!");
                return op;
            }
            if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)){
                op.setStatus(OpResult.OP_FAILED);
                op.setMessage(OpResult.OpMsg.OP_LOGIN_FAIL +":用户名或密码不可为空!");
                return op;
            }

            //检查同一公司重名
            if(existedUserName(userName, vendorId)){
                op.setStatus(OpResult.OP_FAILED);
                op.setMessage("用户名已存在!");
                return op;
            }

            UserEntity userEntity = new UserEntity();
            userEntity.setAddTime((int) (System.currentTimeMillis() / 1000));
            userEntity.setUpdateTime((int) (System.currentTimeMillis() / 1000));
            userEntity.setVendorId(vendorId);
            userEntity.setUsername(userName);
            userEntity.setPassword(password);
            userEntity.setSecret(vendorId + "#" + (System.currentTimeMillis() / 1000));
            userEntity.setIsDelete(false);

            Integer result = userEntityMapper.insert(userEntity);


        }catch (Exception e) {
            log.error("exception! e={}", e);
            op.setStatus(OpResult.OP_FAILED);
            op.setMessage(OpResult.OpMsg.OP_LOGIN_FAIL);
            return op;
        }
        return op;

    }

    private Boolean existedUserName(String userName, Long vendorId) {
        Boolean rtn = true;

        Integer countUser = userEntityMapper.countUserName(vendorId, userName);
        if(countUser > 0){
            return false;
        }

        return rtn;
    }


}
