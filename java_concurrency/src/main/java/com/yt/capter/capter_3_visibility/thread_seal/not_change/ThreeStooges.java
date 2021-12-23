package com.yt.capter.capter_3_visibility.thread_seal.not_change;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: YT
 * @date: 2021/12/23/023
 */
public class ThreeStooges {
    private final Set<String> stooges = new HashSet<String>();

    public ThreeStooges() {
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
    }

    public boolean isStooge(String name) {
        return stooges.contains(name);
    }
}
