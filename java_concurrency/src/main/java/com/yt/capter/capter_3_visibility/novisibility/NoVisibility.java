package com.yt.capter.capter_3_visibility.novisibility;

/**
 * visibility question study
 * @author: YT
 * @date: 2021/12/17/017
 */
public class NoVisibility {
    private static boolean ready;
    private static int number;
    private static class ReaderThread extends Thread{
        @Override
        public void run() {
            while (!ready) {
                //this method is not use cpu to yield other thread to use;
                Thread.yield();
                System.out.println(number);
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        number=5;
        new ReaderThread().start();
        number = 42;
        ready = true;
    }
}
