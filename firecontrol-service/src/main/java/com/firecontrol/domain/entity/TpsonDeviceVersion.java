package com.firecontrol.domain.entity;

public class TpsonDeviceVersion {
    private Long id;

    private String name;

    private Integer version;

    private String code;

    private Long deviceType;

    private Integer period;

    private Boolean deprecated;

    private Boolean isConfigurable;

    private Boolean isSilencing;

    private Boolean isSettablePeriod;

    private Boolean isModeConfigurable;

    private Boolean isControlDo;

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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Long getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Long deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Boolean getDeprecated() {
        return deprecated;
    }

    public void setDeprecated(Boolean deprecated) {
        this.deprecated = deprecated;
    }

    public Boolean getIsConfigurable() {
        return isConfigurable;
    }

    public void setIsConfigurable(Boolean isConfigurable) {
        this.isConfigurable = isConfigurable;
    }

    public Boolean getIsSilencing() {
        return isSilencing;
    }

    public void setIsSilencing(Boolean isSilencing) {
        this.isSilencing = isSilencing;
    }

    public Boolean getIsSettablePeriod() {
        return isSettablePeriod;
    }

    public void setIsSettablePeriod(Boolean isSettablePeriod) {
        this.isSettablePeriod = isSettablePeriod;
    }

    public Boolean getIsModeConfigurable() {
        return isModeConfigurable;
    }

    public void setIsModeConfigurable(Boolean isModeConfigurable) {
        this.isModeConfigurable = isModeConfigurable;
    }

    public Boolean getIsControlDo() {
        return isControlDo;
    }

    public void setIsControlDo(Boolean isControlDo) {
        this.isControlDo = isControlDo;
    }
}