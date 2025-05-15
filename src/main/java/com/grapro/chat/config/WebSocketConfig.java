/**
 * Project Name:chat
 * File Name:WebSocketConfig
 * Package Name:com.grapro.chat.config
 * Date: 2023/2/11 19:09
 * Copyright (c) 2018, CCI All Rights Reserved.
 */
package com.grapro.chat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
/**
 * @author 超神的菠萝
 * date 2023-02-11
 */

/**
 * @author mdyy
 * @version 1.0.0
 * @className WebSocketConfig
 * @description TODO
 * @date 2023/2/11 19:09
 * @since JDK 1.8
 */
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        // 在此处设置bufferSize
        container.setMaxTextMessageBufferSize(512000);
        container.setMaxBinaryMessageBufferSize(512000);
        container.setMaxSessionIdleTimeout(15 * 60000L);
        return container;
    }
}