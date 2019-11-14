package com.firecontrol.domain.dto;

import com.alibaba.fastjson.JSON;
import com.firecontrol.domain.entity.TpsonAlarmEntity;

/**
 * Created by mariry on 2019/11/14.
 */
public class WebSocketMsg {
    private String fromUser;
    private String toUser;
    private TpsonAlarmEntity message;

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public TpsonAlarmEntity getMessage() {
        return message;
    }

    public void setMessage(TpsonAlarmEntity message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "fromUser='" + fromUser + '\'' +
                ", toUser='" + toUser + '\'' +
                ", message='" + JSON.toJSONString(message) + '\'' +
                '}';
    }

}
