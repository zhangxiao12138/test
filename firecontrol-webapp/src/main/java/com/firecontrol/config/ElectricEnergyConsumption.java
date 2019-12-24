package com.firecontrol.config;

import com.firecontrol.service.TpsonDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by mariry on 2019/12/19.
 */

@Component
public class ElectricEnergyConsumption {

    private static final Logger log = LoggerFactory.getLogger(ElectricEnergyConsumption.class);

    @Scheduled(cron = "0 1 0 * * *")
    public void calculateElectricUsage() {
        //按设备计算每天的耗电量
        //每天12点1分，遍历设备列表，将设备的能耗传感器的最近一条记录（sensorType = 18)汇总，并与前一日做差








        return;
    }

    @Scheduled(cron = "*/20 * * * * *")
    public void timerTest() {
        log.info("timer test, every 20s");
        return;
    }

}
