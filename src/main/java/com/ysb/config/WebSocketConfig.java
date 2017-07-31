package com.ysb.config;

import com.ysb.web.controller.websocket.MessageTransferHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Created by wushenjun on 2017/7/14.
 */
@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //允许连接的域,只能以http或https开头
        //String[] allowsOrigins = {"http://www.xxx.com"};
        registry.addHandler(messageTransferHandler(), "/messageTransferHandler");//.addInterceptors(new HandSocketshakeInterceptor());
        registry.addHandler(messageTransferHandler(), "/messageTransferHandler/sockjs").setAllowedOrigins("*").withSockJS();
    }

    @Bean
    public WebSocketHandler messageTransferHandler() {
        return new MessageTransferHandler();
    }
}
