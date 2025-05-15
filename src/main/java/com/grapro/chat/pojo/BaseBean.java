/**
 * Project Name:chat
 * File Name:BaseBean
 * Package Name:com.grapro.chat.pojo
 * Date: 2023/2/3 17:08
 * Copyright (c) 2018, CCI All Rights Reserved.
 */
package com.grapro.chat.pojo;
/**
 * @author 超神的菠萝
 * date 2023-02-03
 */

/**
 *@className BaseBean
 *@description TODO
 *@author mdyy
 *@date 2023/2/3 17:08
 *@version 1.0.0
 *@since JDK 1.8
 */
public class BaseBean {
    private int code;
    private String msg;
    private Object data;
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}