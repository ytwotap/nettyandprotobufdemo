package com.yt.capter.capter_5_base_block.生产者和消费者;

import lombok.Data;

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
public class FileCrawler implements Runnable {
    private final BlockingQueue<File> fileQueue;
    private final FileFilter fileFilter;
    private final File root;
    @Override
    public void run() {
        craw(root);
    }

    private void craw(File root) {
        File[] files = root.listFiles();
        if (Objects.nonNull(files)) {
            for (File file : files) {
                if (file.isDirectory()) {
                    craw(file);
                }else {
                    try {
                        System.out.println(Thread.currentThread().getName()+"----生产----"+file.getName());
                        fileQueue.put(file);
                    } catch (InterruptedException e) {
                        //回复中断来防止中断被屏蔽
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

}
