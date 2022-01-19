package com.yt.capter.capter_5_base_block.并发容器;

import com.yt.capter.capter_4_complete.car_find.Point;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.security.Key;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * 并发容器 是啥?
 *  同步容器不支持在多线程中操作 哼多操作需要客户端加锁来解决多线程访问呢问题
 *
 *  并发容器为多线程设计的容器 通过更小粒度的加锁来保证可见性和性能伸缩性
 *  常见的并发容器 ConcurrentMap 是常见的并发容器 郑家了一些符合操作  如
 * @author: YT
 * @date: 2022/1/19/019
 */
public class demo1 {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private class Point {
        private int x;
        private int y;
    }

    /**
     * hashMap已经实现了常用的原子操作 所以 需要多线程同步的地方实现这个就可以了
     */
    @Test
    public void testConcurrentHashMap() {
        ConcurrentHashMap<Integer, Point> map = new ConcurrentHashMap<>();
        Point point = new Point(1, 2);
        map.put(1, point);
        map.computeIfAbsent(1, key-> point);
        Point p2 = new Point(3, 4);
        map.computeIfAbsent(2, key-> p2);
        map.values().forEach(value-> System.out.println(value.toString()));
    }


    //Queue Test

    @Test
    public void testConcurrentLinkedQueue() {
        ConcurrentLinkedQueue<Point> queue = new ConcurrentLinkedQueue<>();
        //insert to queue
        queue.offer(new Point(1, 2));
        queue.offer(new Point(3, 4));
        //peek 方法 看 队列头部对象
        System.out.println(queue.peek().toString());
        System.out.println(queue.peek().toString());
        System.out.println(queue.peek().toString());
        //poll 移除头部指针
        System.out.println(queue.poll().toString());
        System.out.println(queue.poll().toString());
        // if queue is null and return null ;
        System.out.println(queue.poll().toString());

    }
    //BlockingQueue test
    @Test
    public void testBlockingQueue() throws InterruptedException {
        BlockingDeque<Point> queue = new LinkedBlockingDeque<>();
        //insert to queue
        queue.putFirst(new Point(1, 2));
        queue.putFirst(new Point(3, 4));
        //peek 方法 看 队列头部对象
        System.out.println(queue.peek().toString());
        System.out.println(queue.peek().toString());
        System.out.println(queue.peek().toString());
        //poll 移除头部指针
        System.out.println(queue.takeFirst().toString());
        System.out.println(queue.takeFirst().toString());

        //新建线程生产 point 对象
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                queue.putFirst(new Point(6, 7));
            } catch (Exception e) {
                e.printStackTrace();
            }


        }).start();
        // if queue is null and return null ;
        System.out.println(queue.takeFirst().toString());
        System.out.println(queue.takeFirst().toString());

    }
}
