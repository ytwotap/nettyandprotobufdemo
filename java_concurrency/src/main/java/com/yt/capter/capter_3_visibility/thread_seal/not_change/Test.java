package com.yt.capter.capter_3_visibility.thread_seal.not_change;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: YT
 * @date: 2021/12/23/023
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
        ThreeStooges threeStooges = new ThreeStooges();
        boolean moe = threeStooges.isStooge("Moe");
      log.info("Moe is stooge:{}",moe);
    }
}
