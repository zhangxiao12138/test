package com.firecontrol.domain.dto;

/**
 * Created by mariry on 2019/8/27.
 *
 * 拓深烟感SCU200 推送消息接收实体
 * demo：
 * "data":{
 *       "temperature":30, //温度，int
 *       "battery":80, //电池电压(mV)，int
 *       "signal":1, //信号强度，int
 *       "temperatureLimit":50, //温度设定高限，int
 *       "batteryBottom":1000, //电池设置电压低限，int
 *       "smokeStatus":"正常", //烟状态：正常，报警,烟雾传感器故障
 *       "temperatureStatus":"正常", //温度状态：正常，报警,温度传感器故障
 *       "batteryStatus":"正常", //电池状态：正常，电池电量低
 *       "dismantleStatus":"正常", //防拆状态：正常，被拆卸
 *       }
 *
 */
public class SCU200DataDto {
    private Integer temperature;

    private Integer battery;

    private Integer signal;

    private Integer temperatureLimit;

    private Integer batteryBottom;

    private String smokeStatus;

    private String temperaturesStatus;

    private String batteryStatus;

    private String dismantleStatus;


    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getBattery() {
        return battery;
    }

    public void setBattery(Integer battery) {
        this.battery = battery;
    }

    public Integer getSignal() {
        return signal;
    }

    public void setSignal(Integer signal) {
        this.signal = signal;
    }

    public Integer getTemperatureLimit() {
        return temperatureLimit;
    }

    public void setTemperatureLimit(Integer temperatureLimit) {
        this.temperatureLimit = temperatureLimit;
    }

    public Integer getBatteryBottom() {
        return batteryBottom;
    }

    public void setBatteryBottom(Integer batteryBottom) {
        this.batteryBottom = batteryBottom;
    }

    public String getSmokeStatus() {
        return smokeStatus;
    }

    public void setSmokeStatus(String smokeStatus) {
        this.smokeStatus = smokeStatus;
    }

    public String getTemperaturesStatus() {
        return temperaturesStatus;
    }

    public void setTemperaturesStatus(String temperaturesStatus) {
        this.temperaturesStatus = temperaturesStatus;
    }

    public String getBatteryStatus() {
        return batteryStatus;
    }

    public void setBatteryStatus(String batteryStatus) {
        this.batteryStatus = batteryStatus;
    }

    public String getDismantleStatus() {
        return dismantleStatus;
    }

    public void setDismantleStatus(String dismantleStatus) {
        this.dismantleStatus = dismantleStatus;
    }
}
