package com.firecontrol.domain.dto;

import java.util.List;
import java.util.Map;

/**
 * Created by mariry on 2019/11/11.
 */
public class SMR3002DataDto {
    //实时监控字段
    //
//        "residueCurrent":500.50, //剩余电流，单位mA
//                "temperature1":25.60, //温度传感器1温度，单位摄氏度
//                "temperature2":27.60, //温度传感器2温度，单位摄氏度
//                "temperature3":28.60, //温度传感器3温度，单位摄氏度
//                "temperature4":29.60, //温度传感器4温度，单位摄氏度
//                "power":1000.00, //有功功率，单位瓦特
//                "voltage":223.00, //有效电压，单位伏特
//                "current":16.00, // 有效电流，单位安培
//                "faultArcCount":14, //故障电弧个数，单位：个/秒
//                "realElectricity":48.25 //实时电量（从第一次开机到当前时间用了多少kW.h的电量）

    /*
    剩余电流，单位mA
     */
    private Double residueCurrent;
    /*
    温度传感器1温度，单位摄氏度
     */
    private Double temperature1;
    /*
    温度传感器2温度，单位摄氏2
     */
    private Double temperature2;
    /*
    温度传感器3温度，单位摄氏
     */
    private Double temperature3;
    /*
    温度传感器4温度，单位摄氏度
     */
    private Double temperature4;
    /*
    有功功率，单位瓦特
     */
    private Double power;
    /*
    有效电压，单位伏特
     */
    private Double voltage;
    /*
    有效电流，单位安培
     */
    private Double current;
    /*
    故障电弧个数，单位：个/秒
     */
    private Integer faultArcCount;
    /*
    实时电量（从第一次开机到当前时间用了多少kW.h的电量）
     */
    private Double realElectricity;



    //预警、报警信息字段
    /*
    //预警类型:剩余电流，温度1，温度2，温度3，温度4，故障电弧，短路（实时数据和预警数据为0）；不同的预警类型，对应不同的实时数据和预警阈值
     */
    private String alarmTypeString;
    /*
    实时数据,浮点数
     */
    private Double realValue;
    /*
    预警阈值，浮点数
     */
    private Double configValue;


    //故障信息字段
    /*
      故障类型:剩余电流，温度1，温度2，温度3，温度4，故障电弧，短路
     */
    private String faultTypeString;



    //电器识别信息字段

//    "elecName":"电瓶车充电器",//电器名称
//    "identifyResult":[{"name":"电瓶车充电器","possible":0.98},{"name":"电风扇","possible":0.6}], //识别结果集
//    "power":202, //功率,浮点数，单位瓦
//    "elecType":0, //int，电器类型（0普通类型，2大功率电器，4发热电器，6大功率发热电器）
//    "action":1, //int，电器状态：-1拔出，0无动作，1接入，2换挡或模式变化，只有接入(action=1)的电器才能学习
//    "uuid":"3850335492070604572" //记录id

    /*
    电器名称
     */
    private String elecName;
    /*
    电器类型（0普通类型，2大功率电器，4发热电器，6大功率发热电器）
     */
    private Integer elecType;

    /*
    电器状态：-1拔出，0无动作，1接入，2换挡或模式变化，只有接入(action=1)的电器才能学习
     */
    private Integer action;
    /*
    uuid
     */
    private String uuid;

    /*
    [{"name":"电瓶车充电器","possible":0.98},{"name":"电风扇","possible":0.6}], //识别结果集
     */
    private List<Map> identifyResult;









    public Double getResidueCurrent() {
        return residueCurrent;
    }

    public void setResidueCurrent(Double residueCurrent) {
        this.residueCurrent = residueCurrent;
    }

    public Double getTemperature1() {
        return temperature1;
    }

    public void setTemperature1(Double temperature1) {
        this.temperature1 = temperature1;
    }

    public Double getTemperature2() {
        return temperature2;
    }

    public void setTemperature2(Double temperature2) {
        this.temperature2 = temperature2;
    }

    public Double getTemperature3() {
        return temperature3;
    }

    public void setTemperature3(Double temperature3) {
        this.temperature3 = temperature3;
    }

    public Double getTemperature4() {
        return temperature4;
    }

    public void setTemperature4(Double temperature4) {
        this.temperature4 = temperature4;
    }

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

    public Double getVoltage() {
        return voltage;
    }

    public void setVoltage(Double voltage) {
        this.voltage = voltage;
    }

    public Double getCurrent() {
        return current;
    }

    public void setCurrent(Double current) {
        this.current = current;
    }

    public Integer getFaultArcCount() {
        return faultArcCount;
    }

    public void setFaultArcCount(Integer faultArcCount) {
        this.faultArcCount = faultArcCount;
    }

    public Double getRealElectricity() {
        return realElectricity;
    }

    public void setRealElectricity(Double realElectricity) {
        this.realElectricity = realElectricity;
    }

    public String getAlarmTypeString() {
        return alarmTypeString;
    }

    public void setAlarmTypeString(String alarmTypeString) {
        this.alarmTypeString = alarmTypeString;
    }

    public Double getRealValue() {
        return realValue;
    }

    public void setRealValue(Double realValue) {
        this.realValue = realValue;
    }

    public Double getConfigValue() {
        return configValue;
    }

    public void setConfigValue(Double configValue) {
        this.configValue = configValue;
    }

    public String getFaultTypeString() {
        return faultTypeString;
    }

    public void setFaultTypeString(String faultTypeString) {
        this.faultTypeString = faultTypeString;
    }

    public String getElecName() {
        return elecName;
    }

    public void setElecName(String elecName) {
        this.elecName = elecName;
    }

    public Integer getElecType() {
        return elecType;
    }

    public void setElecType(Integer elecType) {
        this.elecType = elecType;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<Map> getIdentifyResult() {
        return identifyResult;
    }

    public void setIdentifyResult(List<Map> identifyResult) {
        this.identifyResult = identifyResult;
    }
}
