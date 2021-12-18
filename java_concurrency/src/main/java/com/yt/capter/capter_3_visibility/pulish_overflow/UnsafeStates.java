package com.yt.capter.capter_3_visibility.pulish_overflow;

import com.yt.capter.tool.NotThreadSafe;

/**
 *
 * @author: YT
 * @date: 2021/12/17/017
 */
@NotThreadSafe
public class UnsafeStates {
    private String[] states = new String[]{
            "AK","AL","AR"
    };
    //use this method will get all array value . that is not sagfe .
    public String[] getStates() {
        return states;
    }
}
