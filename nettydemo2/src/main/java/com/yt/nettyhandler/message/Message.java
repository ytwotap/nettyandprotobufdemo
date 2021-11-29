package com.yt.nettyhandler.message;

import io.netty.buffer.ByteBuf;

/**
 * 公共消息接口 实现了对消息的处理
 * 通过此接口可以对消息进行序列化
 * 通过
 * <pre>decode() </pre>
 * 和 encode 方法
 * 也能根据相应的接口通过getId()方法获取消息的唯一id.
 * @author: 杨涛
 * @date: 2021/11/24/024
 */
public interface Message {
    /**
     * get id
     *注意，此id 需要自己设置，必须是唯一的id
     * @return
     */
    int getId();

    /**
     * 编码
     *
     * @return
     */

    public byte[] encode();

    /**
     * 解码
     *
     *
     * @return 解码对象
     * @param var1
     */
   public Object decode(byte[] var1);


    int length();

    /**
     * 设置长度
     * @param var1
     */
    void setLength(int var1);


    void setSequence(short var1);

    short getSequence();

    default int getHostId() {
        return 0;
    }
}
