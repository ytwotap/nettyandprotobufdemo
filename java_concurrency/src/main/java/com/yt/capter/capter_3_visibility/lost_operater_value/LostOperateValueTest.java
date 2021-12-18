package com.yt.capter.capter_3_visibility.lost_operater_value;

import org.junit.Test;

/**
 * @author: YT
 * @date: 2021/12/17/017
 */
public class LostOperateValueTest {
    public static void main(String[] args) {

    }

    @Test
    public void testLostValueCirculate() {
        int i = 0;
        while (i < 10) {
            lostValue();
            i++;
        }
    }

    public void lostValue() {
        MutableInteger mutableInteger = new MutableInteger();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(mutableInteger.get());
            }
        });
        thread.start();
        mutableInteger.set(2);
    }

}
