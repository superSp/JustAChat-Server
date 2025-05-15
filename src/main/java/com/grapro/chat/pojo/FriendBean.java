/**
 * Project Name:chat
 * File Name:FriendBean
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
 * @className FriendBean
 * @description TODO
 * @date 2023/2/3 14:30
 * @since JDK 1.8
 */
public class FriendBean {
    private int id;
    private int user_id;
    private int friend_id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(int friend_id) {
        this.friend_id = friend_id;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", user_id:" + user_id +
                ", friend_id:" + friend_id +
                '}';
    }
}