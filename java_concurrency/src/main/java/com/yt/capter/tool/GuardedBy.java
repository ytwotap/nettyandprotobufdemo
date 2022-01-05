package com.yt.capter.tool;

/**
 * @author: YT
 * @date: 2022/1/5/005
 */
public @interface GuardedBy {
    String value() default "";
}
