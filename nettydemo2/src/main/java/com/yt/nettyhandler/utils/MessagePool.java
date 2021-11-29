package com.yt.nettyhandler.utils;

import com.yt.nettyhandler.message.ChatMessage;
import com.yt.nettyhandler.message.Message;
import com.yt.nettyhandler.proto.Person;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 编码pool
 * @author: 杨涛
 * @date: 2021/11/26/026
 */
public class MessagePool {
    public static ConcurrentHashMap<Integer, Message> MessagePool;

    static {
        MessagePool = new ConcurrentHashMap<>();
        init();
    }


    /**
     * 初始化消息池
     * todo
     */
    private static void init() {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setProto(Person.newBuilder().build());
        register(chatMessage);
    }

    /**
     * todo 存放的为message class, 而不是一个对象 ， 对象太占用内存了 ，容易造成堆内存溢出。
     * 注入消息到消息池中
     * @param message
     */
    static void register(Message message) {
        //传入的id可以为静态的消息
        MessagePool.put(message.getId(), message);
    }

    /**
     * 从消息池中移除消息
     * @param message
     */
   static void unregister(Message message) {
       MessagePool.remove(message.getId());
    }

    /**
     * get message
     */
    public static Message getMessage(Integer id) {
        Message message = MessagePool.get(id);
        return message;

    }


}
