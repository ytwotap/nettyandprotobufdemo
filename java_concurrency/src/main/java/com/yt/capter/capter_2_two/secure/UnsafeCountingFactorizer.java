package com.yt.capter.capter_2_two.secure;

import com.yt.capter.tool.UnThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 *
 *原子性验证 不是线程安全的 race condition
 *count 当被多个线程操作时候 存在竞态条件
 * @author: 杨涛
 * @date: 2021/11/15/015
 */
@UnThreadSafe
public class UnsafeCountingFactorizer implements Servlet {
    //访问计数器
    private long count = 0;




    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * not less state servlet
     * state less class must thread secure!
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger i=extractFromRequest(servletRequest);
        BigInteger[] factors=factor(i);
        //每次访问计数器+1
        ++count;
        encodeIntoResponse(servletResponse, factors);
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
