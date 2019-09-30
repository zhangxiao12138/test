package com.firecontrol.domain.entity;

public class TpsonSensorEntity {

    private Long id;

    private String cameraId;

    private Long deviceId;

    private Long alarmTypeId;

    private Byte alarmLevel;

    private Integer addTime;

    private Integer updateTime;

    private Long companyId;

    private Long floorId;

    private String posX;

    private String posY;

    private Long sensorType;

    private String name;

    private String code;

    private String limitUp;

    private String limitDown;

    private Byte status;

    private Byte alarmStatus;

    private String preLimitUp;

    private String preLimitDown;

    private Boolean channelSwitch;

    private Boolean triggerSwitch;

    private Double range;

    private Long deviceType;

    private Boolean warningSwitch;

    private Boolean online;

    private Byte faultStatus;

    private Long siteId;

    private Integer group;

    private String man;

    private String phone;

    private Long departmentId;

    private Long geographicId;

    private Boolean isOutdoor;

    private String position;

    private String alarmName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCameraId() {
        return cameraId;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId == null ? null : cameraId.trim();
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getAlarmTypeId() {
        return alarmTypeId;
    }

    public void setAlarmTypeId(Long alarmTypeId) {
        this.alarmTypeId = alarmTypeId;
    }

    public Byte getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(Byte alarmLevel) {
        this.alarmLevel = alarmLevel;
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

    public Long getSensorType() {
        return sensorType;
    }

    public void setSensorType(Long sensorType) {
        this.sensorType = sensorType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getLimitUp() {
        return limitUp;
    }

    public void setLimitUp(String limitUp) {
        this.limitUp = limitUp == null ? null : limitUp.trim();
    }

    public String getLimitDown() {
        return limitDown;
    }

    public void setLimitDown(String limitDown) {
        this.limitDown = limitDown == null ? null : limitDown.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(Byte alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public String getPreLimitUp() {
        return preLimitUp;
    }

    public void setPreLimitUp(String preLimitUp) {
        this.preLimitUp = preLimitUp == null ? null : preLimitUp.trim();
    }

    public String getPreLimitDown() {
        return preLimitDown;
    }

    public void setPreLimitDown(String preLimitDown) {
        this.preLimitDown = preLimitDown == null ? null : preLimitDown.trim();
    }

    public Boolean getChannelSwitch() {
        return channelSwitch;
    }

    public void setChannelSwitch(Boolean channelSwitch) {
        this.channelSwitch = channelSwitch;
    }

    public Boolean getTriggerSwitch() {
        return triggerSwitch;
    }

    public void setTriggerSwitch(Boolean triggerSwitch) {
        this.triggerSwitch = triggerSwitch;
    }

    public Double getRange() {
        return range;
    }

    public void setRange(Double range) {
        this.range = range;
    }

    public Long getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Long deviceType) {
        this.deviceType = deviceType;
    }

    public Boolean getWarningSwitch() {
        return warningSwitch;
    }

    public void setWarningSwitch(Boolean warningSwitch) {
        this.warningSwitch = warningSwitch;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Byte getFaultStatus() {
        return faultStatus;
    }

    public void setFaultStatus(Byte faultStatus) {
        this.faultStatus = faultStatus;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public String getMan() {
        return man;
    }

    public void setMan(String man) {
        this.man = man == null ? null : man.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName == null ? null : alarmName.trim();
    }
}