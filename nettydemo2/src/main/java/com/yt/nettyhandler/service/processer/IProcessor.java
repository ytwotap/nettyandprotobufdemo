package com.yt.nettyhandler.service.processer;

import com.yt.nettyhandler.message.Message;

/**
 * 程序处理接口
 * @author: 杨涛
 * @date: 2021/11/25/025
 */
public interface IProcessor {
    void process(Message message);
}
