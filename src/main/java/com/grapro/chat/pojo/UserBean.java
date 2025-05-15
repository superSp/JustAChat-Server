/**
 * Project Name:chat
 * File Name:UserBean
 * Package Name:com.grapro.chat.pojo
 * Date: 2023/2/3 14:30
 * Copyright (c) 2018, CCI All Rights Reserved.
 */
package com.grapro.chat.pojo;
/**
 * @author 超神的菠萝
 * date 2023-02-03
 */

import java.util.List;

/**
 * @author mdyy
 * @version 1.0.0
 * @className UserBean
 * @description TODO
 * @date 2023/2/3 14:30
 * @since JDK 1.8
 */
public class UserBean{
    private int user_id;
    private String user_name;
    private String user_pwd;


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    @Override
    public String toString() {
        return "{" +
                "user_id:" + user_id +
                ", user_name:'" + user_name + '\'' +
                ", user_pwd:'" + user_pwd + '\'' +
                '}';
    }
}