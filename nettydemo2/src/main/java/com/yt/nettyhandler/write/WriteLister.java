package com.yt.nettyhandler.write;

import com.yt.nettyhandler.message.ChatMessage;
import com.yt.nettyhandler.message.Message;
import com.yt.nettyhandler.proto.Person;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author: 杨涛
 * @date: 2021/11/25/025
 */

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class WriteLister  implements Runnable{
    ChannelHandlerContext context;
    /**
     * 获取键盘字符串，并发送到服务器
     */
    @SneakyThrows
    @Override
    public void run() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String msg = null;
        try {
            //退出
            while (!StringUtils.equals(msg,"exit")) {
                msg = br.readLine();
                Person.Builder person = Person.newBuilder().setName("client").setMessage(msg).setUuid(111);
                //写入到channel中，
                context.writeAndFlush(
                        // Unpooled 类是 Netty 提供的专门操作缓冲区的工具
                        // 类，copiedBuffer 方法返回的 ByteBuf 对象类似于
                        // NIO 中的 ByteBuffer，但性能更高
                        Unpooled.copiedBuffer(
                                person.build().toString(),
                                CharsetUtil.UTF_8
                        ));
                Person build = person.build();
                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setProto(build);
                context.writeAndFlush(chatMessage);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            br.close();
        }
    }
}
