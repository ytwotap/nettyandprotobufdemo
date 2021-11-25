package com.yt.nettyhandler.bean;

import lombok.Data;

/**
 * 使用者pojo
 * @author: 杨涛
 * @date: 2021/11/25/025
 */
@Data
public class user {
    String name;
    int id;
    String message;
    //保留的用户连接通通道
    Session session;

}
