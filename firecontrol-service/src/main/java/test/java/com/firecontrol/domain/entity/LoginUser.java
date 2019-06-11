package com.firecontrol.domain.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by mariry on 2019/6/11.
 */

public class LoginUser implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;

    private String code;

    private String userName;

    private String password;

    private Date crreatDate;

    private Date modifyDate;

    private Date lastLoginDate;

    private Integer df;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

    public Date getCrreatDate() {
        return crreatDate;
    }

    public void setCrreatDate(Date crreatDate) {
        this.crreatDate = crreatDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Integer getDf() {
        return df;
    }

    public void setDf(Integer df) {
        this.df = df;
    }
}
