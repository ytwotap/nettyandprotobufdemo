package com.yt.logdemo;


import com.sun.corba.se.impl.orb.ORBConfiguratorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {

    public static void main(String[] args) {


        Logger logger = LoggerFactory.getLogger(HelloWorld.class);

        // 记录info级别的信息
        logger.info("This is info message.");
        // 记录error级别的信息
        logger.error("This is error message.");
        logger.error("this is error");
    }
}



