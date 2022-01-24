package com.yt.capter.capter_7_thread_close_and_cacel;

import com.yt.capter.tool.GuardedBy;
import com.yt.capter.tool.ThreadSafe;
import com.yt.capter.tool.TimeUtils;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 取消 运行的线程
 * 使用中断机制 实现 一个线程终止另一个线程
 *
 * 为啥取消
 *  需要有这个需求
 *      如 ：
 *          超过时间取消
 *          关闭任务或者服务器却笑
 *          用户操作取消
 *          错误操作 或者数据取消
 *   java 没有 抢占式的机制来停止线程 只能通过协商的方式取取消任务
 *
 *协作机制:
 *  设置 Cancellation Requested 标志
 * 遵循how when what 操作 流程
 * @author: YT
 * @date: 2022/1/22/022
 */
public class Demo1 {

    //test 素数
    @Test
    public void testPrime() throws InterruptedException {
        List<BigInteger> bigIntegers = aSecondOfPrimes();
        bigIntegers.forEach(key->{
            System.out.println(key);
        });
    }
    //调用
    List<BigInteger> aSecondOfPrimes() throws InterruptedException{
        PrimeGenerator generator = new PrimeGenerator();
        new Thread(generator).start();
        try {
            //when 在何时取消
            TimeUnit.SECONDS.sleep(1);
        }finally {
            //how 其他代码如何取消
            generator.cancel();
        }
        return generator.get();
    }
}

//案例 枚举素数（除了1 和 它 本身 不能 被其他的数整除）
//todo write code
@ThreadSafe
class PrimeGenerator implements Runnable {
    @GuardedBy("this")
    private final List<BigInteger> primes = new ArrayList<BigInteger>();
    private volatile boolean cancelled;

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        //what 取消后的操作 这里取消后的操作是停止执行素数
        while (!cancelled) {
            p = p.nextProbablePrime();
            synchronized (this) {
                primes.add(p);
            }
        }
    }

    public void cancel() {
        cancelled = true;
    }

    public synchronized List<BigInteger> get() {
        return new ArrayList<BigInteger>(primes);
    }
}





