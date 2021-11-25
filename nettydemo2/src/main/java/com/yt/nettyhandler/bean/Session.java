package com.yt.nettyhandler.bean;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import lombok.Data;

/**
 * 保留的一些连接信息
 * @author: 杨涛
 * @date: 2021/11/25/025
 */
@Data
public class Session {
    /**
     * 保留的交流的一些信息
     */
    private Channel channel;
    /**
     * 处理进程id
     */
    int queueId;

}
