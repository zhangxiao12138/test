package com.firecontrol.domain.dto;


import io.swagger.models.auth.In;

/**
 * Created by mariry on 2019/10/9.
 */
public class FaultCountDto {
    private Integer count;

    private Long id;

    private Long type;

    public FaultCountDto(){}

    public FaultCountDto(Integer id, Integer type, Integer count) {
        this.id = new Long(id.longValue());
        this.type = new Long(type.longValue());
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }
}
