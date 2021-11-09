package com.yt.reflect.script.test;

import com.yt.reflect.script.Engine.ScriptEngine;
import com.yt.reflect.script.service.Anime;
import com.yt.reflect.script.service.Dog;

/**
 * test @script 注解
 */
public class TestMain {
    public static void main(String[] args) {
        //获取 annime 实体类
        Anime dog = (Anime) ScriptEngine.getOne(Anime.class);

        //调用annime实体类
        dog.shout();


        //看结果 "wang wang wang! Hello world! " 就是正确的 ！

    }
}
