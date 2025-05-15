package com.grapro.chat.dao;

import com.grapro.chat.pojo.FriendBean;
import com.grapro.chat.pojo.UserBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 超神的菠萝
 * date 2023-02-03
 */
@Mapper
public interface UserDao {
    UserBean findUser(String name, String pwd);

    UserBean findUserByName(String name);
    int registUser(String name, String pwd);
    int addFriend(String userName, String friendName);
    int getUserIdByName(String name);
    List<UserBean> findFriend(String user_id);
}
