package com.firecontrol.domain.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by mariry on 2019/8/14.
 */
public class TerminalEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    private String id;

    private String hardwareId;

    private Date addTime;

    private Date firstAssignTime;

    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHardwareId() {
        return hardwareId;
    }

    public void setHardwareId(String hardwareId) {
        this.hardwareId = hardwareId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getFirstAssignTime() {
        return firstAssignTime;
    }

    public void setFirstAssignTime(Date firstAssignTime) {
        this.firstAssignTime = firstAssignTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
