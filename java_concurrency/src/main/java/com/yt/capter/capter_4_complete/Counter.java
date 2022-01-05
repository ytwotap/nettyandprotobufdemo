package com.yt.capter.capter_4_complete;

import com.yt.capter.tool.GuardedBy;
import com.yt.capter.tool.ThreadSafe;

import java.util.HashSet;
import java.util.Set;

/**
 * 线程安全的计数
 */
@ThreadSafe
public final class Counter {
    @GuardedBy
    private long value = 0;

    public synchronized long getValue() {
        return value;
    }

    public synchronized long increment() {
        if (value == Long.MAX_VALUE) {
            throw new IllegalStateException("counter overflow");
        }
        return ++value;
    }
}

