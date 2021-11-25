package com.yt.nettyhandler.service.event.manager;

import com.yt.nettyhandler.service.event.event.HeartJumpEventListener;
import com.yt.nettyhandler.service.event.event.IEventListener;
import com.yt.nettyhandler.service.event.type.EventType;
import com.yt.nettyhandler.service.event.type.ServerMonitorEventType;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 事件管理
 *
 * @author: 杨涛
 * @date: 2021/11/23/023
 */
@Slf4j
public class EventManager {
    /**
     * 线程安全的map , 可以下执行的时候添加事件调用，从而保证了线程的安全
     */
    public static final Map<EventType, List<IEventListener>> EVENT_LIST = new ConcurrentHashMap<>();


    public static  Boolean addListener(ServerMonitorEventType type, IEventListener eventListener) {
        try {
            //获取监听者
            List<IEventListener> iEventListeners = EVENT_LIST.computeIfAbsent(type, k -> new ArrayList<>());
            //增加到监听者
            iEventListeners.add(eventListener);
            return true;
        } catch (Exception e) {
            log.info("addListener is exception in EventManager" + e.getStackTrace().toString());
            return false;
        }
    }


    public static  Boolean removeListener(EventType type, IEventListener eventListener) {
        try {
            //获取监听者
            List<IEventListener> iEventListeners = EVENT_LIST.computeIfAbsent(type, k -> new ArrayList<>());
            //移除相应的事件
            //todo 移除事件test
            iEventListeners.remove(eventListener);
            return true;
        } catch (Exception e) {
            log.info("removeListener is exception in EventManager" + e.getStackTrace().toString());
            return false;
        }
    }


    public static  Boolean touchListener(EventType type, Object context) {
        try {
            //获取事件类型对应的事件列表
            List<IEventListener> iEventListeners = EVENT_LIST.get(type);
            //对列表中的每个事件执行update()
            for (IEventListener iEventListener : iEventListeners) {
                iEventListener.update(context);
            }
            return true;
        } catch (Exception e) {
            log.info("touchListener is exception in EventManager" + e.getStackTrace().toString());
            return false;
        }

    }

}