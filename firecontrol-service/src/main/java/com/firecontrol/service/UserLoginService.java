package com.firecontrol.service;

import com.firecontrol.common.OpResult;
import com.firecontrol.domain.entity.LoginParam;

/**
 * Created by mary on 2020/1/6.
 */
public interface UserLoginService {

    OpResult userLogin(LoginParam param);

    OpResult newUser(Long vendorId, String userName, String password);

}
