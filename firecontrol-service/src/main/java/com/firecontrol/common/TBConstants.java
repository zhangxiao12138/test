package com.firecontrol.common;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by mariry on 2019/8/27.
 */
public class TBConstants {

    public static final Map<String, Map<String, String>> allConstAlias = new LinkedHashMap<String, Map<String, String>>();

    // 烟感状态常量
    public static final class SmokeSensorStatus{
        public static final String OK = "正常";
        public static final String ALARM = "报警";
        public static final String FAULT = "温度传感器故障";
    }

    public final static class DATATYPE {
        //smr1410 中继数据
        public static final String DATA = "DEVICE_DATA";
        //smr1410 报警数据
        public static final String ALARM = "SENSOR_DATA";
    }


}
