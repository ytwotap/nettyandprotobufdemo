package com.yt.capter.first.introduce.capter;

import com.yt.capter.tool.ThreadSafe;
import com.yt.capter.tool.UnThreadSafe;
import org.junit.Test;

/**
 *
 * race condition question
 * 竞态条件问题
 * @author: 杨涛
 * @date: 2021/11/12/012
 */
public class ThreadSafeQuestion {
    private  int value=10;
    @UnThreadSafe
    public int getNext() {
        System.out.println(++this.value);
        return this.value;
    }


    @ThreadSafe
    public synchronized int getNext2() {
        System.out.println(++this.value);
        return this.value;
    }

    /**
     * test getNext() refrence race conditon question
     */
    @Test
    public void testGetNext() {
        //多线程条件
        //分别调用getNext()
        Thread thread = new Thread(() -> getNext());
        Thread thread2 = new Thread(() -> getNext());

        //启动多线程
        thread.start();
        thread2.start();
    }

    /**
     * test getNext2() not refrence race conditon question
     */
    @Test
    public void testGetNext2() {
        //多线程条件
        //分别调用getNext()
        Thread thread = new Thread(() -> getNext2());
        Thread thread2 = new Thread(() -> getNext2());

        //启动多线程
        thread.start();
        thread2.start();
    }
}
