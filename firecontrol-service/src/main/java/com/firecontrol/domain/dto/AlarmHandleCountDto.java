package com.firecontrol.domain.dto;

/**
 * Created by mariry on 2019/11/4.
 */
public class AlarmHandleCountDto {
    private Long dealUserId;

    private Integer amount;

    private String dealUserName;

    public Long getDealUserId() {
        return dealUserId;
    }

    public void setDealUserId(Long dealUserId) {
        this.dealUserId = dealUserId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDealUserName() {
        return dealUserName;
    }

    public void setDealUserName(String dealUserName) {
        this.dealUserName = dealUserName;
    }
}
