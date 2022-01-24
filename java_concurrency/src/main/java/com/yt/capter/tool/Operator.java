package com.yt.capter.tool;

/**
 * 具体的操作
 * @author: YT
 * @date: 2022/1/20/020
 */
public interface Operator {
    /**
     * 激活的插座
     * @return
     */
    boolean active() throws InterruptedException;
}
