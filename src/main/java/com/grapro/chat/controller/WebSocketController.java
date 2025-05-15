/**
 * Project Name:chat
 * File Name:WebSocketController
 * Package Name:com.grapro.chat.controller
 * Date: 2023/2/11 19:13
 * Copyright (c) 2018, CCI All Rights Reserved.
 */
package com.grapro.chat.controller;
import com.grapro.chat.component.WebSocket;
import com.grapro.chat.pojo.MsgBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
/**
 * @author 超神的菠萝
 * date 2023-02-11
 */

/**
 *@className WebSocketController
 *@description TODO
 *@author mdyy
 *@date 2023/2/11 19:13
 *@version 1.0.0
 *@since JDK 1.8
 */
@RestController
@RequestMapping("/webSocket")
public class WebSocketController {
    @Autowired
    private WebSocket webSocket;
    @GetMapping("/sentMessage")
    public void sentMessage(String userId,String message){
        try {
            MsgBean msgBean=new MsgBean();
            msgBean.setToUser(Integer.parseInt(userId));
            msgBean.setTxt(message);
            webSocket.sendMessageByUserId(msgBean,message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}