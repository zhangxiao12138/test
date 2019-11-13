package com.firecontrol.domain.dto;

/**
 * Created by mariry on 2019/11/11.
 */
public class SMR3002Dto {
    /*
    10位时间戳
     */
    private Integer time;
    /*
    deviceType
     */
    private String deviceType;
    /*
    deviceCode
     */
    private String deviceCode;

    /*
    data_type 固定为“REAL_DATA"/WARNING_DATA/ALARM_DATA/FAULT_DATA/IDENTIFY_DATA（电器识别推送）
     */
    private String dataType;

    private SMR3002DataDto data;






    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public SMR3002DataDto getData() {
        return data;
    }

    public void setData(SMR3002DataDto data) {
        this.data = data;
    }
}
