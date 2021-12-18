package com.yt.capter.capter_3_visibility.volatitle;

import org.junit.Test;

/**
 * @author: YT
 * @date: 2021/12/17/017
 */
public class Asleep {
    volatile static boolean asleep;

    public static void main(String[] args) {
        while (!asleep) {
            countSomeSheep();
        }
    }

    private static void countSomeSheep() {
    }
}
