package com.yt.nettyhandler.service.router;



import com.yt.nettyhandler.service.message.Message;

import java.nio.channels.Channel;

/**
 *
 * @author: 杨涛
 * @date: 2021/11/24/024
 */
public interface CommandRouter {
    /**
     * 派遣到相应的processer进行处理
     */
    void dispatch(int processorId, Message message);
}
