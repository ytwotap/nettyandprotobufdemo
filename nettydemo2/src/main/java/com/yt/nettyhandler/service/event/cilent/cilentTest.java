package com.yt.nettyhandler.service.event.cilent;


import com.yt.nettyhandler.service.event.event.HeartJumpEventListener;
import com.yt.nettyhandler.service.event.event.ServerStartLister;
import com.yt.nettyhandler.service.event.manager.EventManager;
import com.yt.nettyhandler.service.event.type.ServerMonitorEventType;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: 杨涛
 * @date: 2021/11/23/023
 */
@Slf4j
public class cilentTest {
    public static void main(String[] args) throws InterruptedException {
        //心跳监听者
        HeartJumpEventListener heartJumpEventListener = new HeartJumpEventListener();
        ServerStartLister serverStartLister = new ServerStartLister();
        //增加监听事件到管理中心
        EventManager.addListener(ServerMonitorEventType.HEART_JUMP_MESSAGE, heartJumpEventListener);
        EventManager.addListener(ServerMonitorEventType.SERVICE_START, serverStartLister);


        //每隔5s触发心跳
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4,new ThreadFactory() {
            AtomicInteger threadNumber=new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                threadNumber.addAndGet(1);
                System.out.println("now thread number is:" + threadNumber);
                return new Thread(r,"用户服务线程-"+threadNumber);
            }
        });
        //执行心跳线程
        /**
         * scheduleAtFixedRate method allows us to run a task after a specified initial delay and then run it repeatedly with a certain period.
         * The period argument is the time measured between the starting times of the tasks, so the execution rate is fixed.
         */
        Future<?> submit = executorService.scheduleAtFixedRate(() -> {
            EventManager.touchListener(ServerMonitorEventType.HEART_JUMP_MESSAGE, null);
        },10,1,TimeUnit.SECONDS
        );
        TimeUnit.SECONDS.sleep(10);


        //执行服务器启动事件
        executorService.submit(() -> {
            int interval = 1;
            try {
                while (true) {
                    TimeUnit.SECONDS.sleep(interval);
                     EventManager.touchListener(ServerMonitorEventType.SERVICE_START, null);

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }
}
