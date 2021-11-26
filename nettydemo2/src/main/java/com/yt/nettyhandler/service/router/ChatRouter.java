package com.yt.nettyhandler.service.router;

import com.yt.nettyhandler.message.Message;
import com.yt.nettyhandler.service.processer.ChatProcessor;

import java.util.HashMap;
import java.util.List;

/**
 * @author: 杨涛
 * @date: 2021/11/25/025
 */
public class ChatRouter implements CommandRouter{
    HashMap<Integer, List<ChatProcessor>> processor = new HashMap<Integer, List<ChatProcessor>>();
    @Override
    public void dispatch(int processorId, Message message) {

    }
}
