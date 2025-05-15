/**
 * Project Name:chat
 * File Name:UserService
 * Package Name:com.grapro.chat.service
 * Date: 2023/2/3 14:50
 * Copyright (c) 2018, CCI All Rights Reserved.
 */
package com.grapro.chat.service.impl;
/**
 * @author 超神的菠萝
 * date 2023-02-03
 */

import com.grapro.chat.dao.UserDao;
import com.grapro.chat.pojo.BaseBean;
import com.grapro.chat.pojo.FriendBean;
import com.grapro.chat.pojo.UserBean;
import com.grapro.chat.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mdyy
 * @version 1.0.0
 * @className UserService
 * @description TODO
 * @date 2023/2/3 14:50
 * @since JDK 1.8
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public BaseBean findUser(String name, String pwd) {
        UserBean userBean = userDao.findUser(name, pwd);
        BaseBean baseBean = new BaseBean();
        if (userBean == null) {
            baseBean.setCode(0);
            baseBean.setMsg("The user does not exist");
            return baseBean;
        }
        baseBean.setCode(1);
        baseBean.setMsg("The request succeeded");
        baseBean.setData(userBean);
        return baseBean;
    }

    @Override
    public BaseBean addFriend(String userName, String friendName) {
        BaseBean baseBean = new BaseBean();

        UserBean userBean = userDao.findUserByName(friendName);
        if (null == userBean) {
            baseBean.setCode(0);
            baseBean.setMsg("The query does not have this user");
            return baseBean;
        }
        int userId=userDao.getUserIdByName(userName);
        List<UserBean> friendBean=userDao.findFriend(String.valueOf(userId));
        for (UserBean bean : friendBean) {
            if (bean.getUser_name().equals(friendName)){
                baseBean.setCode(0);
                baseBean.setMsg("The user is already your friend");
                return baseBean;
            }
        }

        int isSuccess = userDao.addFriend(userName, friendName);
        if (isSuccess != 1) {
            baseBean.setCode(0);
            baseBean.setMsg("Failed to add user");
            return baseBean;
        }
        baseBean.setCode(1);
        baseBean.setMsg("The request succeeded");
        return baseBean;
    }

    @Override
    public BaseBean RegistUser(String name, String pwd) {
        UserBean userBean = userDao.findUser(name, pwd);
        BaseBean baseBean = new BaseBean();
        if (userBean != null) {
            baseBean.setCode(0);
            baseBean.setMsg("The username already exists, please set it again");
            return baseBean;
        }

        int user_id = userDao.registUser(name, pwd);
        baseBean = new BaseBean();
        if (-1 == user_id) {
            baseBean.setCode(0);
            baseBean.setMsg("Registration failed");
            return baseBean;
        }
        baseBean.setCode(1);
        baseBean.setMsg("Registration successful");
        return baseBean;
    }

    @Override
    public BaseBean findFriend(String user_id) {
        List<UserBean> bean = userDao.findFriend(user_id);
        BaseBean baseBean = new BaseBean();
        if (bean == null || 0 == bean.size()) {
            baseBean.setCode(0);
            baseBean.setMsg("No data yet");
            return baseBean;
        }
        baseBean.setCode(1);
        baseBean.setMsg("The request succeeded");
        baseBean.setData(bean);
        return baseBean;
    }
}