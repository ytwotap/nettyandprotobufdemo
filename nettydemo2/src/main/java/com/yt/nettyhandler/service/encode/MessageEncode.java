package com.yt.nettyhandler.service.encode;

import com.yt.nettyhandler.service.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * 消息解码
 * @author: 杨涛
 * @date: 2021/11/25/025
 */
@Slf4j
public class MessageEncode extends MessageToByteEncoder<Message> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        log.info("encode message:【{}】 ",msg);
        //todo 消息解码
    }
}
