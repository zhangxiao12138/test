package com.firecontrol.domain.entity;

public class TpsonDeviceFaultTypeEntity {
    private Long id;

    private String name;

    private Double scoreWeight;

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

    public Double getScoreWeight() {
        return scoreWeight;
    }

    public void setScoreWeight(Double scoreWeight) {
        this.scoreWeight = scoreWeight;
    }
}