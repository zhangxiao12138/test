package com.firecontrol.domain.entity;

public class PatrolDeviceType {
    private Long id;

    private Long parentId;

    private Integer level;

    private String name;

    private String description;

    private Integer checktype;

    private String allName;

    private Long companyFk;

    private Boolean isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getChecktype() {
        return checktype;
    }

    public void setChecktype(Integer checktype) {
        this.checktype = checktype;
    }

    public String getAllName() {
        return allName;
    }

    public void setAllName(String allName) {
        this.allName = allName == null ? null : allName.trim();
    }

    public Long getCompanyFk() {
        return companyFk;
    }

    public void setCompanyFk(Long companyFk) {
        this.companyFk = companyFk;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
}