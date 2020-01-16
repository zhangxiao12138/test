package com.firecontrol.domain.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class TaskDetail {

    private Long id;

    @Excel(name = "检查内容", orderNum = "1", width = 15)
    private String checkItemName;

    private Long userFk;

    private String userName;

    private Integer createDate;

    @Excel(name = "详细状况描述", orderNum = "5", width = 80)
    private String description;

    private Integer type;

    private Boolean isStop;

    private Integer siteCount;

    @Excel(name = "设备总数", orderNum = "3", width = 15)
    private Integer totalCount;

    @Excel(name = "本次巡检设备数", orderNum = "2", width = 15)
    private Integer deviceCount;

    private Double percentage;

    private Double devicePercentage;

    private Long companyFk;

    private Integer isOutdoor;

    private Long taskId;

    private String checkItemId;

    private Long floorId;

    private String floorName;

    //状态 1：正常 0：不正常
    @Excel(name = "检查结果(1:正常 0:异常)", orderNum = "4", width = 25)
    private Integer state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCheckItemName() {
        return checkItemName;
    }

    public void setCheckItemName(String checkItemName) {
        this.checkItemName = checkItemName == null ? null : checkItemName.trim();
    }

    public Long getUserFk() {
        return userFk;
    }

    public void setUserFk(Long userFk) {
        this.userFk = userFk;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Integer createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getIsStop() {
        return isStop;
    }

    public void setIsStop(Boolean isStop) {
        this.isStop = isStop;
    }

    public Integer getSiteCount() {
        return siteCount;
    }

    public void setSiteCount(Integer siteCount) {
        this.siteCount = siteCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(Integer deviceCount) {
        this.deviceCount = deviceCount;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Double getDevicePercentage() {
        return devicePercentage;
    }

    public void setDevicePercentage(Double devicePercentage) {
        this.devicePercentage = devicePercentage;
    }

    public Long getCompanyFk() {
        return companyFk;
    }

    public void setCompanyFk(Long companyFk) {
        this.companyFk = companyFk;
    }

    public Integer getIsOutdoor() {
        return isOutdoor;
    }

    public void setIsOutdoor(Integer isOutdoor) {
        this.isOutdoor = isOutdoor;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getCheckItemId() {
        return checkItemId;
    }

    public void setCheckItemId(String checkItemId) {
        this.checkItemId = checkItemId == null ? null : checkItemId.trim();
    }

    public Long getFloorId() {
        return floorId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}