package com.yt.nettyhandler.service.handler;

import com.yt.nettyhandler.message.Message;

/**
 * 消息处理基类
 * @author: 杨涛
 * @date: 2021/11/24/024
 */
public abstract class AbstractMessageHandler<T extends Message> implements MessageHandler{
    Message message;

    /**
     * 运行action方法 处理message;
     */
    @Override
    public void run() {
        this.action(message);
    }

    /**
     * 处理message
     * @param message
     */
    protected abstract void action(Message message);
}
