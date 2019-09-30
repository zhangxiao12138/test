package com.firecontrol.domain.entity;

public class TpsonDeviceEntityWithBLOBs extends TpsonDeviceEntity {
    private String phone;

    private String photoIds;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPhotoIds() {
        return photoIds;
    }

    public void setPhotoIds(String photoIds) {
        this.photoIds = photoIds == null ? null : photoIds.trim();
    }
}