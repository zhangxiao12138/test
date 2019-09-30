package com.firecontrol.domain.dto;

/**
 * Created by mariry on 2019/9/26.
 */
public class SCU1410RelayData {
    /*
     * 低电压报警
     * 1：报警
     * 0：正常
     */
    private Integer batteryVoltageStatus;

    /*
     * 电池电压 mV
     */
    private Integer batteryVoltage;
    /*
        主机安装楼层
     */
    private Integer floor;

    /*
        传感器编码地址
     */
    private Integer address;
    /*
        烟感安装的房间号码
     */
    private Integer room;

    public Integer getBatteryVoltageStatus() {
        return batteryVoltageStatus;
    }

    public void setBatteryVoltageStatus(Integer batteryVoltageStatus) {
        this.batteryVoltageStatus = batteryVoltageStatus;
    }

    public Integer getBatteryVoltage() {
        return batteryVoltage;
    }

    public void setBatteryVoltage(Integer batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }
}
