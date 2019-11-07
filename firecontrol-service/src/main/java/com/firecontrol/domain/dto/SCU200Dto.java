package com.firecontrol.domain.dto;

import java.util.Date;

/**
 *
 * 拓深烟温一体机 - 推送实体
 * Created by mariry on 2019/8/27.
 *
 {
     "time":1515294211, //UNIX时间戳，时间
     "deviceType":"SCU200", //固定为此
     "deviceCode":"888888888888888", //设备编码
     "dataType":"DEVICE_DATA", //固定为此
         "data":{
         "temperature":30, //温度，int
         "battery":80, //电池电压(mV)，int
         "signal":1, //信号强度，int
         "temperatureLimit":50, //温度设定高限，int
         "batteryBottom":1000, //电池设置电压低限，int
         "smokeStatus":"正常", //烟状态：正常，报警,烟雾传感器故障
         "temperatureStatus":"正常", //温度状态：正常，报警,温度传感器故障
         "batteryStatus":"正常", //电池状态：正常，电池电量低
         "dismantleStatus":"正常", //防拆状态：正常，被拆卸
     }
 }
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
    private Integer time;


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

    public SCU200DataDto getData() {
        return data;
    }

    public void setData(SCU200DataDto data) {
        this.data = data;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
