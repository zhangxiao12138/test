package com.firecontrol.domain.entity;

public class PatrolCheckItem {
    private Integer id;

    private Integer frequency;

    private Integer type;

    private Long patrolDeviceTypeFk;

    private Integer status;

    private Long companyFk;

    private Boolean isDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getPatrolDeviceTypeFk() {
        return patrolDeviceTypeFk;
    }

    public void setPatrolDeviceTypeFk(Long patrolDeviceTypeFk) {
        this.patrolDeviceTypeFk = patrolDeviceTypeFk;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCompanyFk() {
        return companyFk;
    }

    public void setCompanyFk(Long companyFk) {
        this.companyFk = companyFk;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
}