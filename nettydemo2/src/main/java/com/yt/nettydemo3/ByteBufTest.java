package com.yt.nettydemo3;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * test byteBuf
 * @author: 杨涛
 * @date: 2021/11/26/026
 */
@Slf4j
public class ByteBufTest {
    /**
     * 堆缓冲区
     */
    @Test
    public void testHeapByteBuf() {
        ByteBuf heapBuf = Unpooled.buffer(10);
        if (heapBuf.hasArray()) {
            byte[] array = heapBuf.array();
            int offset = heapBuf.arrayOffset() + heapBuf.readerIndex();
            int length = heapBuf.readableBytes();
            //0,0
            log.info("offset:{},length:{}", offset, length);
            log.info(Arrays.toString(array));
        }
    }

    /**
     * 直接内存缓存区
     */
    @Test
    public void testDirectByteBuf() {
        ByteBuf directBuffer = Unpooled.directBuffer(10);
        directBuffer.writeByte(1);
        if (!directBuffer.hasArray()) {
            int length = directBuffer.readableBytes();
            byte[] array = new byte[length];
            ByteBuf bytes = directBuffer.getBytes(directBuffer.readerIndex(), array);
            //0,0
            log.info("offset:{},length:{}",bytes.readerIndex() , array.length);
            log.info(Arrays.toString(array));
        }
    }



    /**
     * 读取 和 显示 index 操作index等
     */
    @Test
    public void testRead() {
        ByteBuf directBuffer = Unpooled.directBuffer(50);
        int i = 1;
        //写入
        while (directBuffer.isWritable()) {
            directBuffer.writeByte(i++);
        }

        while (directBuffer.isReadable()&&directBuffer.readerIndex()<20) {
            System.out.println(directBuffer.readByte());
        }
        log.info("操作index 前------------------------");
        int i1 = directBuffer.readableBytes();
        log.info("readableBytes:"+String.valueOf(i1));
        log.info(String.valueOf("readerIndex:"+directBuffer.readerIndex()));
        //清楚读取的
        log.info("清除reader index...");
        directBuffer.discardReadBytes();

        log.info("操作index后 ------------------------");
        log.info("readableBytes:"+String.valueOf(directBuffer.readableBytes()));
        log.info(String.valueOf("readerIndex:"+directBuffer.readerIndex()));

        log.info("执行 clear ------------------------");
        directBuffer.clear();
        log.info("readableBytes:"+String.valueOf(directBuffer.readableBytes()));
        log.info(String.valueOf("readerIndex:"+directBuffer.readerIndex()));

    }


    @Test
    public void testGetAndSet() {
        ByteBuf directBuffer = Unpooled.directBuffer();
        soutLog(directBuffer,"before");
        directBuffer.setByte(1,11);
        soutLog(directBuffer," setByte");
        directBuffer.writeByte(11);
        directBuffer.writeByte(22);
        soutLog(directBuffer,"writeByte");
        directBuffer.writeByte(22);
        //只能设置 writeIndex - read index 中间已有未读的字符串
        directBuffer.setByte(directBuffer.readerIndex(),55);
        soutLog(directBuffer,"writeByte");

    }

    /**
     * 输出相应的操作
     * @param directBuffer
     * @param operateString
     */
    public void soutLog(ByteBuf directBuffer,String operateString) {
        log.info("执行--{} ...",operateString);
        log.info("readableBytes:"+String.valueOf(directBuffer.readableBytes()));
        log.info(String.valueOf("readerIndex:"+directBuffer.readerIndex()));
        while (directBuffer.isReadable()) {
            log.info(String.valueOf(directBuffer.readByte()));
        }
    }

}
