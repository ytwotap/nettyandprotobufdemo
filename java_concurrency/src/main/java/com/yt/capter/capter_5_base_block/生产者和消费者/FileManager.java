package com.yt.capter.capter_5_base_block.生产者和消费者;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 生产者和消费者管理
 * @author: YT
 * @date: 2022/1/19/019
 */
public class FileManager {
    private static int producer = 2;
    private static int comsumer = 5;
    public static void startIndexing(File root) {
        BlockingQueue<File> queue = new LinkedBlockingQueue<>();
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return true;
            }
        };
        for (int i = 0; i < producer; i++) {
            new Thread(new FileCrawler(queue, fileFilter, root)).start();

        }
        for (int i = 0; i < comsumer; i++) {
            new Thread(new Indexer(queue)).start();

        }

    }
}
