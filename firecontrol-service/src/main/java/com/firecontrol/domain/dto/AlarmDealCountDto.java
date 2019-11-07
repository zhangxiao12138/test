package com.firecontrol.domain.dto;

import io.swagger.models.auth.In;

/**
 * Created by mariry on 2019/10/12.
 */
public class AlarmDealCountDto {
    private Integer status;
    private String statusName;
    private Integer amount;

    public AlarmDealCountDto(){}

    public AlarmDealCountDto(Integer status, String statusName, Integer amount){
        this.status = status;
        this.statusName = statusName;
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
