package com.grapro.chat.dao;

import com.grapro.chat.pojo.MsgBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 超神的菠萝
 * date 2023-02-19
 */
@Mapper
public interface MsgDao {
    void saveMsg(MsgBean msgBean);

    List<MsgBean> getMsgs(String toUserId);

    void deleteMsg(int id);
}
