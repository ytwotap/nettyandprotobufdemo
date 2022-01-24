package com.yt.capter.capter_4_complete.car_find;

import com.yt.capter.tool.GuardedBy;
import com.yt.capter.tool.ThreadSafe;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * @time 2022年1月5日
 * @author yt
 */
@ThreadSafe
public class MonitorVehicleTracker {
    @GuardedBy("this")
    private final Map<String, MutablePoint> location;

    public MonitorVehicleTracker(Map<String, MutablePoint> location) {
        this.location = location;
    }

    public synchronized Map<String, MutablePoint> getLocation() {
        return deepCopy(location);
    }

    public synchronized void setLocation(String id, int x, int y) {
        MutablePoint loc = location.get(id);
        if (loc == null) {
            throw new IllegalArgumentException("No such ID:" + id);
        }
        loc.x =x ;
        loc.y = y;
    }

    public synchronized MutablePoint getLocation(String id) {
        MutablePoint loc = location.get(id);
        return loc == null ? null : new MutablePoint(loc);
    }
    private Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> location) {
        Map<String, MutablePoint> result = new HashMap<>();
        for (String id : location.keySet()) {
            result.put(id,new MutablePoint(location.get(id)));
        }
        return Collections.unmodifiableMap(result);
    }
}
