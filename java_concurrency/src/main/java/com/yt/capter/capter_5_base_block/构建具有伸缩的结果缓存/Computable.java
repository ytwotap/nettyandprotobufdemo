package com.yt.capter.capter_5_base_block.构建具有伸缩的结果缓存;

import com.yt.capter.tool.Operator;
import com.yt.capter.tool.TimeUtils;
import lombok.Synchronized;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * 构建可伸缩的缓存de'mo  this is use 使用synchronized
 * 1. 使用synchronized
 * 2. 使用 ConcurrentHashmap() 实现并发
 * 3. 使用FutureTask class
 * 实现未完成任务缓存 防止2个线程同时请求缓存 都没有获取到 之后两个线程都进行了计算 就会 出现一个结果被两次计算 照成计算资源的浪费
 *
 * @author: YT
 * @date: 2022/1/20/020
 */
public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}

class ExpensiveFunction implements Computable<Integer, BigInteger> {

    @Override
    public BigInteger compute(Integer arg) throws InterruptedException {
        //长时间的计算 通过sleep来模拟相关的技计算
        arg = arg * arg * arg * arg;
        return new BigInteger(arg.toString());
    }
}

/**
 * 缓存计算
 * 使用synchronized
 *
 * @param <A>
 * @param <V>
 */
class Memoizerl<A, V> implements Computable<A, V> {
    private final Map<A, V> cache = new HashMap<>();
    private final Computable<A, V> C;

    Memoizerl(Computable<A, V> c) {
        C = c;
    }

    /**
     * 计算
     * 现在缓存中找 没有 在计算放在缓存中
     *
     * @param arg
     * @return
     * @throws InterruptedException
     */
    @Override
    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (Objects.isNull(result)) {
            result = C.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}

/**
 * 缓存计算
 * 使用ConcurrentHashMap实现并发 不用加锁
 *
 * @param <A>
 * @param <V>
 */
class Memoizerl2<A, V> implements Computable<A, V> {
    private final Map<A, V> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> C;


    Memoizerl2(Computable<A, V> c) {
        C = c;
    }

    /**
     * 计算
     * 现在缓存中找 没有 在计算放在缓存中
     *
     * @param arg
     * @return
     * @throws InterruptedException
     */
    @Override
    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (Objects.isNull(result)) {
            result = C.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}

/**
 * 缓存计算
 * 使用FutureTask 优化 计算相同结果
 *
 * @param <A>
 * @param <V>
 */
class Memoizerl3<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> C;

    Memoizerl3(Computable<A, V> c) {
        C = c;
    }

    /**
     * 计算
     * 现在缓存中找 没有 在计算放在缓存中
     *
     * @param arg
     * @return
     * @throws InterruptedException
     */
    @Override
    public V compute(A arg) throws InterruptedException {
        Future<V> result = cache.get(arg);
        if (Objects.isNull(result)) {
            //和runnable 差不多的方法 但是可以返回结果
            Callable<V> eval = new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return  C.compute(arg);
                }
            };

            FutureTask<V> ft = new FutureTask<V>(eval);
            result = ft;
            cache.put(arg, ft);
            //调用 c.compute
            ft.run();
        }
        try {
            return result.get();
        } catch (Exception e) {
            throw new RuntimeException(e.getCause());
        }
    }
}

class Test {
    public static void main(String[] args) throws InterruptedException {
        TimeUtils.operatorTime(new Operator() {
            @Override
            public boolean active() throws InterruptedException {
                Memoizerl<Integer, BigInteger> m1 = new Memoizerl<>(new ExpensiveFunction());
                ExecutorService executorService = Executors.newCachedThreadPool();
                CopyOnWriteArrayList<Integer> integers = new CopyOnWriteArrayList<>();
                int i = 0;
                while (i < 500) {
                    integers.add(i);
                    integers.add(1);

                    i++;
                }
                test(m1, executorService, integers);
                return true;
            }
        });


        TimeUtils.operatorTime(new Operator() {
            @Override
            public boolean active() throws InterruptedException {
                Memoizerl2<Integer, BigInteger> m1 = new Memoizerl2<>(new ExpensiveFunction());
                CopyOnWriteArrayList<Integer> integers = new CopyOnWriteArrayList<>();
                int i = 0;
                while (i < 500) {
                    integers.add(i);
                    integers.add(1);

                    i++;
                }
                ExecutorService executorService = Executors.newCachedThreadPool();

                test(m1, executorService, integers);

                return true;
            }
        });


        //futureTask 运行
        TimeUtils.operatorTime(new Operator() {
            @Override
            public boolean active() throws InterruptedException {
                Memoizerl3<Integer, BigInteger> m1 = new Memoizerl3<>(new ExpensiveFunction());
                CopyOnWriteArrayList<Integer> integers = new CopyOnWriteArrayList<>();
                int i = 0;
                while (i < 500) {
                    integers.add(i);
                    integers.add(1);
                    i++;
                }
                ExecutorService executorService = Executors.newCachedThreadPool();

                test(m1, executorService, integers);

                return true;
            }
        });



        //futureTask 运行


//        TimeUtils.operatorTime(new Operator() {
//            @Override
//            public boolean active() throws InterruptedException {
//                Memoizerl3<> m1 = new Memoizerl3<Integer,Future<BigInteger>>(new Computable<Integer, Future<Future<BigInteger>>>() {
//                    @Override
//                    public Future<Future<BigInteger>> compute(Integer arg) throws InterruptedException {        //长时间的计算 通过sleep来模拟相关的技计算
//
//                        Future<Future<BigInteger>> futureCompletableFuture = new FutureTask<>(new Callable<Future<BigInteger>>() {
//                            @Override
//                            public Future<BigInteger> call() throws Exception {
//                                TimeUnit.SECONDS.sleep(1);
//                                return
//                            }
//                        });
//                        return futureCompletableFuture;
//                    }
//                });
//                m1.compute(1);
//                m1.compute(1);
//                m1.compute(1);
//                m1.compute(1);
//                m1.compute(1);
//                m1.compute(1);
//                return true;
//            }
//        });
    }

    private static void test(Computable<Integer, BigInteger> m1, ExecutorService executorService, List list) {
        list.forEach(value -> {
            executorService.submit(() -> {


                try {
                    m1.compute((Integer) value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        });
}
}

