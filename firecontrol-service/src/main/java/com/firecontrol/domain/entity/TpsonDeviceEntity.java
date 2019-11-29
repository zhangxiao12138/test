package com.firecontrol.domain.entity;

import com.firecontrol.common.BasePager;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class TpsonDeviceEntity extends BasePager implements Serializable {

    @ApiModelProperty(value = "id" )
    private Long id;

    @ApiModelProperty(value = "摄像头id")
    private String cameraId;

    @ApiModelProperty(value = "设备名称")
    private String name;

    @ApiModelProperty(value = "自动注册设备")
    private String code;

    @ApiModelProperty(value = "版本")
    private Integer version;

    @ApiModelProperty(value = "类型")
    private Long type;

    @ApiModelProperty(value = "设备所属公司ID")
    private Long companyId;

    @ApiModelProperty(value = "设备所属公司地理位置ID")
    private String floorId;

    private String buildingId;

    @ApiModelProperty(value = "添加时间")
    private Integer addTime;

    @ApiModelProperty(value = "更新时间")
    private Integer updateTime;

    @ApiModelProperty(value = "0离线1在线")
    private Boolean online;

    @ApiModelProperty(value = "0正常1注销2删除")
    private Byte status;

    @ApiModelProperty(value = "")
    private String posX;

    @ApiModelProperty(value = "")
    private String posY;

    @ApiModelProperty(value = "ip")
    private String ip;

    @ApiModelProperty(value = "端口号")
    private Integer port;

    @ApiModelProperty(value = "负责人")
    private String man;

    @ApiModelProperty(value = "中继标志位")
    private Boolean relay;

    @ApiModelProperty(value = "故障状态, 0正常")
    private Long faultStatus;

    @ApiModelProperty(value = "位置信息")
    private String position;

    @ApiModelProperty(value = "监测点id")
    private Long siteId;

    @ApiModelProperty(value = "部门id")
    private Long departmentId;

    @ApiModelProperty(value = "SIM卡ID")
    private String sim;

    @ApiModelProperty(value = "设备同步周期")
    private Integer period;

    @ApiModelProperty(value = "是否同步到设备")
    private Boolean isSync;

    @ApiModelProperty(value = "是否报警联动")
    private Boolean isAlarmLinkage;

    @ApiModelProperty(value = "消音状态 0未知，1正常，2消音")
    private Integer silenceStatus;

    @ApiModelProperty(value = "地理位置id")
    private Long geographicId;

    @ApiModelProperty(value = "是否室外")
    private Boolean isOutdoor;

    @ApiModelProperty(value = "传感器模式类型，0电压型 1电流型")
    private Integer sensorMode;

    @ApiModelProperty(value = "移动设备识别码")
    private String imei;

    @ApiModelProperty(value = "集成电路卡识别码")
    private String iccid;

    @ApiModelProperty(value = "最新通讯时间")
    private Integer lastLogTime;

    @ApiModelProperty(value = "启用短信联动")
    private Boolean enableSms;

    @ApiModelProperty(value = "启用电话联动")
    private Boolean enableCall;

    @ApiModelProperty(value = "禁用短信功能的报警类型ids")
    private String smsUnenableAlarmTypeIds;

    @ApiModelProperty(value = "禁用电话功能的报警类型ids")
    private String callUnenableAlarmTypeIds;

    @ApiModelProperty(value = "公安系统编号")
    private String publicSecurityCode;

    @ApiModelProperty(value = "设备风格(类型):0其它，1治安，2交通，3社区，4公安")
    private Integer style;

    @ApiModelProperty(value = "信号强度")
    private Integer signalQuality;

    @ApiModelProperty(value = "0未激活，1离线，2正常，3故障，4报警，5禁用")
    private Byte runningState;

    private Integer runStateKeep;

    private String phone;

    private List<CameraEntity> cameras;

    private List<SensorLog> sensorList;


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

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
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

    public Boolean getSync() {
        return isSync;
    }

    public void setSync(Boolean sync) {
        isSync = sync;
    }

    public Boolean getAlarmLinkage() {
        return isAlarmLinkage;
    }

    public void setAlarmLinkage(Boolean alarmLinkage) {
        isAlarmLinkage = alarmLinkage;
    }

    public Boolean getOutdoor() {
        return isOutdoor;
    }

    public void setOutdoor(Boolean outdoor) {
        isOutdoor = outdoor;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<CameraEntity> getCameras() {
        return cameras;
    }

    public void setCameras(List<CameraEntity> cameras) {
        this.cameras = cameras;
    }

    public Integer getRunStateKeep() {
        return runStateKeep;
    }

    public void setRunStateKeep(Integer runStateKeep) {
        this.runStateKeep = runStateKeep;
    }

    public List<SensorLog> getSensorList() {
        return sensorList;
    }

    public void setSensorList(List<SensorLog> sensorList) {
        this.sensorList = sensorList;
    }
}