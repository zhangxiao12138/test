package com.firecontrol.domain.entity;

import sun.rmi.server.InactiveGroupException;

public class TpsonDeviceType {
    private Long id;

    private String name;

    private Integer systemType;

    public TpsonDeviceType(){}
    public TpsonDeviceType(Integer id, String name, Integer systemType){
        this.id = new Long(id.longValue());
        this.name= name;
        this.systemType = systemType;
    }



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

    public Integer getSystemType() {
        return systemType;
    }

    public void setSystemType(Integer systemType) {
        this.systemType = systemType;
    }
}