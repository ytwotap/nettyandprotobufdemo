package com.yt.capter.capter_2_two.secure.lock;

import com.yt.capter.tool.UnThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Objects;

/**
 * 两个原子操作 在多线程中 容易出现 两者操作 前后不一致 使用synchronized 保证线程安全性
 * 在加锁的基础上细分操作 将只是可能错误的位置同步下就可以了。
 *
 * @author: 杨涛
 * @date: 2021/11/15/015
 */
@UnThreadSafe
public class SafeCachingFactorizer2 implements Servlet {
    private BigInteger lastNumber;
    private BigInteger[] lastFactors;
    private long hits;
    private long chacheHits;


    public SafeCachingFactorizer2(BigInteger lastNumber, BigInteger[] lastFactors, long hits, long chacheHits) {
        this.lastNumber = lastNumber;
        this.lastFactors = lastFactors;
        this.hits = hits;
        this.chacheHits = chacheHits;
    }


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * 缓存操作 根据 从缓存数组中拿到以前缓存的数据 如果 有 就 使用  缓存结果
     * 没有 就进行计算 之后 缓存
     * 使用互斥锁保证线程安全性
     *
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger i = extractFromRequest(servletRequest);
        BigInteger[] factors = null;

        synchronized (this) {
            ++hits;
            //缓存击中
            if (i.equals(lastNumber)) {
                ++chacheHits;
                factors = lastFactors.clone();
            }
        }
        //缓存未击中
        if (factors == null) {
            //进行因数分解 获取缓存。
            factors = factor(i);
            synchronized (this) {
                this.lastNumber = i;
                this.lastFactors = factors.clone();
            }
        }


    }


    private BigInteger[] factor(BigInteger i) {
        return new BigInteger[0];
    }

    private void encodeIntoResponse(ServletResponse servletResponse, BigInteger[] bigIntegers) {
    }

    private BigInteger extractFromRequest(ServletRequest servletRequest) {
        return null;
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
