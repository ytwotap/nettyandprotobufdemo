package com.yt.functionprogram.lambda;

/**
 * TODO
 *
 * @author: 杨涛
 * @date: 2021/11/11/011
 */
public class LambdaTest {
    public static void main(String[] args) {
        //正常写法
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                System.out.println("i am runnable");
            }
        };

        //lambda写法
        Runnable runnableLambda = ()->System.out.println("i am lambda");
    }
}
