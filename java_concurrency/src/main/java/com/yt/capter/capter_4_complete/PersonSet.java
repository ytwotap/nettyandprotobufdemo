package com.yt.capter.capter_4_complete;

import com.yt.capter.tool.GuardedBy;
import com.yt.capter.tool.ThreadSafe;

import java.util.HashSet;
import java.util.Set;

/**
 * @time 2022年1月5日
 */
@ThreadSafe
public class PersonSet {
    @GuardedBy("this")
    private final Set<Person> mySet = new HashSet<Person>();

    public synchronized void addPerson(Person person) {
        mySet.add(person);
    }

    public synchronized boolean countainsPerson(Person person) {
        return mySet.contains(person);
    }
}

