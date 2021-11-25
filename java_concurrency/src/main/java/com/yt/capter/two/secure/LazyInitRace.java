package com.yt.capter.two.secure;

import com.yt.capter.tool.UnThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 * 先检查后执行  延迟初始化的竞态条件
 *
 * @author: 杨涛
 * @date: 2021/11/15/015
 */
@UnThreadSafe
@Slf4j
public class LazyInitRace {

    private ExpensiveObject instance = null;

    /**
     * 初始化 instance 带有延迟条件 instance != null;
     *
     * @return ExpensiveObject object instance
     */
    public  ExpensiveObject getInstance() {
        if (Objects.isNull(instance)) {
            //昂贵的初始化
            instance = new ExpensiveObject();
            log.info(Thread.currentThread().getName() + " init: " + this.instance);
        } else {
            log.info(Thread.currentThread().getName() + " not init: " + this.instance);
        }
        return instance;
    }

    /**
     * test 延迟初始化的竞态条件 导致的 instance != null; 没有生效
     */
    @Test
    public void test() throws InterruptedException {
        new Thread(() -> this.getInstance()).start();
        new Thread(() -> this.getInstance()).start();

        //主线程睡眠等待
        TimeUnit.MINUTES.sleep(10);
    }
}
