package com.firecontrol.domain.dto;

/**
 * Created by mariry on 2019/11/15.
 */
public class WsAlarmPush {
    //websocket服务器端推送消息类型：系统消息0，报警1，其他2(待扩展)
    private Integer msgType;

    private Long id;

    private Long type;

    private Integer level;

    private Long companyId;

    private Long floorId;

    private Long deviceId;

    private Long sensorId;

    private Byte status;

    private Long dealUserId;

    private String dealUserName;

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


    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

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

    public String getDealUserName() {
        return dealUserName;
    }

    public void setDealUserName(String dealUserName) {
        this.dealUserName = dealUserName;
    }

    public String getDealDetail() {
        return dealDetail;
    }

    public void setDealDetail(String dealDetail) {
        this.dealDetail = dealDetail;
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
        this.position = position;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public String getAlarmData() {
        return alarmData;
    }

    public void setAlarmData(String alarmData) {
        this.alarmData = alarmData;
    }

    public String getLimitUp() {
        return limitUp;
    }

    public void setLimitUp(String limitUp) {
        this.limitUp = limitUp;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLimitDown() {
        return limitDown;
    }

    public void setLimitDown(String limitDown) {
        this.limitDown = limitDown;
    }

    public String getPreLimitUp() {
        return preLimitUp;
    }

    public void setPreLimitUp(String preLimitUp) {
        this.preLimitUp = preLimitUp;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Long getPendingUserId() {
        return pendingUserId;
    }

    public void setPendingUserId(Long pendingUserId) {
        this.pendingUserId = pendingUserId;
    }

    public Boolean getPending() {
        return isPending;
    }

    public void setPending(Boolean pending) {
        isPending = pending;
    }

    public String getPosX() {
        return posX;
    }

    public void setPosX(String posX) {
        this.posX = posX;
    }

    public String getPosY() {
        return posY;
    }

    public void setPosY(String posY) {
        this.posY = posY;
    }

    public String getCameraId() {
        return cameraId;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
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
        this.buildingName = buildingName;
    }

    public String getBuildingMan() {
        return buildingMan;
    }

    public void setBuildingMan(String buildingMan) {
        this.buildingMan = buildingMan;
    }

    public String getBuildingPhone() {
        return buildingPhone;
    }

    public void setBuildingPhone(String buildingPhone) {
        this.buildingPhone = buildingPhone;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
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
        this.siteMan = siteMan;
    }

    public String getSitePhone() {
        return sitePhone;
    }

    public void setSitePhone(String sitePhone) {
        this.sitePhone = sitePhone;
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
        this.departmentName = departmentName;
    }

    public String getDepartmentMan() {
        return departmentMan;
    }

    public void setDepartmentMan(String departmentMan) {
        this.departmentMan = departmentMan;
    }

    public String getDepartmentPhone() {
        return departmentPhone;
    }

    public void setDepartmentPhone(String departmentPhone) {
        this.departmentPhone = departmentPhone;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
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

    public Boolean getOutdoor() {
        return isOutdoor;
    }

    public void setOutdoor(Boolean outdoor) {
        isOutdoor = outdoor;
    }

    public String getOutPosX() {
        return outPosX;
    }

    public void setOutPosX(String outPosX) {
        this.outPosX = outPosX;
    }

    public String getOutPosY() {
        return outPosY;
    }

    public void setOutPosY(String outPosY) {
        this.outPosY = outPosY;
    }

    public Integer getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Integer reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Boolean getReview() {
        return isReview;
    }

    public void setReview(Boolean review) {
        isReview = review;
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
        this.detail = detail;
    }
}
