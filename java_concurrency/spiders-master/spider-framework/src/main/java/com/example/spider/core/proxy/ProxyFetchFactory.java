package com.example.spider.core.proxy;

import com.example.spider.bean.HttpProxy;
import com.example.spider.common.SpringContext;
import com.example.spider.core.cache.CacheService;
import com.example.spider.utils.StringUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 代理工厂类
 * 调用 buildProxy 会构建相应的工厂
 * Created by alany on 2019/07/26.
 */
@Service
public class ProxyFetchFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProxyFetchFactory.class);

    private static final int MAX_TIMEOUT_MS = 2000;//ms
    //会调用 init
    private List<AbstractProxyFetcher> proxyServices = new ArrayList<>();

    private CacheService<String, HttpHost> cacheService = SpringContext.getBean(CacheService.class);

    public List<HttpHost> getProxyHosts() {
        return cacheService.getValues();
    }

    /**
     * 在springcontext 中被 AbstractProxyFetcher 调用
     * @param proxyService
     */
    public void addService(AbstractProxyFetcher proxyService) {
        proxyServices.add(proxyService);
    }

    /**
     * 构建工厂代理
     */
    public void buildProxy() {
        List<HttpProxy> proxies = null;
        //存放http 实例
        List<Future<HttpProxy>> futures = new ArrayList<>();
        for (AbstractProxyFetcher proxyService : proxyServices) {
            if (!proxyService.getBusiness().equals("github")){
                continue;
            }
            try {
                proxies = proxyService.fetchProxy();
            } catch (Exception e) {
                LOGGER.error("fetch " + proxyService.getBusiness() + " proxy meet error", e);
                continue;
            }
            if (proxies != null && !proxies.isEmpty()) {
                //多线程执行检查相关的值
                ExecutorService es = Executors.newFixedThreadPool(10);
                for (final HttpProxy proxy : proxies) {

//                    futures.add((Future<HttpProxy>) es.submit(new Runnable() {
//                        @Override
//                        public void run() {
//                            proxy.setValid(checkHost(proxy));
//                        }
//                    }));
                    //使用future 延迟获取结果
                    Future<HttpProxy> submit = (Future<HttpProxy>) es.submit(
                            () -> proxy.setValid(checkHost(proxy))
                    );
                    futures.add(submit);
                }
                //强制关闭 todo 为啥要强制关闭？？
                es.shutdown();
                for (Future<HttpProxy> future : futures) {
                    try {
                        future.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        LOGGER.info("valid host total size: " + getProxyHosts().size());
    }

    /**
     * 检查host是否可用
     * 主从线程是不是执行更快 ？？  或者网络请求需要启动单个线程
     * @param httpProxy
     * @return
     */
    private boolean checkHost(final HttpProxy httpProxy) {
        boolean isValid = false;
        ExecutorService executor = Executors.newSingleThreadExecutor();
        FutureTask<String> future = new FutureTask<String>(new Callable<String>() {//使用Callable接口作为构造参数
            @Override
            public String call() {
                //搜索任务异步处理
                return request(httpProxy);
            }
        });
        executor.execute(future);
        String content = null;
        try {
            content = future.get(MAX_TIMEOUT_MS, TimeUnit.MILLISECONDS); //在执行超时时间内获取结果
        } catch (Exception e) {
            future.cancel(true);
        } finally {
            executor.shutdown();
        }
        isValid = StringUtils.isNotBlank(content) && content.indexOf("百度") > 0;

        LOGGER.info(httpProxy.getProvider() + " - " + httpProxy.getAddress() + " : " + isValid);
        if (isValid) {
            cacheService.put(httpProxy.getAddress() + ":" + httpProxy.getPort(), httpProxy.toHost());
            LOGGER.info("cache size:" + cacheService.size());
        }
        return isValid;
    }

    private String request(HttpProxy httpProxy) {
        String content = null;
        InetSocketAddress addr = null;
        URLConnection conn = null;
        InputStream in = null;
        try {
            //Proxy类代理方法
            URL url = new URL("http://www.baidu.com");
            // 创建代理服务器
            addr = new InetSocketAddress(httpProxy.getAddress(), httpProxy.getPort());
            Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); // http 代理
            conn = url.openConnection(proxy);
            in = conn.getInputStream();
            content = IOUtils.toString(in);
        } catch (Exception e) {
            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content;
    }
}
