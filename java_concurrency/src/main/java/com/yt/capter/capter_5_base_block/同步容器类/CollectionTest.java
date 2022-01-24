package com.yt.capter.capter_5_base_block.同步容器类;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author: YT
 * @date: 2022/1/18/018
 */
public class CollectionTest {
    @Test
    public void testCollection() {
        HashMap<Integer, Point> pointHashMap = new HashMap<>();
        Point point = new Point();
        point.x = 1;
        point.y = 1;
        pointHashMap.put(1, point);
//        unmodifiableMap（）不能被修改 map 但是 可以修改 map中的值
        Map<Integer, Point> integerPointMap = Collections.unmodifiableMap(pointHashMap);
        //.UnsupportedOperationException
//        integerPointMap.put(2, point);

        Point point1 = integerPointMap.get(1);
        point1.x = 2;
        System.out.println(point1.toString());
    }




    @Test
    public void testBlockingQueue() {
        BlockingDeque<Object> objects = new LinkedBlockingDeque<>();
    }
    class Point{
        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        int x;
        int y;
    }
    //生产者 消费者
    @Test
    public void testBlockingQue(){
        class a{
            int a = 1;
        }

    }

}
