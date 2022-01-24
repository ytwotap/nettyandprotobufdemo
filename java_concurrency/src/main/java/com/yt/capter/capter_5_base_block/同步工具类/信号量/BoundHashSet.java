package com.yt.capter.capter_5_base_block.同步工具类.信号量;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 信号量 根据信号量设置相对的值
 * @author: YT
 * @date: 2022/1/20/020
 */
@Data
@AllArgsConstructor
public class BoundHashSet<T> {
    private final Set<T> set;
    private final Semaphore semaphore;

    public BoundHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<>());
        this.semaphore = new Semaphore(bound);
    }
    public boolean add(T o) throws InterruptedException {
        semaphore.acquire();
        boolean wasAdd = false;
        try {
            wasAdd = set.add(o);
            return wasAdd;
        }finally {
            if (!wasAdd) {
                semaphore.release();
            }
        }
    }

    public boolean remove(Object o) {
        boolean wasRemoved = set.remove(o);
        if (wasRemoved) {
            semaphore.release();
        }
        return wasRemoved;
    }

}
