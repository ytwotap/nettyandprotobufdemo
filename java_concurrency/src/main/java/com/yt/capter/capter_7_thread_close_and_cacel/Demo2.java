package com.yt.capter.capter_7_thread_close_and_cacel;

import com.yt.capter.tool.GuardedBy;
import com.yt.capter.tool.ThreadSafe;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 中断操作
 *
 * @author: YT
 * @date: 2022/1/24/024
 */
public class Demo2 {
    @Test
    public void test() {
        new Thread();
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





