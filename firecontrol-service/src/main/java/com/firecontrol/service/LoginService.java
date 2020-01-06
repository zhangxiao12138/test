package com.firecontrol.service;

import com.firecontrol.common.OpResult;
import com.firecontrol.domain.entity.LoginParam;

/**
 * Created by mariry on 2019/6/13.
 */
public interface LoginService{

    OpResult login(LoginParam param);


}
