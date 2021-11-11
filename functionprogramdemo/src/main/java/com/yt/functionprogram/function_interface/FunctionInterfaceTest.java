package com.yt.functionprogram.function_interface;

import java.util.function.Function;

/**
 *
 * 函数式编程 test
 * @author: 杨涛
 * @date: 2021/11/11/011
 */

public class FunctionInterfaceTest {
    public static void main(String[] args) throws Exception {
        testFunctionProgram();

        useMethodRefrence();
    }

    /**
     * 使用方法引用
     */
    private static void useMethodRefrence() {
        //方法引用的出现，使得我们可以将一个方法赋给一个变量或者作为参数传递给另外一个方法。
        // ::双冒号作为方法引用的符号，
        Function<String,Integer> s=Integer::parseInt;
        Integer i = s.apply("10");
        System.out.println(i);
    }

    public static void testFunctionProgram() throws Exception {
        FunctionInterface functionProgram=()-> System.out.println("this is program to function method");
        functionProgram.isFunctions();
    }
}
