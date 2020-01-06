package com.firecontrol.domain.entity;

/**
 * Created by mariry on 2020/1/6.
 */
public class LoginParam {

    private String userName;

    private String password;

    private Long vendorId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }


}
