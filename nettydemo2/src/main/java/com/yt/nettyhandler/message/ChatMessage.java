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
 * @author: 杨涛
 * @date: 2021/11/25/025
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatMessage extends AbstractMessage{
    private Person chat;

    /**
     * 返回消息定义的枚举常量值得id
     * @return
     */
    @Override
    public  int getId() {
        return MessageId.chatId.ordinal();
    }

    @Override
    public byte[] encode(Object o) {
        if (o instanceof Person) {
            Person person = (Person) o;
            return person.toByteArray();
        } else {
            log.error("not decode Person Message:【{}】",o.getClass().getName());
            //todo 不能编码如何处理；
            return null;
        }
    }

    @Override
    public Object decode(byte[] var1) {
        try {
            return Person.parseFrom(var1);
        } catch (InvalidProtocolBufferException e) {
            log.info("decode message--parseClass:【{}】 is error,exception:【{}】",this.getClass().getName(), Arrays.toString(e.getStackTrace()));
        }
        return var1;
    }
}
