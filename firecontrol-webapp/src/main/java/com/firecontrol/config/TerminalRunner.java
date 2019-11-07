package com.firecontrol.config;

import com.firecontrol.common.StartConfigProperty;
import com.mycorp.vodplatform.server.Startup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by mariry on 2019/11/7.
 */
@Component
@Configuration
public class TerminalRunner implements ApplicationRunner {

//    private static final Logger log = LoggerFactory.getLogger(TerminalRunner.class);

//    private static ApplicationContext applicationContext = null;

    @Resource
    private Startup startup;
    @Resource
    private StartConfigProperty startConfigProperty;
//
//    public static void setApplicationContext(ApplicationContext applicationContext){
//        if(TerminalRunner.applicationContext == null){
//            TerminalRunner.applicationContext  = applicationContext;
//        }
//
//    }
//
//    //获取applicationContext
//    public static ApplicationContext getApplicationContext() {
//        return applicationContext;
//    }
//
//    //通过name获取 Bean.
//    public static Object getBean(String name){
//        return getApplicationContext().getBean(name);
//
//    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("######################### will start vodplatform startup! #############################");
        startup.mainForStart(startConfigProperty.getStartupPath());
        System.out.println("######################### vodplatform startup started! ##############################");
    }
}
