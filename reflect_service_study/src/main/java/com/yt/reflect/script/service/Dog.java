package com.yt.reflect.script.service;

import com.yt.reflect.script.annotion.script;

@script
public class Dog implements Anime{
    @Override
    public void shout() {
        System.out.println("wang wang wang! Hello world! ");
    }


}
