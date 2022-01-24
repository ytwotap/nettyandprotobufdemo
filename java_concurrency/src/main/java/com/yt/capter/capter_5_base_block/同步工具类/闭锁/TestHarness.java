package com.yt.capter.capter_5_base_block.同步工具类.闭锁;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * 同步工具类 使用 闭锁 实现  线程 启动 同步   并且在结束的时候进行相关计时
 * @author: YT
 * @date: 2022/1/19/019
 */
public class TestHarness {
    @SneakyThrows
    @Test
    public void test() {
        System.out.println(timeTasks(10, new Runnable() {
            @Override
            public void run() {
                System.out.println("h l w !");
            }
        }));
    }
    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);
        for (int i = 0; i < nThreads; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        startGate.await();
                        task.run();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        endGate.countDown();
                    }
                }
            }).start();
        }
        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }
}
