package com.firecontrol.domain.dto;

import io.swagger.models.auth.In;

/**
 * Created by mariry on 2019/10/10.
 */
public class FaultStatisticsDto {

    private Integer  deviceNum;

    private Integer dayHandledFault;

    private Integer monthHandleFault;

    private Integer unhandledFault;

    public FaultStatisticsDto(){}

    public FaultStatisticsDto(Integer deviceNum, Integer dayHandledFault, Integer monthHandleFault, Integer unhandledFault) {
        this.deviceNum = deviceNum;
        this.dayHandledFault = dayHandledFault;
        this.monthHandleFault = monthHandleFault;
        this.unhandledFault = unhandledFault;
    }


    public Integer getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(Integer deviceNum) {
        this.deviceNum = deviceNum;
    }

    public Integer getDayHandledFault() {
        return dayHandledFault;
    }

    public void setDayHandledFault(Integer dayHandledFault) {
        this.dayHandledFault = dayHandledFault;
    }

    public Integer getMonthHandleFault() {
        return monthHandleFault;
    }

    public void setMonthHandleFault(Integer monthHandleFault) {
        this.monthHandleFault = monthHandleFault;
    }

    public Integer getUnhandledFault() {
        return unhandledFault;
    }

    public void setUnhandledFault(Integer unhandledFault) {
        this.unhandledFault = unhandledFault;
    }

}
