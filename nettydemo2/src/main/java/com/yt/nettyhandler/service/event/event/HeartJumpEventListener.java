package com.yt.nettyhandler.service.event.event;

import java.sql.Time;

/**
 * @author: 杨涛
 * @date: 2021/11/23/023
 */
public class HeartJumpEventListener implements IEventListener {

    @Override
    public void update(Object context) {
        System.out.println(new Time(System.currentTimeMillis()));
    }
}
