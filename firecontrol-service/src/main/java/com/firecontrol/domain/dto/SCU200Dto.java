package com.firecontrol.domain.dto;

import java.util.Date;

/**
 *
 * 拓深烟温一体机 - 推送实体
 * Created by mariry on 2019/8/27.
 */
public class SCU200Dto {

    private String deviceCode;

    private String deviceType;

    /*
    固定值，为DEVICE_DATA
     */
    private String dataType;
    /*
    10位时间戳
     */
    private Date time;


    private SCU200DataDto data;

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

    public SCU200DataDto getData() {
        return data;
    }

    public void setData(SCU200DataDto data) {
        this.data = data;
    }
}
