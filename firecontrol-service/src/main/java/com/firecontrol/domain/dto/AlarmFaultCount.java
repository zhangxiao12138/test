package com.firecontrol.domain.dto;

/**
 * Created by mariry on 2019/11/12.
 */
public class AlarmFaultCount {

    private Integer todayAlarm;
    private Integer thisMonthAlarm;
    private Integer thisYearAlarm;

    private Integer todayFault;
    private Integer thisMonthFault;
    private Integer thisYearFault;

    private Integer todayLogin;
    private Integer currentLogin;
    private Integer totalUserAmount;

    public Integer getTodayAlarm() {
        return todayAlarm;
    }

    public void setTodayAlarm(Integer todayAlarm) {
        this.todayAlarm = todayAlarm;
    }

    public Integer getThisMonthAlarm() {
        return thisMonthAlarm;
    }

    public void setThisMonthAlarm(Integer thisMonthAlarm) {
        this.thisMonthAlarm = thisMonthAlarm;
    }

    public Integer getThisYearAlarm() {
        return thisYearAlarm;
    }

    public void setThisYearAlarm(Integer thisYearAlarm) {
        this.thisYearAlarm = thisYearAlarm;
    }

    public Integer getTodayFault() {
        return todayFault;
    }

    public void setTodayFault(Integer todayFault) {
        this.todayFault = todayFault;
    }

    public Integer getThisMonthFault() {
        return thisMonthFault;
    }

    public void setThisMonthFault(Integer thisMonthFault) {
        this.thisMonthFault = thisMonthFault;
    }

    public Integer getThisYearFault() {
        return thisYearFault;
    }

    public void setThisYearFault(Integer thisYearFault) {
        this.thisYearFault = thisYearFault;
    }

    public Integer getTodayLogin() {
        return todayLogin;
    }

    public void setTodayLogin(Integer todayLogin) {
        this.todayLogin = todayLogin;
    }

    public Integer getCurrentLogin() {
        return currentLogin;
    }

    public void setCurrentLogin(Integer currentLogin) {
        this.currentLogin = currentLogin;
    }

    public Integer getTotalUserAmount() {
        return totalUserAmount;
    }

    public void setTotalUserAmount(Integer totalUserAmount) {
        this.totalUserAmount = totalUserAmount;
    }
}
