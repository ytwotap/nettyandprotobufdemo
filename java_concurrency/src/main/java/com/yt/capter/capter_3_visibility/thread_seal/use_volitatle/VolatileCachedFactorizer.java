package com.yt.capter.capter_3_visibility.thread_seal.use_volitatle;

import com.yt.capter.tool.ThreadSafe;

import javax.servlet.*;
import java.math.BigInteger;

/**
 * @author: YT
 * @date: 2021/12/23/023
 */
@ThreadSafe
public class VolatileCachedFactorizer implements Servlet{
    private volatile OneValueCache cache =
            new OneValueCache(null, null);

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse response) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = cache.getFactors(i);
        if (factors == null) {
            factors = factors(i);
            cache = new OneValueCache(i, factors);
        }
        encodeIntoResponse(response, factors);
    }

    private void encodeIntoResponse(ServletResponse response, BigInteger[] factors) {

    }

    private BigInteger[] factors(BigInteger i) {
        return new BigInteger[0];
    }

    private BigInteger extractFromRequest(ServletRequest req) {
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
