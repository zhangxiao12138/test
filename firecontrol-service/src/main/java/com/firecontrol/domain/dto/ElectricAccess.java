package com.firecontrol.domain.dto;

/**
 * Created by mariry on 2019/12/2.
 */
public class ElectricAccess {
    private Integer powerType;

    private Integer amount;

    public Integer getPowerType() {
        return powerType;
    }

    public void setPowerType(Integer powerType) {
        this.powerType = powerType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
