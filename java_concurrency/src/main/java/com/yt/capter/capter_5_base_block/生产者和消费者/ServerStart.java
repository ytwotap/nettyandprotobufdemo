package com.yt.capter.capter_5_base_block.生产者和消费者;

import java.io.File;

/**
 * @author: YT
 * @date: 2022/1/19/019
 */
public class ServerStart {
    public static void main(String[] args) {
        FileManager.startIndexing(new File("C:\\"));
    }
}
