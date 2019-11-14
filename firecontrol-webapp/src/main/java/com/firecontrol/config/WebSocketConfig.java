package com.firecontrol.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Created by mariry on 2019/11/14.
 */
@Configuration
public class WebSocketConfig{

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }


//
//    /*
//    注册端点，发布或者订阅消息时需连接此端点
//    setAllowedOrigins 非必须，* 表示允许跨域
//    withSockjs 表示开始sockjs支持
//     */
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry reg) {
//        reg.addEndpoint("/tainbo/ws/{sid}").setAllowedOrigins("*").withSockJS();
//    }
//
//    /**
//     * 配置消息代理
//     * enableSimpleBroker 服务端推送给客户端的路径前缀
//     * setApplicationDestinationPrefixes  客户端发送数据给服务器端的一个前缀
//     */
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry reg) {
//        reg.enableSimpleBroker("/serverInit");
//        reg.setApplicationDestinationPrefixes("/clientInit");
//    }


}
