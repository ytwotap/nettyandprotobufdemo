package com.alany.spider.core.cache;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

/**
 * Guava Cache 构建 缓存
 * Guava Cache是在内存中缓存数据，相比较于数据库或redis存储，访问内存中的数据会更加高效。Guava官网介绍，下面的这几种情况可以考虑使用Guava Cache：
 *
 * 愿意消耗一些内存空间来提升速度。
 *
 * 预料到某些键会被多次查询。
 *
 * 缓存中存放的数据总量不会超出内存容量。
 *
 * 所以，可以将程序频繁用到的少量数据存储到Guava Cache中，以改善程序性能。下面对Guava Cache的用法进行详细的介绍。
 *
 * Created by alany on 2019/07/24.
 */
@Service
public class CacheService<K, V> {
    //日志构建 可以使用 slf4j 代替 使用lombok 表达式
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheService.class);

    private int cacheMaxSize = 5000;

    public void setCacheMaxSize(int cacheMaxSize) {
        this.cacheMaxSize = cacheMaxSize;
    }

    // 缓存
    //Guava Cache 构建 缓存
    private Cache<K, V> cache = CacheBuilder.newBuilder()
            .maximumSize(cacheMaxSize)
            .removalListener((notification -> {
                if (notification.wasEvicted()) {
                    LOGGER.debug("key[" + notification.getKey() + "] was removed with expired.");
                } else {
                    LOGGER.debug("key[" + notification.getKey() + "] was updated with put operation.");
                }
            }))
            .build();
//    private Cache<K, V> cache = CacheBuilder.newBuilder()
//            .maximumSize(cacheMaxSize)
//            .removalListener(new RemovalListener<K, V>() { //移出缓存时执行的操作
//                @Override
//                public void onRemoval(RemovalNotification<K, V> notification) {
//                    if (notification.wasEvicted()) {
//                        LOGGER.debug("key[" + notification.getKey() + "] was removed with expired.");
//                    } else {
//                        LOGGER.debug("key[" + notification.getKey() + "] was updated with put operation.");
//                    }
//                }
//            })
//            .build();


    public boolean containsKey(K key) {
        try {

            return cache.getIfPresent(key) != null;
        } catch (Exception e) {
            return false;
        }
    }

    public long size() {
        return cache.size();
    }

    public V get(K key) {
        return cache.getIfPresent(key);
    }

    public ConcurrentMap<K, V> getAll() {
        return cache.asMap();
    }

    public List<V> getValues(){
        return new ArrayList(getAll().values());
    }

    public void put(K key, V value) {
        cache.put(key, value);
        LOGGER.debug(String.format("put key %s with value %s to cache...", key, JSON.toJSONString(value)));
    }

    public void remove(K key) {
        if (containsKey(key)) {
            cache.invalidate(key);
        }else {
            LOGGER.info("remove key not find :{}",key);
        }
    }
}
