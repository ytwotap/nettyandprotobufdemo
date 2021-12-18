package com.yt.capter.capter_3_visibility.thread_seal;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;

/**
 * @author: YT
 * @date: 2021/12/18/018
 */
public class SpecisGenderComparator implements java.util.Comparator<? super Animal>, java.util.Collection<? extends Animal>, java.util.SortedSet<Animal> {
    @Override
    public Comparator<? super Animal> comparator() {
        return null;
    }

    @Override
    public SortedSet<Animal> subSet(Animal fromElement, Animal toElement) {
        return null;
    }

    @Override
    public SortedSet<Animal> headSet(Animal toElement) {
        return null;
    }

    @Override
    public SortedSet<Animal> tailSet(Animal fromElement) {
        return null;
    }

    @Override
    public Animal first() {
        return null;
    }

    @Override
    public Animal last() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<? extends Animal> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean addAll(Collection<? extends Animal> c) {
        return false;
    }

    @Override
    public boolean add(Animal animal) {
        return false;
    }

    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }
}
