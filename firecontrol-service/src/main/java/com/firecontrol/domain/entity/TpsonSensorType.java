package com.firecontrol.domain.entity;

public class TpsonSensorType {
    private Long id;

    private String name;

    private Long alarmTypeId;

    private Long deviceType;

    private Integer systemType;

    private String unit;

    private Boolean isStat;

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

    public Long getAlarmTypeId() {
        return alarmTypeId;
    }

    public void setAlarmTypeId(Long alarmTypeId) {
        this.alarmTypeId = alarmTypeId;
    }

    public Long getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Long deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getSystemType() {
        return systemType;
    }

    public void setSystemType(Integer systemType) {
        this.systemType = systemType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Boolean getIsStat() {
        return isStat;
    }

    public void setIsStat(Boolean isStat) {
        this.isStat = isStat;
    }
}