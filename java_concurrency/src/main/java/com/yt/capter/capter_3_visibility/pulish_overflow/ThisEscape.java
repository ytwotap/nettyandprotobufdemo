package com.yt.capter.capter_3_visibility.pulish_overflow;

import java.awt.*;
import java.util.EventListener;

/**
 * @author: YT
 * @date: 2021/12/17/017
 */
public class ThisEscape {
    public ThisEscape(EventSource source) {
        source.registerListener(
                new EventListener(){
                    public void onEvent(Event e) {
                        doSomething(e);
                    }
                }
        );
    }

    void doSomething(Event event) {
        System.out.println("event test");
    }

}
