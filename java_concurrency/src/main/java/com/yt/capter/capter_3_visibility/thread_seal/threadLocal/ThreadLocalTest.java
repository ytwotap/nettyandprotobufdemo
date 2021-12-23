package com.yt.capter.capter_3_visibility.thread_seal.threadLocal;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * test use ThreadLocal
 * why to use it?
 * 对每个变量都分配一个对应的 local value
 * how to use it?
 * 在初始化的时候实现 threadLocal 之后线程调用 ThreadLocal 变量get方法
 * 和 ad-hoc 还有栈封闭的区别
 * 这个是通过java 的机制实现 变量和线程的绑定 上边的 两个是写法上的不同  感觉都查多 这个local 感觉用多了是不是耦合了？？
 * it can to do something?
 *  设置每个线程的线程名
 *  用于构建临时对象 在每次分配的时候 都重新构建这个临时对象  相对于 使用 new 出来的对象 避免了临时对象的逸出
 * @author: YT
 * @date: 2021/12/23/023
 */
@Slf4j
public class ThreadLocalTest {
    @Test
    public void testThreadLocalUse() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i<=10;i++){
            executorService.submit(() -> {
                log.info("this id is: [{}]", ThreadId.get());
            });
        }
    }
}

