package com.firecontrol.domain.entity;

import org.springframework.context.annotation.Bean;

import java.io.Serializable;

/**
 * Created by mariry on 2019/8/12.
 */

public class CameraEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String ip;

    private Integer port;
    /*
    摄像头的登录用户名
     */
    private String user;
    /*
    摄像头的登录密码
     */
    private String password;

    private String codingProtocal;

    private String channel;

    private String bitstream;

    private String buffer;

    private String terminalId;

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
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getCodingProtocal() {
        return codingProtocal;
    }

    public void setCodingProtocal(String codingProtocal) {
        this.codingProtocal = codingProtocal;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getBitstream() {
        return bitstream;
    }

    public void setBitstream(String bitstream) {
        this.bitstream = bitstream;
    }

    public String getBuffer() {
        return buffer;
    }

    public void setBuffer(String buffer) {
        this.buffer = buffer;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
