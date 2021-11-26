package com.yt.nettyhandler.service.net;

import com.yt.nettyhandler.service.comsummer.NetworkConsumer;
import com.yt.nettyhandler.service.event.event.IEventListener;
import com.yt.nettyhandler.message.Message;
import io.netty.channel.*;
import lombok.extern.slf4j.Slf4j;

/**
 * 消息执行者
 * @author: 杨涛
 * @date: 2021/11/25/025
 */
@Slf4j
public class MessageExecutor extends ChannelHandlerAdapter {
    //todo consumer 和 listerner 啥时候赋值
    private NetworkConsumer consumer;
    protected IEventListener listener;

    public MessageExecutor(NetworkConsumer consumer, IEventListener listener) {
        this.consumer = consumer;
        this.listener = listener;
    }

    /**
     * 消息处理 消息传入对应的消费者消费
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof Message) {
            this.consumer.consumer(ctx.channel(),(Message) msg);
        }
        else {
            log.info("不能识别的消息：{} ",msg.getClass().getName());
        }

    }
}
