/**
 * Project Name:chat
 * File Name:UserController
 * Package Name:com.grapro.chat.controller
 * Date: 2023/2/3 14:50
 * Copyright (c) 2018, CCI All Rights Reserved.
 */
package com.grapro.chat.controller;
/**
 * @author 超神的菠萝
 * date 2023-02-03
 */

import com.grapro.chat.pojo.BaseBean;
import com.grapro.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mdyy
 * @version 1.0.0
 * @className UserController
 * @description TODO
 * @date 2023/2/3 14:50
 * @since JDK 1.8
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/login/select/{name}/{pwd}")
    private BaseBean findUser(@PathVariable("name") String name, @PathVariable("pwd") String pwd) {
        return userService.findUser(name, pwd);
    }

    @GetMapping("/regist/instert/{name}/{pwd}")
    private BaseBean registUser(@PathVariable("name") String name, @PathVariable("pwd") String pwd) {
        return userService.RegistUser(name, pwd);
    }

    @GetMapping("/friend/select/{userid}")
    private BaseBean findUser(@PathVariable("userid") String userid) {
        return userService.findFriend(userid);
    }

    @GetMapping("/friend/insert/{userName}/{friendName}")
    private BaseBean addFriend(@PathVariable("userName") String userName,@PathVariable("friendName") String friendName) {
        return userService.addFriend(userName,friendName);
    }
}