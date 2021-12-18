package com.yt.capter.capter_2_two.secure;


import java.util.concurrent.TimeUnit;

/**
 * TODO
 * 非常贵的花销的class
 * @author: 杨涛
 * @date: 2021/11/15/015
 */
public class ExpensiveObject {
    public ExpensiveObject() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
