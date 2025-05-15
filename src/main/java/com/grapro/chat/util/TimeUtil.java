/**
 * Project Name:chat
 * File Name:TimeUtil
 * Package Name:com.grapro.chat.util
 * Date: 2023/2/28 18:41
 * Copyright (c) 2018, CCI All Rights Reserved.
 */
package com.grapro.chat.util;
/**
 * @author 超神的菠萝
 * date 2023-02-28
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *@className TimeUtil
 *@description TODO
 *@author mdyy
 *@date 2023/2/28 18:41
 *@version 1.0.0
 *@since JDK 1.8
 */
public class TimeUtil {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()));
        //获取当前时间戳,也可以是你自已给的一个随机的或是别人给你的时间戳(一定是long型的数据)
        long timeStamp = System.currentTimeMillis();
        //这个是你要转成后的时间的格式
        SimpleDateFormat sdff=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 时间戳转换成时间
        String sd = sdff.format(new Date(timeStamp));
        System.out.println(sd);//打印出你要的时间
    }
    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}