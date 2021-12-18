package com.yt.capter.capter_3_visibility.lost_operater_value;

import com.yt.capter.tool.NotThreadSafe;
import com.yt.capter.tool.ThreadSafe;

/**
 * thread is safe
 * @author: YT
 * @date: 2021/12/17/017
 */
@ThreadSafe
public class SynchronizedInteger {
    private int value;

    public synchronized int get() {
        return value;
    }

    public synchronized void set(int value) {
        this.value = value;
    }
}
