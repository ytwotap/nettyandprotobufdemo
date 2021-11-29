package com.yt.nettyhandler.service.encode;

import com.yt.nettyhandler.message.Message;
import com.yt.nettyhandler.utils.MessagePool;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ReadOnlyByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

/**
 * 消息解码
 * @author: 杨涛
 * @date: 2021/11/25/025
 */
@Slf4j
public class MessageEncode extends MessageToByteEncoder<Message> {
    MessagePool messagePool;



    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        try {
            //todo 编码
            Message message = MessagePool.getMessage(msg.getId());
            //获取byty 数组
            byte[] encode = message.encode();
            //加入到context中
            out = Unpooled.wrappedBuffer(encode);
            System.out.println(msg.getId());
//            out.setInt(0, msg.getId());
            log.info("encode message:【{}】->编码后：【{}】 ", msg, out.toString());
        } catch (Exception e) {
            log.info("encode is erro for: 【{}】",msg);
        }

    }
}
