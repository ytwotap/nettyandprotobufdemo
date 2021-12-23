//package com.yt.capter.capter_3_visibility.thread_seal;
//
//import java.util.Collection;
//import java.util.SortedSet;
//import java.util.TreeSet;
//
///**
// * 没明白这个能够栈封闭？？
// *
// * @author: YT
// * @date: 2021/12/18/018
// */
//public class StackSeal {
//    public int loadTheArk(Collection<Animal> candidates) {
//        SortedSet<Animal> animals;
//        int numPairs = 0;
//        Animal candidate = null;
//
//        //animals 被封闭在方法中 不要让他们逸出
//        animals = new TreeSet < Animal > ((Collection<? extends Animal>) new SpecisGenderComparator());
//        animals.addAll(candidates);
//        for (Animal animal : animals) {
//            if (candidate == null || !candidate.isPotentialMate(animal))
//            {
//                candidate = animal;
//            }
//            else{
//                ark.load(new AnimalPair(candidate, animal));
//                ++numPairs;
//                candidate = null;
//            }
//        }
//        return numPairs;
//    }
//}
