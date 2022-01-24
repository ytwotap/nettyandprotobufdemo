package com.yt.capter.capter_5_base_block.同步工具类.栅栏;

import javafx.concurrent.Worker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.var;

import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: YT
 * @date: 2022/1/20/020
 */
@Data
@AllArgsConstructor
public class CellularAutomata {
    private final Board mainBoard;
    private final CyclicBarrier barrier;
    private final Worker[] workers;

    public CellularAutomata(Board board) {
        this.mainBoard = board;
        int count = Runtime.getRuntime().availableProcessors();

        //使用栅栏  在 所有线程到达栅栏后 触发 Runnable 操作
        this.barrier = new CyclicBarrier(count, () -> {
            mainBoard.commitNewValues();
        });
        this.workers = new Worker[count];
        for (int i = 0; i < count; i++) {
            workers[i] = new Worker((Board) mainBoard.getSubBoard(count, i));
        }
    }
    @Data
    @AllArgsConstructor
    private class Worker implements Runnable {
        private final Board board;

        @SneakyThrows
        @Override
        public void run() {
            // to use barrier
            //。。。

            //拦截线程等待 说有的线程到达这里
            barrier.await();
        }
    }

}

class Board{
    public void commitNewValues() {


    }

    public Object getSubBoard(int count, int i) {
        return null;
    }
}
