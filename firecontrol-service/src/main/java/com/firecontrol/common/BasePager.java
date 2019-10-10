package com.firecontrol.common;

/**
 * Created by mariry on 2019/10/8.
 */
public class BasePager {
    Integer currentPage;
    Integer length;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
