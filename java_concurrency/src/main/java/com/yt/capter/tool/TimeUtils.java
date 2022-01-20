package com.yt.capter.tool;

/**
 *
 * @author: YT
 * @date: 2022/1/20/020
 */
public class TimeUtils {
    public static long operatorTime(Operator operator) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        // 需计算执行时间的代码段
        operator.active();

        long endTime = System.currentTimeMillis();
        System.out.println(endTime -startTime);
        return endTime -startTime;
    }
}
