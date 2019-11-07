package com.firecontrol.config;

import com.firecontrol.common.StartConfigProperty;
import com.mycorp.vodplatform.server.service.VodMsgService;
import com.mycorp.vodplatform.server.service.impl.VodMsgServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mycorp.vodplatform.server.Startup;

/**
 * Created by mariry on 2019/8/6.
 */
@Configuration
public class BeanConfig {

    @Value("${tyterminal.path}")
    String TY_PATH;

    @Bean
    public Startup createStartupService(){
        Startup startupService = new Startup();
        System.out.println("new Startup ended.");
        return startupService;
    }

    @Bean
    public VodMsgServiceImpl createVodMsgServiceImpl() {
        VodMsgServiceImpl vodMsgService = new VodMsgServiceImpl();
        return vodMsgService;
    }

    @Bean
    public StartConfigProperty createStartConfigProperty() {
        StartConfigProperty startConfig = new StartConfigProperty();
        startConfig.setStartupPath(TY_PATH);
        System.out.println("set startConfig typath : " + startConfig.getStartupPath());
        return startConfig;
    }
}
