package com.firecontrol.domain.dto;

/**
 * Created by mariry on 2019/11/12.
 */
public class DeviceStatMap {

    private Integer total;

    private String name;

    private Integer online;

    private Integer offline;

    private Integer fault;

    private Integer alarm;
    /*
    未激活的数量
     */
    private Integer unActive;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public Integer getOffline() {
        return offline;
    }

    public void setOffline(Integer offline) {
        this.offline = offline;
    }

    public Integer getFault() {
        return fault;
    }

    public void setFault(Integer fault) {
        this.fault = fault;
    }

    public Integer getAlarm() {
        return alarm;
    }

    public void setAlarm(Integer alarm) {
        this.alarm = alarm;
    }

    public Integer getUnActive() {
        return unActive;
    }

    public void setUnActive(Integer unActive) {
        this.unActive = unActive;
    }
}
