package com.yt.capter.capter_3_visibility.security_output;

import com.yt.capter.tool.DefeatedTest;
import com.yt.capter.tool.NotThreadSafe;
import lombok.Data;

import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

/**
 * @author yt
 * @version 1.0
 * @Classname NotSecurityOutput
 * @Description 改程序 虽然保证不变性 但是 初始化 是需要时间的 所以两个程序初始化不一致 导致程序出现安全性问题 test 失败
 * @date 2021-12-26 -11:19
 */
@NotThreadSafe
@DefeatedTest
public class NotSecurityOutput {
    public Holder getHolder() {
        return holder;
    }

    public void setHolder(Holder holder) {
        this.holder = holder;
    }

    //holder 持有人
    public Holder holder;  // 该类 保持 不变 但是 如果 不是 使用 final 修饰 将会导致多线程读取出现问题  多线程状态不报错共享将出现一些奇怪的问题
//    final Holder holder = new Holder(23);
    public void init() throws InterruptedException {
        holder = new Holder(23);
        TimeUnit.SECONDS.sleep(3);
        holder.setN(1);
    }
}
