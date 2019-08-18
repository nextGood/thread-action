package com.nextgood.synchronize;

/**
 * 描述：两个线程各自访问各自对象的“synchronized方法”或者“synchronized代码块”时，两个线程间互不影响。
 * 介绍：http://www.cnblogs.com/skywang12345/p/3479202.html
 * 时间：2017/10/12 17:24
 * 码者: Administrator
 */
public class SynchronizedDemoTwo extends Thread {

    public SynchronizedDemoTwo(String name) {
        super(name);
    }

    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " loop " + i);
            }
        }
    }

    public static void main(String[] args) {
        new SynchronizedDemoTwo("thread1").start();
        new SynchronizedDemoTwo("thread2").start();
    }
}