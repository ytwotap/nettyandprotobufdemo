package com.yt.functionprogram.function_interface;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO
 *streAM Class test
 * @author: 杨涛
 * @date: 2021/11/11/011
 */
public class StreamTest {
    public static void main(String[] args) {
        Stream<String> stringStream = Stream.of("a", "a", "a");
            test1();
    }


    public static void test1() {
        List<String> strings = Arrays.asList("ac", "b", "c", "cd");
        //创建流
        Stream<String> stream = strings.stream();
        stream.filter(s -> s.startsWith("c")) //过滤
                .map(String::toUpperCase) //转换成大写
                .sorted() //排序
                .forEach(System.out::println); //for循环打印 -->终端操作
    }
//
//    public static void testWork1() {
//        List<String> list1 = Lists.newArrayList("a", "b", "ab");
//        List<String> list2 = Lists.newArrayList("a", "c", "ab");
//        List<String> collect = list1.stream()
//                .filter(x -> list2.stream().anyMatch(e -> e.equals(x)))
//                .collect(Collectors.toList());
//        //结果：[a, ab]
//        System.out.println(collect);
//
//    }




}
