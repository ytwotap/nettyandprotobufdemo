package com.yt.capter.capter_4_complete.car_find2.car_find;

import com.yt.capter.tool.ThreadSafe;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 安全类委派
 *
 * @author yt
 * @time 2022年1月5日
 */
@ThreadSafe
public class DelegatingVehicleTracker {
    private final ConcurrentHashMap<String, Point> location;
    private final Map<String, Point> unmodiflableMap;


    public DelegatingVehicleTracker(Map<String, Point> points) {
        this.location = new ConcurrentHashMap<String, Point>(points);
        this.unmodiflableMap = Collections.unmodifiableMap(location);
    }

    public Map<String, Point> getLocation() {
        return unmodiflableMap;
    }

    public Point getLocation(int id) {
        return location.get(id);
    }

    public synchronized void setLocation(String id, int x, int y) {
        if (location.replace(id, new Point(x, y)) == null) {
            throw new IllegalArgumentException(
                    "invalid vehicle name : " + id
            );
        }
    }

}
