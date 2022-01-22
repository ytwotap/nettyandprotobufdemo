package com.yt.capter.capter_7_thread_close_and_cacel;

/**
 * 取消 运行的线程
 * 使用中断机制 实现 一个线程终止另一个线程
 *
 * 为啥取消
 *  需要有这个需求
 *      如 ：
 *          超过时间取消
 *          关闭任务或者服务器却笑
 *          用户操作取消
 *          错误操作 或者数据取消
 *   java 没有 抢占式的机制来停止线程 只能通过协商的方式取取消任务
 *
 *协作机制:
 *  设置 Cancellation Requested 标志
 * @author: YT
 * @date: 2022/1/22/022
 */
public class Demo1 {


}

//案例 枚举素数（除了1 和 它 本身 不能 被其他的数整除）
//todo write code


