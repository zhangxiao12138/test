package com.firecontrol.domain.entity;

import com.firecontrol.common.BasePager;
import com.firecontrol.domain.dto.ElectricPossible;

import java.util.List;
import java.util.Map;

public class ElectricLog extends BasePager{
    private Long id;

    private Integer companyId;

    private Integer deviceId;

    private String deviceCode;

    private Integer type;

    private Double power;

    private Byte powerType;

    private String uuid;

    private Integer action;

    private String actionName;

    private Integer addTime;

    private String possibleStr;

    private List<ElectricPossible> possible;

    private Integer maxPossible;

    private Long departmentId;

    private Integer group;

    private String siteName;

    private String floorId;

    private String buildingId;

    private String feature;

    private String elecName;

    private Boolean isOutdoor;

    private Integer startTime;

    private Integer endTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode == null ? null : deviceCode.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

    public Byte getPowerType() {
        return powerType;
    }

    public void setPowerType(Byte powerType) {
        this.powerType = powerType;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public Integer getAddTime() {
        return addTime;
    }

    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }

    public String getPossibleStr() {
        return possibleStr;
    }

    public void setPossibleStr(String possibleStr) {
        this.possibleStr = possibleStr;
    }

    public List<ElectricPossible> getPossible() {
        return possible;
    }

    public void setPossible(List<ElectricPossible> possible) {
        this.possible = possible;
    }

    public Integer getMaxPossible() {
        return maxPossible;
    }

    public void setMaxPossible(Integer maxPossible) {
        this.maxPossible = maxPossible;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName == null ? null : siteName.trim();
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId == null ? null : floorId.trim();
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId == null ? null : buildingId.trim();
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature == null ? null : feature.trim();
    }

    public Boolean getIsOutdoor() {
        return isOutdoor;
    }

    public void setIsOutdoor(Boolean outdoor) {
        isOutdoor = outdoor;
    }



    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public String getElecName() {
        return elecName;
    }

    public void setElecName(String elecName) {
        this.elecName = elecName;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }
}