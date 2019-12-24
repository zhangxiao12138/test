package com.firecontrol.domain.entity;

public class ElectricConfig {
    private Integer id;

    private String companyCode;

    private Float elePowerThreshold;

    private Float devicePowerThreshold;

    private Boolean eleOverloadIsOutage;

    private Boolean deviceOverloadIsOutage;

    private Double miniPowerValue;

    private Boolean miniPowerIsShow;

    private Float heatingPowerThreshold;

    private Boolean heatingOverloadIsOutage;

    private Boolean showSmallPossible;

    private Integer possibleThreshold;

    private String shieldEleTypeIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    public Float getElePowerThreshold() {
        return elePowerThreshold;
    }

    public void setElePowerThreshold(Float elePowerThreshold) {
        this.elePowerThreshold = elePowerThreshold;
    }

    public Float getDevicePowerThreshold() {
        return devicePowerThreshold;
    }

    public void setDevicePowerThreshold(Float devicePowerThreshold) {
        this.devicePowerThreshold = devicePowerThreshold;
    }

    public Boolean getEleOverloadIsOutage() {
        return eleOverloadIsOutage;
    }

    public void setEleOverloadIsOutage(Boolean eleOverloadIsOutage) {
        this.eleOverloadIsOutage = eleOverloadIsOutage;
    }

    public Boolean getDeviceOverloadIsOutage() {
        return deviceOverloadIsOutage;
    }

    public void setDeviceOverloadIsOutage(Boolean deviceOverloadIsOutage) {
        this.deviceOverloadIsOutage = deviceOverloadIsOutage;
    }

    public Double getMiniPowerValue() {
        return miniPowerValue;
    }

    public void setMiniPowerValue(Double miniPowerValue) {
        this.miniPowerValue = miniPowerValue;
    }

    public Boolean getMiniPowerIsShow() {
        return miniPowerIsShow;
    }

    public void setMiniPowerIsShow(Boolean miniPowerIsShow) {
        this.miniPowerIsShow = miniPowerIsShow;
    }

    public Float getHeatingPowerThreshold() {
        return heatingPowerThreshold;
    }

    public void setHeatingPowerThreshold(Float heatingPowerThreshold) {
        this.heatingPowerThreshold = heatingPowerThreshold;
    }

    public Boolean getHeatingOverloadIsOutage() {
        return heatingOverloadIsOutage;
    }

    public void setHeatingOverloadIsOutage(Boolean heatingOverloadIsOutage) {
        this.heatingOverloadIsOutage = heatingOverloadIsOutage;
    }

    public Boolean getShowSmallPossible() {
        return showSmallPossible;
    }

    public void setShowSmallPossible(Boolean showSmallPossible) {
        this.showSmallPossible = showSmallPossible;
    }

    public Integer getPossibleThreshold() {
        return possibleThreshold;
    }

    public void setPossibleThreshold(Integer possibleThreshold) {
        this.possibleThreshold = possibleThreshold;
    }

    public String getShieldEleTypeIds() {
        return shieldEleTypeIds;
    }

    public void setShieldEleTypeIds(String shieldEleTypeIds) {
        this.shieldEleTypeIds = shieldEleTypeIds == null ? null : shieldEleTypeIds.trim();
    }
}