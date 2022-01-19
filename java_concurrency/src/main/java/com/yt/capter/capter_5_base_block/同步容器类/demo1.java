package com.yt.capter.capter_5_base_block.同步容器类;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * vector 可能产生 数组越界异常 ArrayIndexOutOfBoundsException
 * 需要使用同步器去同步Vector
 * @author: YT
 * @date: 2022/1/18/018
 */
public class demo1 {
    public static void main(String[] args) {
//        多线程调用test
        Vector<Integer> integers = new Vector<>();
        int count=1;
        while (count<=100) {
            integers.add(1);
            count++;
        }

        Thread thread = new Thread(()->{
            int time = 1;
            while (time<=5) {
                try {
                    getLast(integers);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                time++;
            }
        });
        thread.start();
        Thread threadx = new Thread(()->{
            int time = 1;
            while (time<=5) {
                try {
                    getLast(integers);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                time++;
            }
        });
        threadx.start();
        Thread thread2 = new Thread(()->{
            int time = 1;
            while (time<=5) {
                deleteLast(integers);
                time++;
            }
        });
        thread2.start();
        Thread thread3 = new Thread(()->{
            int time = 1;
            while (time<=5) {
                deleteLast(integers);
                time++;
            }
        });
        thread3.start();
    }

    // 程序清单 5-1 Vector 上可能导致混乱结果的符合操作
    public static Object getLast(Vector list) throws InterruptedException {
        int lastIndex = list.size() - 1;
        //增加出现错误的概率
        TimeUnit.SECONDS.sleep(1);
        //ArrayIndexOutOfBoundsException
        return list.get(lastIndex);
    }

    public static void deleteLast(Vector list) {
        int lastIndex = list.size() - 1;
        list.remove(lastIndex);
    }
}
