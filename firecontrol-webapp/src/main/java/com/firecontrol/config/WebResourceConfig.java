package com.firecontrol.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by mariry on 2019/11/18.
 */

@Configuration
public class WebResourceConfig extends WebMvcConfigurerAdapter {


        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            //不拦截静态资源
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
            //上传的图片在D盘下的OTA目录下，访问路径如：http://localhost:8081/OTA/d3cf0281-bb7f-40e0-ab77-406db95ccf2c.jpg
            //其中OTA表示访问的前缀。"file:D:/OTA/"是文件真实的存储路径
            registry.addResourceHandler("/opt/screenshot/**").addResourceLocations("file:/opt/screenshot/");


            super.addResourceHandlers(registry);

        }


}
