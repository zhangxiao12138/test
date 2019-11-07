package com.firecontrol.common;

import io.swagger.models.auth.In;

/**
 * Created by mariry on 2019/10/11.
 */
public class RunningStateCount {
    private Integer runningState;
    private String runningStateName;
    private Integer stateCount;

    public RunningStateCount(){}

    public RunningStateCount(Integer runningState, Integer stateCount){
            this.runningState = runningState;
            this.stateCount = stateCount;
            switch (runningState){
                case 0: {
                    this.runningStateName = "未激活";
                    break;
                }
                case 1: {
                    this.runningStateName = "离线";
                    break;
                }
                case 2: {
                    this.runningStateName = "正常";
                    break;
                }
                case 3: {
                    this.runningStateName = "故障";
                    break;
                }
                case 4: {
                    this.runningStateName = "报警";
                    break;
                }
                case 5: {
                    this.runningStateName = "禁用";
                    break;
                }
            }

    }


    public Integer getRunningState() {
        return runningState;
    }

    public void setRunningState(Integer runningState) {
        this.runningState = runningState;
        switch (runningState){
            case 0: {
                this.runningStateName = "未激活";
                break;
            }
            case 1: {
                this.runningStateName = "离线";
                break;
            }
            case 2: {
                this.runningStateName = "正常";
                break;
            }
            case 3: {
                this.runningStateName = "故障";
                break;
            }
            case 4: {
                this.runningStateName = "报警";
                break;
            }
            case 5: {
                this.runningStateName = "禁用";
                break;
            }
        }

    }



    public Integer getStateCount() {
        return stateCount;
    }

    public void setStateCount(Integer stateCount) {
        this.stateCount = stateCount;
    }

    public String getRunningStateName() {
        return runningStateName;
    }

    public void setRunningStateName(String runningStateName) {
        this.runningStateName = runningStateName;
    }
}
