package com.yt.nettyhandler.service.event.event;

import lombok.extern.slf4j.Slf4j;

/**
 * 服务器启动事件
 *
 * @author: 杨涛
 * @date: 2021/11/23/023
 */
@Slf4j
public class ServerStartLister implements IEventListener {

    @Override
    public void update(Object context) {
        log.info("server start");
        System.out.println("server start sout");
    }
}
