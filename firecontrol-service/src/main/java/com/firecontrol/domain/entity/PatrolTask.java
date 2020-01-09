package com.firecontrol.domain.entity;

public class PatrolTask {
    private Long id;

    private String name;

    private Integer createDate;

    private String description;

    private Long userFk;

    private Integer type;

    private Boolean isStop;

    private Integer siteCount;

    private Integer count;

    private Integer deviceCount;

    private Double percentage;

    private Integer isRepetition;

    private Double devicePercentage;

    private Long createUserFk;

    private Long companyFk;

    private Integer usefulTime;

    private Integer isOutdoor;

    private Integer sort;

    private Long floorId;

    private String floorName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public Long getUserFk() {
        return userFk;
    }

    public void setUserFk(Long userFk) {
        this.userFk = userFk;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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

    public Integer getIsRepetition() {
        return isRepetition;
    }

    public void setIsRepetition(Integer isRepetition) {
        this.isRepetition = isRepetition;
    }

    public Double getDevicePercentage() {
        return devicePercentage;
    }

    public void setDevicePercentage(Double devicePercentage) {
        this.devicePercentage = devicePercentage;
    }

    public Long getCreateUserFk() {
        return createUserFk;
    }

    public void setCreateUserFk(Long createUserFk) {
        this.createUserFk = createUserFk;
    }

    public Long getCompanyFk() {
        return companyFk;
    }

    public void setCompanyFk(Long companyFk) {
        this.companyFk = companyFk;
    }

    public Integer getUsefulTime() {
        return usefulTime;
    }

    public void setUsefulTime(Integer usefulTime) {
        this.usefulTime = usefulTime;
    }

    public Integer getIsOutdoor() {
        return isOutdoor;
    }

    public void setIsOutdoor(Integer isOutdoor) {
        this.isOutdoor = isOutdoor;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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
}