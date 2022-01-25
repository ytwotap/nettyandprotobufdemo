package com.example.spider;

import com.example.spider.common.BizEnum;
import com.example.spider.core.task.AsyncProcessTask;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan(basePackages = {"com.alany.spider"}) //扫描该包路径下的所有spring组件 重复扫描了
@SpringBootApplication
public class SpiderApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(SpiderApplication.class, args);
        com.example.spider.core.task.AsyncProcessTask asyncProcessTask = com.example.spider.common.SpringContext.getBean(AsyncProcessTask.class);
        asyncProcessTask.initProxy();
        Thread.sleep(1000 * 30);
        asyncProcessTask.startProcessorsByBusiness(BizEnum.tabobao.getName());
    }

}

