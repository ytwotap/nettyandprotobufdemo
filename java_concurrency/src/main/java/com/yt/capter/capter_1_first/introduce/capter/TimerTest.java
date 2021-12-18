package com.yt.capter.capter_1_first.introduce.capter;

import org.junit.Test;
import sun.nio.ch.ThreadPool;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;

import static java.lang.Thread.sleep;

/**
 * TODO
 * test timer class reference concurrent question
 * @author: 杨涛
 * @date: 2021/11/12/012
 */
public class TimerTest {
    private int timerName=1;

    public void runTime() {
        //can use lambda implement
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                System.out.println("run it method ");
            }
        };

        //执行延迟任务 运行上面的run()方法
        long l = timerTask.scheduledExecutionTime();
        Timer timer = new Timer("timer "+timerName++);
        timer.schedule(timerTask,1000);
    }

    /**
     * this is test timer's timeTask invoke not clock object can reference thread security problem!
     * todo not complete this code
     */
    @Test
    public void testRunTimer() {
        Thread thread = new Thread(this::runTime);
        Thread threadForLoopWait = new Thread(() ->{
//            int time;

            while (true) {
                System.out.println("wait run:");
//                sleep(1000);

            }

        });

        thread.start();

    }
}
