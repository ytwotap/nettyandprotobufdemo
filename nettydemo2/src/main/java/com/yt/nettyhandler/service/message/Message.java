package com.yt.nettyhandler.service.message;

/**
 * 公共消息接口
 *
 * @author: 杨涛
 * @date: 2021/11/24/024
 */
public interface Message {
    /**
     * get id
     *
     * @return
     */
    int getId();

    /**
     * 编码
     *
     * @return
     */

    byte[] encode();

    /**
     * 解码
     *
     * @return
     */
    void decode(byte[] var1);


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
