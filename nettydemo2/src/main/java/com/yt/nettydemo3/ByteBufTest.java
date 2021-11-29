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
     * 读取
     */
    @Test
    public void testRead() {
        ByteBuf directBuffer = Unpooled.directBuffer(10);
        int i = 1;
        //写入
        while (directBuffer.isWritable()) {
            directBuffer.writeByte(i++);
        }

        while (directBuffer.isReadable()) {
            System.out.println(directBuffer.readByte());
        }
    }


}
