package com.firecontrol.domain.dto;

/**
 * Created by mariry on 2019/10/12.
 */
public class AlarmTypeCountDto {
    private Integer alarmType;
    private String alarmTypeName;
    private Integer amount;

    public AlarmTypeCountDto() {}

    public AlarmTypeCountDto(Integer alarmType, String alarmTypeName, Integer amount) {
        this.alarmType = alarmType;
        this.alarmTypeName = alarmTypeName;
        this.amount = amount;
    }


    public Integer getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(Integer alarmType) {
        this.alarmType = alarmType;
    }

    public String getAlarmTypeName() {
        return alarmTypeName;
    }

    public void setAlarmTypeName(String alarmTypeName) {
        this.alarmTypeName = alarmTypeName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
