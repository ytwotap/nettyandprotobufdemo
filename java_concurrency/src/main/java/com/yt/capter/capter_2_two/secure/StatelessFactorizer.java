package com.yt.capter.capter_2_two.secure;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 *
 *无状态 一定是线程安全的
 * @author: 杨涛
 * @date: 2021/11/15/015
 */
public class StatelessFactorizer implements Servlet {


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

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
        encodeIntoResponse(servletResponse, factors);
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
