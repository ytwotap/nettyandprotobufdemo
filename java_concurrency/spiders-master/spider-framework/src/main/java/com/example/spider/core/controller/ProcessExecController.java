package com.example.spider.core.controller;

import com.example.spider.common.SpringContext;
import com.example.spider.core.task.AsyncProcessTask;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by alany on 2019/7/29.
 */
@RestController
@RequestMapping("/exec")
public class ProcessExecController {

    private AsyncProcessTask asyncProcessTask = SpringContext.getBean(AsyncProcessTask.class);

    @RequestMapping(value = "/all")
    public void execAll(){
        asyncProcessTask.startAllProcessors();
    }

    @RequestMapping(value = "/biz")
    public void execByBiz(String business){
        asyncProcessTask.startProcessorsByBusiness(business);
    }

}
