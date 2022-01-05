package com.yt.capter.capter_4_complete.car_find;

/**
 * @author: YT
 * @date: 2022/1/5/005
 */
public class MutablePoint {
    public int x, y;

    public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint mutablePoint) {
        this.x = mutablePoint.x;
        this.y = mutablePoint.y;
    }
}
