package com.yt.logdemo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LombokSlf4j {

    public static void main(String[] args) {

        log.info("Starting Lombok");
        log.error("this is error");
        log.debug("this is debug");
        log.warn("this is warn");

    }
}