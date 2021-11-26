package com.yt.nettyhandler.message;

/**
 *
 * @author: 杨涛
 * @date: 2021/11/25/025
 */
public abstract class AbstractMessage implements Message{
    //消息处理队列
   private int processorId;




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
