/**
 * Project Name:chat
 * File Name:HelloController
 * Package Name:com.grapro.chat.controller
 * Date: 2023/2/3 11:59
 * Copyright (c) 2018, CCI All Rights Reserved.
 */
package com.grapro.chat.controller;
/**
 * @author 超神的菠萝
 * date 2023-02-03
 */

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mdyy
 * @version 1.0.0
 * @className HelloController
 * @description TODO
 * @date 2023/2/3 11:59
 * @since JDK 1.8
 */
//https://blog.csdn.net/weixin_56993128/article/details/125993827 websocket部署参考
//https://blog.csdn.net/weixin_43757027/article/details/124454843 websocket部署参考
@RestController
public class HelloController {
    @GetMapping("/hello")
    private String hello() {
        return "hello";
    }
}