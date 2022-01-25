package com.example.spider.core.proxy;

import com.example.spider.bean.HttpProxy;
import com.example.spider.common.SpringContext;
import com.example.spider.core.http.UserAgentService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;

/**
 * Created by yangangui on 2018/12/5.
 */
@Component
public abstract class AbstractProxyFetcher {

    protected List<String> userAgentList = null;

    private Random random = new Random();

    public UserAgentService userAgentService = SpringContext.getBean(UserAgentService.class);

    private ProxyFetchFactory proxyFetchFactory = SpringContext.getBean(ProxyFetchFactory.class);
    //初始化 在 spring context 中 被 bean 实例化 就会调用这个方法 非常nice
    @PostConstruct
    public void init() {
        userAgentList = userAgentService.getUserAgentList();
        proxyFetchFactory.addService(this);
    }
    //获取代码名
    public abstract String getBusiness();
    //解析页面 并 获取生产 HttpProxy
    public abstract List<HttpProxy> fetchProxy();

    public String getRandomUserAgent(){
        return userAgentList.get(random.nextInt(userAgentList.size()));
    }

}
