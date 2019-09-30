package com.firecontrol.domain.entity;

public class TpsonAlarmEntity {
    private Long id;

    private Long type;

    private Integer level;

    private Long companyId;

    private Long floorId;

    private Long deviceId;

    private Long sensorId;

    private Byte status;

    private Long dealUserId;

    private String dealDetail;

    private Integer addTime;

    private Integer updateTime;

    private Integer dealTime;

    private Integer count;

    private String position;

    private Boolean isDelete;

    private String alarmData;

    private String limitUp;

    private String unit;

    private String limitDown;

    private String preLimitUp;

    private String siteName;

    private Long pendingUserId;

    private Boolean isPending;

    private String posX;

    private String posY;

    private String cameraId;

    private Long buildingId;

    private String buildingName;

    private String buildingMan;

    private String buildingPhone;

    private String deviceName;

    private String sensorName;

    private String companyName;

    private String floorName;

    private String deviceCode;

    private Long photoId;

    private Long deviceType;

    private String siteMan;

    private String sitePhone;

    private Long siteId;

    private Long departmentId;

    private String departmentName;

    private String departmentMan;

    private String departmentPhone;

    private String openId;

    private Integer deviceVersion;

    private Long geographicId;

    private Boolean isOutdoor;

    private String outPosX;

    private String outPosY;

    private Integer reviewTime;

    private Boolean isReview;

    private Long reviewId;

    private Integer recoverTime;

    private Integer pendingTime;

    private String detail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getFloorId() {
        return floorId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getDealUserId() {
        return dealUserId;
    }

    public void setDealUserId(Long dealUserId) {
        this.dealUserId = dealUserId;
    }

    public String getDealDetail() {
        return dealDetail;
    }

    public void setDealDetail(String dealDetail) {
        this.dealDetail = dealDetail == null ? null : dealDetail.trim();
    }

    public Integer getAddTime() {
        return addTime;
    }

    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDealTime() {
        return dealTime;
    }

    public void setDealTime(Integer dealTime) {
        this.dealTime = dealTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getAlarmData() {
        return alarmData;
    }

    public void setAlarmData(String alarmData) {
        this.alarmData = alarmData == null ? null : alarmData.trim();
    }

    public String getLimitUp() {
        return limitUp;
    }

    public void setLimitUp(String limitUp) {
        this.limitUp = limitUp == null ? null : limitUp.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getLimitDown() {
        return limitDown;
    }

    public void setLimitDown(String limitDown) {
        this.limitDown = limitDown == null ? null : limitDown.trim();
    }

    public String getPreLimitUp() {
        return preLimitUp;
    }

    public void setPreLimitUp(String preLimitUp) {
        this.preLimitUp = preLimitUp == null ? null : preLimitUp.trim();
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName == null ? null : siteName.trim();
    }

    public Long getPendingUserId() {
        return pendingUserId;
    }

    public void setPendingUserId(Long pendingUserId) {
        this.pendingUserId = pendingUserId;
    }

    public Boolean getIsPending() {
        return isPending;
    }

    public void setIsPending(Boolean isPending) {
        this.isPending = isPending;
    }

    public String getPosX() {
        return posX;
    }

    public void setPosX(String posX) {
        this.posX = posX == null ? null : posX.trim();
    }

    public String getPosY() {
        return posY;
    }

    public void setPosY(String posY) {
        this.posY = posY == null ? null : posY.trim();
    }

    public String getCameraId() {
        return cameraId;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId == null ? null : cameraId.trim();
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName == null ? null : buildingName.trim();
    }

    public String getBuildingMan() {
        return buildingMan;
    }

    public void setBuildingMan(String buildingMan) {
        this.buildingMan = buildingMan == null ? null : buildingMan.trim();
    }

    public String getBuildingPhone() {
        return buildingPhone;
    }

    public void setBuildingPhone(String buildingPhone) {
        this.buildingPhone = buildingPhone == null ? null : buildingPhone.trim();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName == null ? null : sensorName.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName == null ? null : floorName.trim();
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode == null ? null : deviceCode.trim();
    }

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public Long getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Long deviceType) {
        this.deviceType = deviceType;
    }

    public String getSiteMan() {
        return siteMan;
    }

    public void setSiteMan(String siteMan) {
        this.siteMan = siteMan == null ? null : siteMan.trim();
    }

    public String getSitePhone() {
        return sitePhone;
    }

    public void setSitePhone(String sitePhone) {
        this.sitePhone = sitePhone == null ? null : sitePhone.trim();
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    public String getDepartmentMan() {
        return departmentMan;
    }

    public void setDepartmentMan(String departmentMan) {
        this.departmentMan = departmentMan == null ? null : departmentMan.trim();
    }

    public String getDepartmentPhone() {
        return departmentPhone;
    }

    public void setDepartmentPhone(String departmentPhone) {
        this.departmentPhone = departmentPhone == null ? null : departmentPhone.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public Integer getDeviceVersion() {
        return deviceVersion;
    }

    public void setDeviceVersion(Integer deviceVersion) {
        this.deviceVersion = deviceVersion;
    }

    public Long getGeographicId() {
        return geographicId;
    }

    public void setGeographicId(Long geographicId) {
        this.geographicId = geographicId;
    }

    public Boolean getIsOutdoor() {
        return isOutdoor;
    }

    public void setIsOutdoor(Boolean isOutdoor) {
        this.isOutdoor = isOutdoor;
    }

    public String getOutPosX() {
        return outPosX;
    }

    public void setOutPosX(String outPosX) {
        this.outPosX = outPosX == null ? null : outPosX.trim();
    }

    public String getOutPosY() {
        return outPosY;
    }

    public void setOutPosY(String outPosY) {
        this.outPosY = outPosY == null ? null : outPosY.trim();
    }

    public Integer getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Integer reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Boolean getIsReview() {
        return isReview;
    }

    public void setIsReview(Boolean isReview) {
        this.isReview = isReview;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getRecoverTime() {
        return recoverTime;
    }

    public void setRecoverTime(Integer recoverTime) {
        this.recoverTime = recoverTime;
    }

    public Integer getPendingTime() {
        return pendingTime;
    }

    public void setPendingTime(Integer pendingTime) {
        this.pendingTime = pendingTime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}