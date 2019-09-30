package com.firecontrol.domain.entity;

public class TpsonDeviceEntity {
    private Long id;

    private String cameraId;

    private String name;

    private String code;

    private Integer version;

    private Long type;

    private Long companyId;

    private Long floorId;

    private Integer addTime;

    private Integer updateTime;

    private Boolean online;

    private Byte status;

    private String posX;

    private String posY;

    private String ip;

    private Integer port;

    private String man;

    private Boolean relay;

    private Long faultStatus;

    private String position;

    private Long siteId;

    private Long departmentId;

    private String sim;

    private Integer period;

    private Boolean isSync;

    private Boolean isAlarmLinkage;

    private Integer silenceStatus;

    private Long geographicId;

    private Boolean isOutdoor;

    private Integer sensorMode;

    private String imei;

    private String iccid;

    private Integer lastLogTime;

    private Boolean enableSms;

    private Boolean enableCall;

    private String smsUnenableAlarmTypeIds;

    private String callUnenableAlarmTypeIds;

    private String publicSecurityCode;

    private Integer style;

    private Integer signalQuality;

    private Byte runningState;

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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getMan() {
        return man;
    }

    public void setMan(String man) {
        this.man = man == null ? null : man.trim();
    }

    public Boolean getRelay() {
        return relay;
    }

    public void setRelay(Boolean relay) {
        this.relay = relay;
    }

    public Long getFaultStatus() {
        return faultStatus;
    }

    public void setFaultStatus(Long faultStatus) {
        this.faultStatus = faultStatus;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
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

    public String getSim() {
        return sim;
    }

    public void setSim(String sim) {
        this.sim = sim == null ? null : sim.trim();
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Boolean getIsSync() {
        return isSync;
    }

    public void setIsSync(Boolean isSync) {
        this.isSync = isSync;
    }

    public Boolean getIsAlarmLinkage() {
        return isAlarmLinkage;
    }

    public void setIsAlarmLinkage(Boolean isAlarmLinkage) {
        this.isAlarmLinkage = isAlarmLinkage;
    }

    public Integer getSilenceStatus() {
        return silenceStatus;
    }

    public void setSilenceStatus(Integer silenceStatus) {
        this.silenceStatus = silenceStatus;
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

    public Integer getSensorMode() {
        return sensorMode;
    }

    public void setSensorMode(Integer sensorMode) {
        this.sensorMode = sensorMode;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid == null ? null : iccid.trim();
    }

    public Integer getLastLogTime() {
        return lastLogTime;
    }

    public void setLastLogTime(Integer lastLogTime) {
        this.lastLogTime = lastLogTime;
    }

    public Boolean getEnableSms() {
        return enableSms;
    }

    public void setEnableSms(Boolean enableSms) {
        this.enableSms = enableSms;
    }

    public Boolean getEnableCall() {
        return enableCall;
    }

    public void setEnableCall(Boolean enableCall) {
        this.enableCall = enableCall;
    }

    public String getSmsUnenableAlarmTypeIds() {
        return smsUnenableAlarmTypeIds;
    }

    public void setSmsUnenableAlarmTypeIds(String smsUnenableAlarmTypeIds) {
        this.smsUnenableAlarmTypeIds = smsUnenableAlarmTypeIds == null ? null : smsUnenableAlarmTypeIds.trim();
    }

    public String getCallUnenableAlarmTypeIds() {
        return callUnenableAlarmTypeIds;
    }

    public void setCallUnenableAlarmTypeIds(String callUnenableAlarmTypeIds) {
        this.callUnenableAlarmTypeIds = callUnenableAlarmTypeIds == null ? null : callUnenableAlarmTypeIds.trim();
    }

    public String getPublicSecurityCode() {
        return publicSecurityCode;
    }

    public void setPublicSecurityCode(String publicSecurityCode) {
        this.publicSecurityCode = publicSecurityCode == null ? null : publicSecurityCode.trim();
    }

    public Integer getStyle() {
        return style;
    }

    public void setStyle(Integer style) {
        this.style = style;
    }

    public Integer getSignalQuality() {
        return signalQuality;
    }

    public void setSignalQuality(Integer signalQuality) {
        this.signalQuality = signalQuality;
    }

    public Byte getRunningState() {
        return runningState;
    }

    public void setRunningState(Byte runningState) {
        this.runningState = runningState;
    }
}