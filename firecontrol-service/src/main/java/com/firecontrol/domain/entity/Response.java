package com.firecontrol.domain.entity;

import java.io.Serializable;

/**
 * Created by mariry on 2019/12/5.
 */
public class Response<T> implements Serializable {
    public void setSuccess(boolean success) {
        this.success = success;
    }

//
//
//    {
//        "data": {
//        "expire": 1800,
//                "time": 1575624078,
//                "token": "CE6F75ACC730815B2DF9F7A75FC152FB"
//    },
//        "success": true,
//            "code": 0
//    }
//

    private boolean success;
    private T data;
    private String code;

    public Response() {
    }

    public Response(T data) {
        this.success = true;
        this.data = data;
    }

    public Response(boolean flag, T data) {
        if (flag) {
            this.success = true;
            this.data = data;
        } else {
            this.success = false;
            this.code = (String) code;
        }

    }

    public Response(String code) {
        this.success = false;
        this.code = code;
    }


    public boolean isSuccess() {
        return this.success;
    }

    public T getData() {
        return this.data;
    }

    public void setResult(T data) {
        this.success = true;
        this.data = data;
    }

    public String getCode() {
        return this.code;
    }

    public void setErrorCode(String errorCode) {
        this.success = false;
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Response response = (Response) o;
            boolean isErrorCode = !this.code.equals(response.code) ? false : this.data.equals(response.data);
            return this.success != response.success ? false : (isErrorCode);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result1 = this.success ? 1 : 0;
        result1 = 31 * result1 + this.data.hashCode();
        result1 = 31 * result1 + this.data.hashCode();
        return result1;
    }

    @Override
    public String toString() {
        return "Response{" +
                "success=" + success +
                ", data=" + data +
                ", code='" + code + '\'' +
                '}';
    }

}
