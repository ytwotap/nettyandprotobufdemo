package com.example.spider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.alany.spider"}) //扫描该包路径下的所有spring组件
@SpringBootApplication
public class SpiderApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(SpiderApplication.class, args);
        com.alany.spider.core.task.AsyncProcessTask asyncProcessTask = com.alany.spider.common.SpringContext.getBean(com.alany.spider.core.task.AsyncProcessTask.class);
        asyncProcessTask.initProxy();
        Thread.sleep(1000 * 30);
        asyncProcessTask.startProcessorsByBusiness(com.alany.spider.common.BizEnum.tabobao.getName());
    }

}

