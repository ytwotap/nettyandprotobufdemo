package com.yt.nettyhandler.service.handler.chat;

import com.yt.nettyhandler.message.Message;

/**
 * 聊天管理api 管理各种api的实现
 * @author: 杨涛
 * @date: 2021/11/24/024
 */
public class ChatManager {
    private static final ChatManager chatManager=new ChatManager();

    ChatManager() {

    }

    /**
     * 返回Manager实例
     * @return 实例
     */
    public ChatManager getInstance() {
        return chatManager;
    }

    /**
     * 获取聊天请求消息
     * @return
     */
    public Message getReqChatMessage() {
        return null;
    }

    /**
     * 回复聊天信息
     */
    public Message returnResChatMessage() {
        return null;
    }



}
