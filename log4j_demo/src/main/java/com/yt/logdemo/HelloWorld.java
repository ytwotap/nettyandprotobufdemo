package com.yt.logdemo;




import java.util.logging.Logger;

public class HelloWorld {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(String.valueOf(HelloWorld.class));

        logger.info("hello world");

    }
}



