package com.yt.nettyhandler.utils;

import com.yt.nettyhandler.write.WriteLister;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *键盘阅读事件工具类
 * @author: 杨涛
 * @date: 2021/11/26/026
 */
@Slf4j
public class KeyBoardReaderUtils {
    /**
     * 初始化调用，执行键盘监听
     * @param ctx 通道
     */
    public static void init(ChannelHandlerContext ctx) {
        ExecutorService executorService = Executors.newSingleThreadExecutor(new ThreadFactory() {
            AtomicInteger num = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                num.addAndGet(1);
                log.info("new thread : {}",num);
                return new Thread(r, "键盘监控线程-" + num);
            }
        });
        //循环执行runnable接口
        executorService.submit(new WriteLister().builder().context(ctx).build());
        log.info("thread is scheduleAtFixedRate");
    }

}
