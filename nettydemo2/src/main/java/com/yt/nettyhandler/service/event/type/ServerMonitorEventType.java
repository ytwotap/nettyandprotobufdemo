package com.yt.nettyhandler.service.event.type;

/**
 * 事件监听类型
 * @author: 杨涛
 * @date: 2021/11/23/023
 */
public enum ServerMonitorEventType implements EventType {
    /**
     * 心跳
     */
    HEART_JUMP_MESSAGE,
    /**
     * 收到消息
     */
    GET_MESSAGE,
    /**
     * 消息返回事件
     */
    RETURN_MESSAGE,
    /**
     * 服务启动事件
     */
    SERVICE_START,
    /**
     * 服务退出事件
     */
    SERVER_EXIT;

}
