package com.yt.capter.capter_5_base_block.同步容器类;

import lombok.SneakyThrows;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * 同步容器 + synchronize
 * 同步容器要遵循同步策略 在客户端加锁实现同步
 * vector 可能产生 数组越界异常 ArrayIndexOutOfBoundsException
 * 需要使用同步器去同步Vector
 *
 * @author: YT
 * @date: 2022/1/18/018
 */
public class demo2 {
    // 同步容器要遵循同步策略 在客户端加锁实现同步
    static Vector<Integer> list = new Vector<>();

    @SneakyThrows
    public static void main(String[] args) {

        int count = 1;
        while (count <= 30) {
            list.add(1);
            count++;
        }

        Thread thread = new Thread(() -> {
            int time = 1;
            while (time <= 5) {
                try {
                    getLast(list);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                time++;
            }
        });
        thread.start();
        Thread threadx = new Thread(() -> {
            int time = 1;
            while (time <= 5) {
                try {
                    getLast(list);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                time++;
            }
        });
        threadx.start();
        Thread thread2 = new Thread(() -> {
            int time = 1;
            while (time <= 5) {
                deleteLast(list);
                time++;
            }
        });
        thread2.start();
        Thread thread3 = new Thread(() -> {
            int time = 1;
            while (time <= 5) {
                deleteLast(list);
                time++;
            }
        });
        thread3.start();
        TimeUnit.MICROSECONDS.sleep(50);
        //安全的遍历
        synchronized (list) {
            for (int i = 0; i < list.size(); i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(list.get(i));
            }
        }
//        不安全的遍历
        for (int i = 0; i < list.size(); i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(list.get(i));
        }
    }

    // 程序清单 5-1 Vector 上可能导致混乱结果的符合操作
    public static Object getLast(Vector list) throws InterruptedException {
        //使用synchronized同步后台
        synchronized (list) {
            int lastIndex = list.size() - 1;
            //增加出现错误的概率
            TimeUnit.MICROSECONDS.sleep(50);

            //ArrayIndexOutOfBoundsException
            return list.get(lastIndex);
        }
    }

    @SneakyThrows
    public static void deleteLast(Vector list) {
        synchronized (list) {
            int lastIndex = list.size() - 1;
            TimeUnit.MICROSECONDS.sleep(50);
            list.remove(lastIndex);
        }
    }
}
