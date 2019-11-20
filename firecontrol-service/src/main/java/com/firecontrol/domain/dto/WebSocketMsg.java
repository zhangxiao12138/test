package com.firecontrol.domain.dto;

import com.alibaba.fastjson.JSON;
import com.firecontrol.domain.entity.TpsonAlarmEntity;
import io.swagger.models.auth.In;

/**
 * Created by mariry on 2019/11/14.
 */
public class WebSocketMsg {
    private Integer msgType;

    private Integer showWindow;

    private Object data;

    public Integer getMsgType() {
        return msgType;
    }

    public WebSocketMsg(){}

    public WebSocketMsg(Integer msgType, Integer showWindow, Object data){
        this.msgType = msgType;
        this.showWindow = showWindow;
        this.data = data;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public Integer getShowWindow() {
        return showWindow;
    }

    public void setShowWindow(Integer showWindow) {
        this.showWindow = showWindow;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
