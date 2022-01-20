package com.yt.capter.capter_5_base_block.同步工具类.信号量;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 信号量test
 *
 * @author: YT
 * @date: 2022/1/20/020
 */
public class SparmoreTest {
    final static CountDownLatch endGate = new CountDownLatch(1);
    public static void main(String[] args) throws InterruptedException {

        BoundHashSet<String> stringBoundHashSet = new BoundHashSet<>(5);
        stringBoundHashSet.add("日你哈");
        stringBoundHashSet.add("搞莫子");
        stringBoundHashSet.add("拟好");
        stringBoundHashSet.add("钢厂");
        stringBoundHashSet.add("干啥子");
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stringBoundHashSet.remove("干啥子");
            System.out.println("释放 count");
            SparmoreTest.endGate.countDown();
            SparmoreTest.endGate.countDown();
        }).start();
        //阻塞调
        System.out.println("使用信号量形成阻塞队列");
        new Thread(() -> {
            AtomicBoolean isEnd = new AtomicBoolean(true);
            new Thread(() -> {
                try {
                    SparmoreTest.endGate.await();
                    System.out.println("endGate 阻塞释放");
                    isEnd.set(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            while (isEnd.get()) {
                System.out.println(isEnd.get());
//                    TimeUnit.SECONDS.sleep(1);
//                    System.out.print(".");
                String[] planets = {".", "..", "..."};
                String format = "\r%s ";
                for (String planet : planets) {
                    System.out.printf(format, planet);
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        //... oh dear
                    }
                }
            }
            stringBoundHashSet.remove("干啥子");
        }).start();
        stringBoundHashSet.add("干啥子");
        System.out.println("end 阻塞");
    }

}
