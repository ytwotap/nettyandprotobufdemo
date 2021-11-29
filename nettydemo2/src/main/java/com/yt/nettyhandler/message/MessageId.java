package com.yt.nettyhandler.message;

/**
 * @author: 杨涛
 * @date: 2021/11/26/026
 */

public enum MessageId {
    //聊天消息
    chatId(1),
    //显示用户信息
    UserId(2);


    int id;


    MessageId(int i) {
        this.id = i;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
