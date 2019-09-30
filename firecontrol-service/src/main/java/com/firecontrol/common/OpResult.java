package com.firecontrol.common;

import java.io.Serializable;

/**
 * Created by mariry on 2019/8/14.
 */
public class OpResult implements Serializable {

    private static final long serialVersionUID = -5217263946675824590L;
    public static final int OP_SUCCESS = 1;
    public static final int OP_FAILED = 0;
    private int status;//1 成功  0失败
    private String message;//提示信息
    private Object dataValue;//数据值

    public OpResult(){}

    public OpResult(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDataValue() {
        return dataValue;
    }

    public void setDataValue(Object dataValue) {
        this.dataValue = dataValue;
    }
    public static final class OpMsg {
        public static final String SAVE_SUCCESS = "保存成功";
        public static final String SAVE_FAIL = "保存失败";
        public static final String MODIFY_SUCCESS = "修改成功";
        public static final String MODIFY_FAIL = "修改失败";
        public static final String DELETE_SUCCESS = "删除成功";
        public static final String DELETE_FAIL = "删除失败";
        public static final String OP_SUCCESS = "操作成功";
        public static final String OP_FAIL = "操作失败";
    }
}
