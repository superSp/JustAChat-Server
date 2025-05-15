/**
 * Project Name:chat
 * File Name:WebSocket
 * Package Name:com.grapro.chat.component
 * Date: 2023/2/11 19:11
 * Copyright (c) 2018, CCI All Rights Reserved.
 */
package com.grapro.chat.component;

import com.google.gson.Gson;
import com.grapro.chat.content.MsgType;
import com.grapro.chat.dao.MsgDao;
import com.grapro.chat.pojo.MsgBean;
import com.grapro.chat.util.ApplicationContextRegister;
import com.grapro.chat.util.ImageUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
/**
 * @author 超神的菠萝
 * date 2023-02-11
 */

/**
 * @author mdyy
 * @version 1.0.0
 * @className WebSocket
 * @description TODO
 * @date 2023/2/11 19:11
 * @since JDK 1.8
 */
@ServerEndpoint(value = "/websocket/{userId}")
@Component
public class WebSocket {
    private final static Logger logger = LogManager.getLogger(WebSocket.class);
    private Gson gson = new Gson();
    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的
     */

    private static int onlineCount = 0;

    /**
     * concurrent包的线程安全Map，用来存放每个客户端对应的MyWebSocket对象
     */
    private static ConcurrentHashMap<String, WebSocket> webSocketMap = new ConcurrentHashMap<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */

    private Session session;
    private String userId;


    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        for (String id : webSocketMap.keySet()) {
            if (userId.equals(id)) {
                logger.info("用户{}已经存在,将替换之前的session", userId);
                webSocketMap.remove(id);
            }
        }
        this.session = session;
        this.userId = userId;
        //加入map
        webSocketMap.put(userId, this);
        addOnlineCount();           //在线数加1
        logger.info("用户{}连接成功,当前在线人数为{}", userId, webSocketMap.keySet().size() + "");
        sendOfflineMsg(userId);
    }

    private void sendOfflineMsg(String userId) {
        MsgDao msgDao = ApplicationContextRegister.getApplicationContext().getBean(MsgDao.class);
        List<MsgBean> msgBeans = msgDao.getMsgs(userId);
        if (msgBeans == null && msgBeans.size() == 0) {
            return;
        }
        for (MsgBean msgBean : msgBeans) {
            try {
                msgBean.setBase64Image(ImageUtil.imgToBase64(msgBean.getImg_path()));
                msgBean.setImg_path("");
                msgDao.deleteMsg(msgBean.getId());
                msgBean.setId(-1);
                sendMessageByUserId(msgBean, gson.toJson(msgBean));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        //从map中删除
        webSocketMap.remove(userId);
        subOnlineCount();           //在线数减1
        logger.info("用户{}关闭连接！当前在线人数为{}", userId, getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        logger.info("来自客户端用户：{} 消息:{}", userId, message);

        MsgBean msgBean = gson.fromJson(message, MsgBean.class);
        String from_user = String.valueOf(msgBean.getFromUser());
        if(webSocketMap.get(from_user)==null){
            return;
        }
        webSocketMap.get(from_user).sendMessageByUserId(msgBean, message);
        //群发消息
        /*for (String item : webSocketMap.keySet()) {
            try {
                webSocketMap.get(item).sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }

    /**
     * 发生错误时调用
     *
     * @OnError
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("用户错误:" + this.userId + ",原因:" + error.getMessage());
        error.printStackTrace();
    }

    /**
     * 向客户端发送消息
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }

    /**
     * 通过userId向客户端发送消息
     */
    public void sendMessageByUserId(MsgBean msgBean, String message) throws IOException {
        logger.info("服务端发送消息到{},消息：{}", msgBean.getToUser() + "", message);
        if (webSocketMap.containsKey(String.valueOf(msgBean.getToUser()))) {
            webSocketMap.get(String.valueOf(msgBean.getToUser())).sendMessage(message);
        } else {
            logger.error("用户{}不在线，将消息保存到本地", msgBean.getToUser() + "");
            saveMsgToLocal(msgBean);
        }

    }

    private void saveMsgToLocal(MsgBean msgBean) {
        MsgDao msgDao = ApplicationContextRegister.getApplicationContext().getBean(MsgDao.class);
        String savePath = "";
        if (msgBean.getMsgType() != MsgType.TEXT) {
            savePath = "D:\\JavaProjects\\chat\\pics\\" +
                    msgBean.getToUser() + System.currentTimeMillis() + ".jpg";
            ImageUtil.base64ToFile(msgBean.getBase64Image(), savePath);
            msgBean.setImg_path(savePath);
        }
        msgDao.saveMsg(msgBean);
    }

    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message) throws IOException {
        for (String item : webSocketMap.keySet()) {
            try {
                webSocketMap.get(item).sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocket.onlineCount--;
    }

}