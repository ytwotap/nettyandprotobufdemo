package com.example.spider.core.http;

import com.sun.xml.internal.ws.util.StreamUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by yangangui on 2018/12/5.
 */
@Service
@Slf4j
public class UserAgentService {

    public List<String> userAgentList = new ArrayList<>();

    @PostConstruct
    public void init() {
        List<String> list = new ArrayList<>();
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("userAgents.txt");
        try {
            if (Objects.isNull(resourceAsStream)) {
                log.error("init user agent is erro and please you exit userAgents.text file");
                return;
            }
            list = IOUtils.readLines(resourceAsStream, "UTF-8");
            if (!list.isEmpty()) {
                //去重处理
                LinkedHashSet<String> set = new LinkedHashSet<String>(list.size());
                set.addAll(list);
                list.clear();
                list.addAll(set);
                userAgentList.addAll(list);
            }
        } catch (IOException e) {
            log.error("read user agent config file meet error:", e);
        }
        log.info("init userAgent config finished, userAgent list size=" + (list == null ? 0 : list.size()));
    }

    public List<String> getUserAgentList() {
        return Collections.unmodifiableList(userAgentList);
    }
}
