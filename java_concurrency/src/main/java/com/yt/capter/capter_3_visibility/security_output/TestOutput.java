package com.yt.capter.capter_3_visibility.security_output;

import jdk.internal.dynalink.support.BottomGuardingDynamicLinker;
import lombok.Lombok;

import javax.swing.*;
import java.sql.Time;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author yt
 * @version 1.0
 * @Classname TestOutput
 * @Description
 * @date 2021-12-26 -11:39
 */
public class TestOutput {
    public static void main(String[] args) throws InterruptedException {
        final NotSecurityOutput notSecurityOutput = new NotSecurityOutput();
        final ExecutorService executorService =
                Executors.newCachedThreadPool();
        executorService.submit(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            try {
                notSecurityOutput.init();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                if (Objects.nonNull(notSecurityOutput.getHolder())) {
                    notSecurityOutput.getHolder().assertSanity();
                }else {
                    throw new NullPointerException("this handler is null");
                }
            } catch (Exception e) {
                System.out.println("erro");
                e.printStackTrace();
            }
        });
//        while (true) {
//            TimeUnit.SECONDS.sleep(1);
//        }


    }
}
