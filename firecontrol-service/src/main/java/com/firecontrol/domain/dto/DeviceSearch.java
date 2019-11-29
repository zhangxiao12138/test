package com.firecontrol.domain.dto;

import com.firecontrol.common.BasePager;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

/**
 * Created by mariry on 2019/10/8.
 */
public class DeviceSearch extends BasePager{

    @ApiModelProperty(value = "设备类型" ,example = "1")
    private Integer deviceType;
    @ApiModelProperty(value = "搜索字符串" ,example = "1")
    private String searchStr;
    @ApiModelProperty(value = "搜索设备号" ,example = "1")
    private String searchCode;
    @ApiModelProperty(value = "搜索设备号" ,example = "1")
    private String deviceCode;


    /*
    运行状态
     */
    @ApiModelProperty(value = "运行状态" ,example = "1")
    private Integer runningState;

    /*
    是否室外 1：是 0：否
     */
    @ApiModelProperty(value = "室内外标志位 1：是 0：否" ,example = "1")
    private Integer isOutdoor;

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }



    public String getSearchCode() {
        return searchCode;
    }

    public void setSearchCode(String searchCode) {
        this.searchCode = searchCode;
    }

    public Integer getRunningState() {
        return runningState;
    }

    public void setRunningState(Integer runningState) {
        this.runningState = runningState;
    }

    public Integer getIsOutdoor() {
        return isOutdoor;
    }

    public void setIsOutdoor(Integer isOutdoor) {
        this.isOutdoor = isOutdoor;
    }

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }
}
