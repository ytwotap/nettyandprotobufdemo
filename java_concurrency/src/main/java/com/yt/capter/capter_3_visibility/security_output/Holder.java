package com.yt.capter.capter_3_visibility.security_output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.TimeUnit;

/**
 * @author yt
 * @version 1.0
 * @Classname Holder
 * @Description
 * @date 2021-12-26 -11:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Holder {
    private int n;

    public void assertSanity() throws InterruptedException {
        int a = n;
        TimeUnit.SECONDS.sleep(4); //实现线程切换过程 在sleep 中 n 的值被改变
        if (a != n) { //多线程将会导致 两个读取状态不一致 但不是这个Holder 类的问题
            throw new AssertionError("This statement is false");
        }
        else {
            System.out.println("This statement is ture");
        }
    }
}
