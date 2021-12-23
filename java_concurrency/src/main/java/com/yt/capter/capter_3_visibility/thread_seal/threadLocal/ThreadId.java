package com.yt.capter.capter_3_visibility.thread_seal.threadLocal;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadId {
    //Atomic integer containing the next thread ID to assigned 
    private static final AtomicInteger nextId = new AtomicInteger(0);
    //Thread local variable containing each threa's ID
    private static final ThreadLocal<Integer> threadId =
            new ThreadLocal<Integer>() {
                @Override
                protected Integer initialValue() {
                    return nextId.getAndIncrement();
                }
            };

    //Returns the current thread's unique ID, assigning it if necessary;
    public static int get() {
        return threadId.get();
    }

    //Removes the current thread's value for this thread-local variable.
    public static void remove() {
       threadId.remove();
    }

    //set value
    public static void set(Integer id) {
        threadId.set(id);
    }
}