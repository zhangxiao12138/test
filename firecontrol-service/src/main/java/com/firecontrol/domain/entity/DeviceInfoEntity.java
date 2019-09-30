package com.firecontrol.domain.entity;

import org.omg.PortableInterceptor.INACTIVE;
import sun.rmi.server.InactiveGroupException;

import java.io.DataOutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by mariry on 2019/9/27.
 */
public class DeviceInfoEntity implements Serializable{

    private Long id;

    private String cameraId;

    private String name;

    private String code;

    private Integer version;

    private Long type;

    private Long companyId;

    /*
    0离线1在线
     */
    private Integer online;
    /*
     0正常1注销2删除
     */
    private Integer status;
    /*
    负责人
     */
    private String man;
    /*
    负责人电话
     */
    private String phone;

    private Integer faultStatus;

    private String sim;
    /*
    设备同步周期
     */
    private Integer period;

    /*
        是否同步到设备
     */
    private Integer isSync;
    /*
        消音状态 0未知，1正常，2消音
     */
    private Integer silenceStatus;
    /*
    移动设备识别码
     */
    private String imei;
    /*
    集成电路卡识别码
     */
    private String iccid;
    /*
        0未激活，1离线，2正常，3故障，4报警，5禁用
     */
    private Integer runningState;

    private Date lastLogDate;
    private Date addTime;

    private Date updateTime;

    private Integer df;

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
        this.cameraId = cameraId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMan() {
        return man;
    }

    public void setMan(String man) {
        this.man = man;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getFaultStatus() {
        return faultStatus;
    }

    public void setFaultStatus(Integer faultStatus) {
        this.faultStatus = faultStatus;
    }

    public String getSim() {
        return sim;
    }

    public void setSim(String sim) {
        this.sim = sim;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getIsSync() {
        return isSync;
    }

    public void setIsSync(Integer isSync) {
        this.isSync = isSync;
    }

    public Integer getSilenceStatus() {
        return silenceStatus;
    }

    public void setSilenceStatus(Integer silenceStatus) {
        this.silenceStatus = silenceStatus;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public Integer getRunningState() {
        return runningState;
    }

    public void setRunningState(Integer runningState) {
        this.runningState = runningState;
    }

    public Date getLastLogDate() {
        return lastLogDate;
    }

    public void setLastLogDate(Date lastLogDate) {
        this.lastLogDate = lastLogDate;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDf() {
        return df;
    }

    public void setDf(Integer df) {
        this.df = df;
    }
}
