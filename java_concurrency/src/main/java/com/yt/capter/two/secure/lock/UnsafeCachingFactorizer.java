package com.yt.capter.two.secure.lock;

import com.yt.capter.tool.UnThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 *两个原子操作 在多线程中 容易出现 两者操作 前后不一致
 * @author: 杨涛
 * @date: 2021/11/15/015
 */
@UnThreadSafe
public class UnsafeCachingFactorizer implements Servlet {
    private final AtomicReference<BigInteger> lastNumber
            = new AtomicReference<BigInteger>();
    private final AtomicReference<BigInteger[]> lastFactors
            = new AtomicReference<BigInteger[]>();


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
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger i = extractFromRequest(servletRequest);
        //判断是否有缓存
        if (Objects.equals(i, lastNumber)) {
            encodeIntoResponse(servletResponse, lastFactors.get());
        } else {
            //没有缓存 计算后设置缓存
            BigInteger[] factors = factor(i);
            lastNumber.set(i);
            lastFactors.set(factors);
            encodeIntoResponse(servletResponse, factors);
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
