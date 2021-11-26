package com.yt.nettyhandler.client.handler;

import com.yt.nettyhandler.utils.KeyBoardReaderUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义一个 Handler，需要继承 Netty 规定好的某个 HandlerAdapter（规范）
 * InboundHandler 用于处理数据流入本端（客户端）的 IO 事件
 * InboundHandler 用于处理数据流出本端（客户端）的 IO 事件
 */
@Slf4j
public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    /**
     * 通道就绪时执行，执行这个方法，就是在tcp连接成功的时候执行了这个方法。
     * 实现启动新线程 并 进行 控制台监听。
     * @param ctx 上下文对象
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx)
            throws Exception {
        System.out.println("channel is connect");
        KeyBoardReaderUtils.init(ctx);
    }



    /**
     * 当通道有数据可读时执行
     *
     * @param ctx 上下文对象
     * @param msg 服务器端发送的数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        // 接收服务器端发来的数据

        System.out.println("server address: "
                + ctx.channel().remoteAddress());

        // ByteBuf 是 Netty 提供的类，比 NIO 的 ByteBuffer 性能更高
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("data from server: "
                + byteBuf.toString(CharsetUtil.UTF_8));
    }

    /**
     * 发生异常时执行
     *
     * @param ctx   上下文对象
     * @param cause 异常对象
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        // 关闭与服务器端的 Socket 连接
        ctx.channel().close();
    }

    /**
     * 注入到事件循环组 调用函数
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }
}