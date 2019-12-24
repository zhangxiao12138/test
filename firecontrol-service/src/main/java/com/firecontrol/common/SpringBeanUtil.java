package com.firecontrol.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 需要在多线程中使用业务层的方法实现自己的逻辑，但是多线程是防注入的，
 * 所以只是在多线程实现类中简单的使用@Autowired方法注入自己的Service，
 * 会在程序运行到此类调用service方法的时候提示注入的service为null;
 *
 * 本文件提供简单的Bean注入功能
 *
 * Created by mariry on 2019/12/24.
 */

@Component
public class SpringBeanUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws
            BeansException {
        // TODO Auto-generated method stub
        SpringBeanUtil.applicationContext = applicationContext;
    }

    /**
     * 从静态变量applicationContext中得到Bean, 自动转型为所赋值对象的类型.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        if(name == null || applicationContext == null)
            return null;
        return (T) applicationContext.getBean(name);
    }

    /**
     * 从静态变量applicationContext中得到Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

}
