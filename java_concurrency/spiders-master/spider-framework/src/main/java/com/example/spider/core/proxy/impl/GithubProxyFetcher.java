package com.example.spider.core.proxy.impl;

import com.example.spider.bean.HttpProxy;
import com.example.spider.bean.HttpResult;
import com.example.spider.common.SpringContext;
import com.example.spider.core.http.HttpRequest;
import com.example.spider.core.proxy.AbstractProxyFetcher;
import com.example.spider.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by alany on 2019/7/26.
 */
@Service
public class GithubProxyFetcher extends AbstractProxyFetcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(GithubProxyFetcher.class);
    private static final String PROXY_LIST_URL = "https://raw.githubusercontent.com/fate0/proxylist/master/proxy.list";

    private HttpRequest httpRequest = SpringContext.getBean(HttpRequest.class);

    @Override
    public String getBusiness() {
        return "github";
    }

    @Override
    public List<HttpProxy> fetchProxy() {
        //todo 这个可以优化 使用多线程获取代理
        List<HttpProxy> list = new CopyOnWriteArrayList<>();
        Map<String, Object> headers = new HashMap<>();
        headers.put("User-Agent", getRandomUserAgent());
        LOGGER.info("request doGet()");
        HttpResult result = httpRequest.setUrl(PROXY_LIST_URL).setHeaders(headers).doGet();
        if (result == null || StringUtils.isBlank(result.getContent())) {
            return list;
        }
        String[] lines = result.getContent().split("\\n");
        //多线程执行
        ExecutorService executor = Executors.newCachedThreadPool();
        if (lines != null && lines.length > 0) {
            //多线程执行调用处理请求
            //todo change this thread .
            for (String line : lines) {
            executor.submit(()->{
                extracted(list, line);
            });
            }
        }
        LOGGER.info("fetch [" + getBusiness() + "] proxy list size=" + list.size());
        return list;
    }

    private void extracted(List<HttpProxy> list, String line) {
        try {
            JSONObject json = JSON.parseObject(line);
            int port = json.getInteger("port");
            String type = json.getString("type");
            JSONArray addrArray = json.getJSONArray("export_address");
            if (addrArray == null || addrArray.size() < 1) {
                return;
            }
            for (int i = 0; i < addrArray.size(); i++) {
                String address = addrArray.getString(i);
                if (StringUtils.isBlank(address) || "unknown".equalsIgnoreCase(address)) {
                    continue;
                }
                HttpProxy httpProxy = new HttpProxy(address, port);
                if (StringUtils.isNotBlank(type)) {
                    httpProxy.setType(type.toLowerCase());
                }
                httpProxy.setProvider(getBusiness());
                list.add(httpProxy);
            }
        } catch (Exception e) {
            LOGGER.error("fetch proxy meet error: ", e);
        }
    }
}
