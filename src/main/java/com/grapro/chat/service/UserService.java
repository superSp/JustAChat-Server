/**
 * Project Name:chat
 * File Name:UserService
 * Package Name:com.grapro.chat.service
 * Date: 2023/2/3 15:20
 * Copyright (c) 2018, CCI All Rights Reserved.
 */
package com.grapro.chat.service;
/**
 * @author 超神的菠萝
 * date 2023-02-03
 */

import com.grapro.chat.pojo.BaseBean;

/**
 * @author mdyy
 * @version 1.0.0
 * @className UserService
 * @description TODO
 * @date 2023/2/3 15:20
 * @since JDK 1.8
 */
public interface UserService {
    BaseBean findUser(String name, String pwd);

    BaseBean RegistUser(String name, String pwd);

    BaseBean findFriend(String user_id);

    BaseBean addFriend(String userName,String friendName);

}