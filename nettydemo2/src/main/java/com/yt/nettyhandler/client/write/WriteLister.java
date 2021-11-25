package com.yt.nettyhandler.client.write;

import com.yt.nettyhandler.service.message.Message;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @author: 杨涛
 * @date: 2021/11/25/025
 */
@Data
public class WriteLister  implements Runnable{
    ChannelHandlerContext context;
    /**
     * 获取键盘字符串，并发送到服务器
     */
    @Override
    public void run() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String msg = br.readLine();
            //写入到channel中，
            context.writeAndFlush(
                    // Unpooled 类是 Netty 提供的专门操作缓冲区的工具
                    // 类，copiedBuffer 方法返回的 ByteBuf 对象类似于
                    // NIO 中的 ByteBuffer，但性能更高
                    Unpooled.copiedBuffer(
                            msg,
                            CharsetUtil.UTF_8
                    ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
