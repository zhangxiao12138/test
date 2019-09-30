package com.firecontrol.config;

import com.mycorp.vodplatform.server.service.VodMsgService;
import com.mycorp.vodplatform.server.service.impl.VodMsgServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mycorp.vodplatform.server.Startup;

/**
 * Created by mariry on 2019/8/6.
 */
@Configuration
public class BeanConfig {

    @Bean
    public Startup createStartupService(){
        Startup startupService = new Startup();
        return startupService;
    }

    @Bean
    public VodMsgServiceImpl createVodMsgServiceImpl() {
        VodMsgServiceImpl vodMsgService = new VodMsgServiceImpl();
        return vodMsgService;
    }
}
