package com.yt.capter.capter_4_complete;

import com.yt.capter.tool.GuardedBy;

/**
 * @time 2022年1月5日
 * @author yt
 */
public class PrivateLock {
    //不同的锁对象 使用单一锁对象 使得外部代码不能够获取锁对象
    private final Object myLock = new Object();
    @GuardedBy("myLock")
    Wiget widget;

    void someMethod() {
        synchronized (myLock) {
            //访问或修改Widget的状态
        }
    }
}

