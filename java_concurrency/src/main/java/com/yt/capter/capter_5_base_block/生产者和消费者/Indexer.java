package com.yt.capter.capter_5_base_block.生产者和消费者;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.FileFilter;
import java.util.Objects;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * create produce and consummer model
 * this is file scan file demo and use ps model to implement it
 * @author: YT
 * @date: 2022/1/19/019
 */
@Data

public class Indexer implements Runnable {
    private final BlockingQueue<File> fileQueue;

    public Indexer(BlockingQueue<File> fileQueue) {
        this.fileQueue = fileQueue;
    }


    @Override
    public void run() {
        try {
            while (true) {
                indexFile(fileQueue.take());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //消费者 消费操作
    private void indexFile(File take) {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()+"---消费---"+take.getName());
    }
}
