package com.firecontrol.domain.dto;

import java.util.Date;

/**
 * Created by mariry on 2019/9/26.
 */
public class SCU1410Dto {

    private String deviceCode;

    private String deviceType;

    /*
    中继数据：DEVICE_DATA
    烟感报警数据：SENSOR_DATA
     */
    private String dataType;
    /*
    10位时间戳
     */
    private Date time;

    private SCU1410RelayData data;


    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public SCU1410RelayData getData() {
        return data;
    }

    public void setData(SCU1410RelayData data) {
        this.data = data;
    }
}
