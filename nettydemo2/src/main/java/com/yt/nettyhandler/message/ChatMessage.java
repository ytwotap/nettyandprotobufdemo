package com.yt.nettyhandler.message;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yt.nettyhandler.proto.Person;
import com.yt.nettyhandler.proto.chatProto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 对ChatMessage 解码
 *
 * @author: 杨涛
 * @date: 2021/11/25/025
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatMessage extends AbstractMessage {
    private Person proto;

    /**
     * 返回消息定义的枚举常量值得id
     *
     * @return
     */
    @Override
    public int getId() {
        return MessageId.chatId.getId();
    }

    @Override
    public byte[] encode() {
        return this.proto.toByteArray();
    }



    /**
     * 编码
     *
     * @param var1
     * @return
     */
    @Override
    public Object decode(byte[] var1) {
        try {
            this.proto = Person.parseFrom(var1);
        } catch (InvalidProtocolBufferException e) {
            log.info("decode message--parseClass:【{}】 is error,exception:【{}】", this.getClass().getName(), Arrays.toString(e.getStackTrace()));
        }
        return var1;
    }
}
