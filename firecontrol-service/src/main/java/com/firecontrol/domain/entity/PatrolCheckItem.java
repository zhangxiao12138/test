package com.firecontrol.domain.entity;

public class PatrolCheckItem {
    private Integer id;

    private Integer frequency;

    private Integer type;

    private Long patrolDeviceTypeFk;

    private Integer status;

    private Long companyFk;

    private Boolean isDelete;

    private Integer amount;

    private Integer totalAmount;

    private Long floorId;

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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getFloorId() {
        return floorId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }
}