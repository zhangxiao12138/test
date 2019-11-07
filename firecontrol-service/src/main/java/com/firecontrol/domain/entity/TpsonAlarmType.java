package com.firecontrol.domain.entity;

public class TpsonAlarmType {
    private Long id;

    private String name;

    private Long deviceTypeId;

    private Integer systemType;

    private Double scoreWeight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(Long deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public Integer getSystemType() {
        return systemType;
    }

    public void setSystemType(Integer systemType) {
        this.systemType = systemType;
    }

    public Double getScoreWeight() {
        return scoreWeight;
    }

    public void setScoreWeight(Double scoreWeight) {
        this.scoreWeight = scoreWeight;
    }
}