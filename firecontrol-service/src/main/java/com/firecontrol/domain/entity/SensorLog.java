package com.firecontrol.domain.entity;

public class SensorLog {

    private Long id;

    private Long sensorId;

    private Integer sensorType;

    private Integer logTime;

    private Byte dataType;

    private Byte logType;

    private String logData;

    private Long deviceId;

    private String deviceCode;


    public SensorLog() {}

    public SensorLog(Long deviceId, String deviceCode, Long sensorId, Integer sensorType, Integer logTime, Byte logType, Byte dataType, String logData) {
        this.deviceCode = deviceCode;
        this.deviceId = deviceId;
        this.sensorId = sensorId;
        this.sensorType = sensorType;
        this.logTime = logTime;
        this.logType = logType;
        this.dataType = dataType;
        this.logData = logData;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    public Integer getSensorType() {
        return sensorType;
    }

    public void setSensorType(Integer sensorType) {
        this.sensorType = sensorType;
    }

    public Integer getLogTime() {
        return logTime;
    }

    public void setLogTime(Integer logTime) {
        this.logTime = logTime;
    }

    public Byte getDataType() {
        return dataType;
    }

    public void setDataType(Byte dataType) {
        this.dataType = dataType;
    }

    public Byte getLogType() {
        return logType;
    }

    public void setLogType(Byte logType) {
        this.logType = logType;
    }

    public String getLogData() {
        return logData;
    }

    public void setLogData(String logData) {
        this.logData = logData;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }
}