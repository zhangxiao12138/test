package com.firecontrol.domain.dto;

/**
 * Created by mariry on 2019/11/26.
 */
public class BuildingAlarmDto {

    private String buildingId;

    private String name;

    private Integer total;

    private Integer unhandle;

    private Integer status;//0未处理 1已处理 2误报'

    public BuildingAlarmDto(){
        this.total = 0;
        this.unhandle = 0;
        this.name = "";
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getUnhandle() {
        return unhandle;
    }

    public void setUnhandle(Integer unhandle) {
        this.unhandle = unhandle;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
