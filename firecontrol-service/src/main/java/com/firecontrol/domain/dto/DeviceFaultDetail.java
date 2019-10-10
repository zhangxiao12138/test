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

    public Integer getCount() {
        return count;
    }

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
}
