package com.yt.functionprogram.function_interface;

/**
 *
 * 函数式编程
 * @author: 杨涛
 * @date: 2021/11/11/011
 */
//函数编程 带的注解
@FunctionalInterface
public interface FunctionInterface {
    /**
     * 只有一个实现方法
     */
    public void isFunctions();
    //加了@FunctionalInterface 两个方法会报错
    //Multiple non-overriding abstract methodsfound in interface com.yt.functionprogram.
    // function_interface.FunctionInterfaceTest
//    public String test2();


    default void isDefaultMethod(){
        System.out.println("this is default method 1！");
    }

    default void isDefaultMethod2() {
        System.out.println("this is default method 2!");
    }
}
