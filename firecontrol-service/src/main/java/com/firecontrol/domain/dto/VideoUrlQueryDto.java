package com.firecontrol.domain.dto;

/**
 * Created by mariry on 2019/8/14.
 */
public class VideoUrlQueryDto {
    /*
    当前登录用户id
     */
    private String userId;
    /*
    终端id
     */
    private String terminalId;
    /*
    摄像头id
     */
    private Integer cameraId;
    /*
    终端id
     */
    private Integer destinationId;

    /*
    播放时长 单位:秒
     */
    private Integer playTimeLimit;

    public VideoUrlQueryDto() {
        this.playTimeLimit = 30;
    }

    public VideoUrlQueryDto(String userId, String terminalId, Integer cameraId, Integer destinationId){
        this.userId = userId;
        this.terminalId = terminalId;
        this.cameraId = cameraId;
        this.destinationId = destinationId;
        this.playTimeLimit = 30;
    }

    public VideoUrlQueryDto(String userId, String terminalId, Integer cameraId, Integer destinationId, Integer playTimeLimit){
        this.userId = userId;
        this.terminalId = terminalId;
        this.cameraId = cameraId;
        this.destinationId = destinationId;
        if(playTimeLimit != null && playTimeLimit > 0) {
            this.playTimeLimit = playTimeLimit;
        }else {
            this.playTimeLimit = 30;
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public Integer getCameraId() {
        return cameraId;
    }

    public void setCameraId(Integer cameraId) {
        this.cameraId = cameraId;
    }

    public Integer getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Integer destinationId) {
        this.destinationId = destinationId;
    }

    public Integer getPlayTimeLimit() {
        return playTimeLimit;
    }

    public void setPlayTimeLimit(Integer playTimeLimit) {
        this.playTimeLimit = playTimeLimit;
    }
}
