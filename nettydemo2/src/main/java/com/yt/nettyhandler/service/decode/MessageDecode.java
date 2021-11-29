package com.yt.nettyhandler.service.decode;

import com.yt.nettyhandler.message.Message;
import com.yt.nettyhandler.utils.MessagePool;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

/**
 * 消息解码器
 * @author: 杨涛
 * @date: 2021/11/25/025
 */
@Slf4j
public class MessageDecode extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        try {
            //todo 如何解码  使用 , id 是如何来的 。（通过通用解码器解码 ） 先设置为0 这里的0 是message的在pool中的位置。
            Message message = MessagePool.getMessage(0);
            if (Objects.isNull(message)) {
                log.info("MessagePool can't read messsage, id is:【{}】",in.readInt());
            }
            //获取byty 数组
            byte[] req = new byte[in.readableBytes()];
            Object decode = message.decode(req);
            //加入到对象队列中
            out.add(decode);
            in.release();
            log.info("message decode:【{}】", decode);
        } catch (Exception e) {
            log.error("decode error about:{}",in);
        }
    }
}
