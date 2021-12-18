package com.yt.capter.capter_3_visibility.lost_operater_value;

import com.yt.capter.tool.NotThreadSafe;

/**
 * @author: YT
 * @date: 2021/12/17/017
 */
@NotThreadSafe
public class MutableInteger {
    private int value;

    public int get() {
        return value;
    }

    public void set(int value) {
        this.value = value;
    }
}
