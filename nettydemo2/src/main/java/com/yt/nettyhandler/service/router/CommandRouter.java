package com.yt.nettyhandler.service.router;



import com.yt.nettyhandler.message.Message;

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
