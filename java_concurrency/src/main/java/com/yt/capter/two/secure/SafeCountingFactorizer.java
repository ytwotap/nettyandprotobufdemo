package com.yt.capter.two.secure;

import com.yt.capter.tool.UnThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 原子性 线程安全的 race condition
 * count 当被多个线程操作时候 存在竞态条件
 *
 * @author: 杨涛
 * @date: 2021/11/15/015
 */
@UnThreadSafe
public class SafeCountingFactorizer implements Servlet {
    //访问计数器 使用线程安全的原子变量
    private final AtomicLong count = new AtomicLong(0);

    public long getCount() {
        return count.get();
    }


    /**
     * not less state servlet
     * state less class must thread secure!
     *
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger i = extractFromRequest(servletRequest);
        BigInteger[] factors = factor(i);
        //每次访问计数器+1 底层计数研究
        count.incrementAndGet();
        encodeIntoResponse(servletResponse, factors);
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    private void encodeIntoResponse(ServletResponse servletResponse, BigInteger[] factors) {

    }

    private BigInteger[] factor(BigInteger i) {
        return new BigInteger[0];
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
