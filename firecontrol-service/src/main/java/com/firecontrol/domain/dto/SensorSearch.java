package com.firecontrol.domain.dto;

import com.firecontrol.common.BasePager;
import io.swagger.models.auth.In;

/**
 * Created by mariry on 2019/10/8.
 */
public class SensorSearch extends BasePager{

    private String deviceCode;

    private Integer deviceType;

    private Integer faultStatus;

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getFaultStatus() {
        return faultStatus;
    }

    public void setFaultStatus(Integer faultStatus) {
        this.faultStatus = faultStatus;
    }
}
