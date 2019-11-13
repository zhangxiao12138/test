package com.firecontrol.common;

import sun.rmi.server.InactiveGroupException;

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

    public class DEVICE_TYPE {
        //用传
        public final static int YC = 1;
        public final static String YC_NAME = "用户信息传输装置";
        //用水
        public final static int YS = 2;
        public final static String YS_NAME = "消防用水主机";
        //用电
        public final static int YD = 3;
        public static final String YD_NAME = "电气监测主机";
        //消防栓主机
        public static final int XFS = 4;
        public static final String XFS_NAME = "消防栓主机";
        //烟感
        public static final int YG = 7;
        public static final String YG_NAME = "无线烟感监测主机";
        //三相电气监测主机
        public static final int SXDQ = 12;
        public static final String SXDQ_NAME = "三相电气监测主机";
        //智能设备箱监测主机
        public static final int ZNSBX = 13;
        public static final String ZNSBX_NAME = "智能设备箱监测主机";
        //无线手动报警主机
        public static final int WXSD = 14;
        public static final String WXSD_NAME = "无线手动报警主机";
    }

    public class AlarmStatus {
        public final static String ALARM = "报警";
        public final static String ALARM_DISMISS = "烟雾报警停止";
        public final static String SMOCK_FAULT = "烟雾传感器故障";
        public final static String TEMP_FAULT = "温度传感器故障";
        public final static String BATTERY_LOW = "电池电量低";
        public final static String NORMAL = "正常";
        public final static String DISMENTLE = "被拆卸";

    }

    public class DeviceStatus {
        public final static int unActive = 0;      //未激活
        public final static int offline = 1;      //离线
        public final static int online = 2;      //正常
        public final static int fault = 3;      //故障
        public final static int alarm = 4;      //报警
        public final static int forbidden = 5;      //禁用
    }

    public class DeviceOnOffLine{
        public final static boolean online = true;
        public final static boolean offline = false;
    }



    public class TypeConstants {
        public final static int itemSku = 1;      //商品档案导入
        public final static int itemSkuSync = 2;      //商品档案导入同步数据
        public final static int deliverPriceAdjustValid = 3;      //配送调价单导入
        public final static int deliverPriceAdjustSync = 4;      //配送调价单导入同步数据
        public final static int storeSkuValid = 5;      //分店商品导入校验
        public final static int storeSkuSync = 6;      //分店商品导入同步数据
        public final static int itemSkuValid = 7;      //商品档案校验
    }

    public final static class DEVICE_FAULT {
        //smr1410 已处理
        public static final Integer OK = 1;
        //smr1410 未处理
        public static final Integer UNDEAL = 0;
        //误报
        public static final Integer WRONGALARM = 2;

    }


}
