package com.yt.nettyhandler.service.message;

/**
 * @author: 杨涛
 * @date: 2021/11/25/025
 */
public class AbstractMessage implements Message{
    //消息处理队列
   private int processorId;


    @Override
    public int getId() {
        return processorId;
    }

    @Override
    public byte[] encode() {
        return new byte[0];
    }

    @Override
    public void decode(byte[] var1) {

    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public void setLength(int var1) {

    }

    @Override
    public void setSequence(short var1) {

    }

    @Override
    public short getSequence() {
        return 0;
    }

    @Override
    public int getHostId() {
        return 0;
    }
}
