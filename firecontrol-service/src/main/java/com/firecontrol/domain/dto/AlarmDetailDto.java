package com.firecontrol.domain.dto;

/**
 * Created by mariry on 2019/10/12.
 */
public class AlarmDetailDto {

    private Long id;

    private Long type;


    private Long companyId;

    private Long floorId;

    private Long deviceId;

    private String deviceCode;

    private String deviceName;

    private Long deviceType;

    private Integer deviceVersion;

    private String deviceVersionName;

    private Byte status;

    private Long dealUserId;
    /*
        处理详情
     */
    private String dealDetail;
    /*
        详情
     */
    private String detail;

    private Integer addTime;

    private Integer updateTime;

    private Integer dealTime;

    private Integer count;

    private String position;

    private String unit;

    private String siteName;

    //报警阈值-
    private String limitDown;
    //报警阈值-高
    private String limitUp;

    private String dealUserName;

    //报警等级
    private Integer level;
    //
    private String levelName;




    private String buildingId;

    private String buildingName;

    private String buildingMan;

    private String buildingPhone;



    private String companyName;

    private String floorName;



    private String siteMan;

    private String sitePhone;

    private Long siteId;


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

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Long getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Long deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getDeviceVersion() {
        return deviceVersion;
    }

    public void setDeviceVersion(Integer deviceVersion) {
        this.deviceVersion = deviceVersion;
    }

    public String getDeviceVersionName() {
        return deviceVersionName;
    }

    public void setDeviceVersionName(String deviceVersionName) {
        this.deviceVersionName = deviceVersionName;
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
        this.dealDetail = dealDetail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
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

    public String getLimitDown() {
        return limitDown;
    }

    public void setLimitDown(String limitDown) {
        this.limitDown = limitDown;
    }

    public String getLimitUp() {
        return limitUp;
    }

    public void setLimitUp(String limitUp) {
        this.limitUp = limitUp;
    }

    public String getDealUserName() {
        return dealUserName;
    }

    public void setDealUserName(String dealUserName) {
        this.dealUserName = dealUserName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
}
