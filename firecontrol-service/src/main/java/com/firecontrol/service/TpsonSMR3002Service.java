package com.firecontrol.service;

import com.firecontrol.common.OpResult;
import com.firecontrol.domain.dto.SMR3002Dto;

/**
 * Created by mariry on 2019/11/11.
 */
public interface TpsonSMR3002Service {

    public OpResult saveSMR3002Data(SMR3002Dto dto);

    public String getTpsonToken();


}
