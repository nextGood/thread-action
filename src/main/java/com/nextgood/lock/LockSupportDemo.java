package com.nextgood.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * 描述：LockSupport是通过调用Unsafe函数中的接口实现阻塞和解除阻塞的
 * 介绍：http://www.cnblogs.com/skywang12345/p/3505784.html
 * 时间：2017/10/26
 * 码者: nextGood
 */
public class LockSupportDemo {
    public static Thread mainThread;

    public static void unparkTest() {
        System.out.println(Thread.currentThread().getName() + " call unpark()");
        LockSupport.unpark(mainThread);
    }

    public static void main(String[] args) {
        mainThread = new Thread();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                unparkTest();
            }
        }, "thread");
        System.out.println(Thread.currentThread().getName() + " call start()");
        thread.start();
        System.out.println(Thread.currentThread().getName() + " call park()");
        LockSupport.park(mainThread);
        System.out.println(Thread.currentThread().getName() + " continue");
    }
}