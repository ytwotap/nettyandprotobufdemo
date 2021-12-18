package com.yt.capter.capter_3_visibility.pulish_overflow;

import java.util.HashSet;
import java.util.Set;

/**
 * publish demo test
 * @author: YT
 * @date: 2021/12/17/017
 */
public class PublishDemo {
    public static Set<Secret> knowSecrets;

    public void initialize() {
        knowSecrets = new HashSet<Secret>();
    }
}
