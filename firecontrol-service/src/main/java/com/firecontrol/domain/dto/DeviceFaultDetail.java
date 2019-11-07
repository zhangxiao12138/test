package com.firecontrol.domain.dto;

import com.firecontrol.domain.entity.TpsonDeviceEntity;
import com.firecontrol.domain.entity.TpsonDeviceFaultEntity;

/**
 * Created by mariry on 2019/10/10.
 */
public class DeviceFaultDetail extends TpsonDeviceEntity{
    private Integer count;
    private Long type;
    private String type_name;
    private String deviceVersionName;
    private String deviceMan;
    private String devicePhone;
    private Integer addTime;
    private String dealDetail;

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public Long getType() {
        return type;
    }

    @Override
    public void setType(Long type) {
        this.type = type;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getDeviceVersionName() {
        return deviceVersionName;
    }

    public void setDeviceVersionName(String deviceVersionName) {
        this.deviceVersionName = deviceVersionName;
    }

    public String getDeviceMan() {
        return deviceMan;
    }

    public void setDeviceMan(String deviceMan) {
        this.deviceMan = deviceMan;
    }

    public String getDevicePhone() {
        return devicePhone;
    }

    public void setDevicePhone(String devicePhone) {
        this.devicePhone = devicePhone;
    }

    @Override
    public Integer getAddTime() {
        return addTime;
    }

    @Override
    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }

    public String getDealDetail() {
        return dealDetail;
    }

    public void setDealDetail(String dealDetail) {
        this.dealDetail = dealDetail;
    }


}
