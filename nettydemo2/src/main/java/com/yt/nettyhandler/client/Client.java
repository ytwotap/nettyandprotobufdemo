package com.yt.nettyhandler.client;

import com.yt.nettyhandler.client.handler.NettyClientHandler;
import com.yt.nettyhandler.service.decode.MessageDecode;
import com.yt.nettyhandler.service.encode.MessageEncode;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.socksx.v4.Socks4ClientDecoder;

/**
 * 需要的依赖：
 * <dependency>
 * <groupId>io.netty</groupId>
 * <artifactId>netty-all</artifactId>
 * <version>4.1.52.Final</version>
 * </dependency>
 */
public class Client {


    public static void main(String[] args) throws InterruptedException {

        // 客户端只需要一个事件循环组，可以看做 BossGroup
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            // 创建客户端的启动对象
            Bootstrap bootstrap = new Bootstrap();
            // 配置参数
            bootstrap
                    // 设置线程组
                    .group(eventLoopGroup)
                    // 说明客户端通道的实现类（便于 Netty 做反射处理）
                    .channel(NioSocketChannel.class)
                    // handler()方法用于给 BossGroup 设置业务处理器
                    .handler(
                            // 创建一个通道初始化对象
                            new ChannelInitializer<SocketChannel>() {
                                // 向 Pipeline 添加业务处理器
                                @Override
                                protected void initChannel(
                                        SocketChannel socketChannel
                                ) throws Exception {
                                    socketChannel.pipeline().addLast("encode",
                                            new MessageEncode()
                                    );
                                    //test 发送消息
                                    socketChannel.pipeline().addLast(
                                            new NettyClientHandler()
                                    );
                                    // 可以继续调用 socketChannel.pipeline().addLast()
                                    // 添加更多 Handler
                                }
                            }
                    );

            System.out.println("client is ready...");

            // 启动客户端去连接服务器端，ChannelFuture 涉及到 Netty 的异步模型，后面展开讲
            ChannelFuture channelFuture = bootstrap.connect(
                    "127.0.0.1",
                    12312).sync();
            // 对通道关闭进行监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }


}