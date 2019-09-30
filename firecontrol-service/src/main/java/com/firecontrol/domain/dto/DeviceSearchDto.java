package com.firecontrol.domain.dto;

/**
 * Created by mariry on 2019/9/27.
 */
public class DeviceSearchDto {
    /*
    设备类型
    烟感：8
     */
    private Integer deviceType;

    private Integer companyId;

    private Integer currentPage;

    private Integer pageSize;

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
