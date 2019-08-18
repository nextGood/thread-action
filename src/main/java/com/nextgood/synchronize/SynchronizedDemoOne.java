package com.nextgood.synchronize;

/**
 * 描述：当一个线程访问“某对象”的“synchronized方法”或者“synchronized代码块”时，
 * 其他线程对“该对象”的该“synchronized方法”或者“synchronized代码块”的访问将被阻塞。
 * 介绍：http://www.cnblogs.com/skywang12345/p/3479202.html
 * 时间：2017/10/12 17:18
 * 码者: Administrator
 */
public class SynchronizedDemoOne implements Runnable {
    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " loop " + i);
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedDemoOne synchronizedDemoOne = new SynchronizedDemoOne();
        new Thread(synchronizedDemoOne, "thread1").start();
        new Thread(synchronizedDemoOne, "thread2").start();
    }
}