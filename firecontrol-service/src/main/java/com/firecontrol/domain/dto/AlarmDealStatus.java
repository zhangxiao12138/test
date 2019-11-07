package com.firecontrol.domain.dto;

/**
 * Created by mariry on 2019/10/12.
 */
public class AlarmDealStatus {
    private Integer status;
    private String statusName;

    public AlarmDealStatus(){}

    public AlarmDealStatus(Integer status, String statusName){
        this.status = status;
        this.statusName = statusName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
