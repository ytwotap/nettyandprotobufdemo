package com.yt.nettyhandler.service.comsummer;

import com.yt.nettyhandler.message.Message;
import io.netty.channel.Channel;


/**
 * @author: 杨涛
 * @date: 2021/11/25/025
 */
public interface NetworkConsumer {
    void consumer(Channel channel, Message message);

}
