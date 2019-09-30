package com.firecontrol.service;

import com.firecontrol.common.OpResult;
import com.firecontrol.domain.dto.SCU1410Dto;

/**
 * Created by mariry on 2019/9/26.
 */
public interface TpsonSCU1410Service {

    /**
     * 烟感推送数据落库
     * @param dto
     * @return
     */
    public OpResult saveSCU1410Data(SCU1410Dto dto);


    public OpResult saveSCU1410DateToMQ(SCU1410Dto dto);




}
