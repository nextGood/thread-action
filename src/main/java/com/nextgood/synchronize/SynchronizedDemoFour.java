package com.nextgood.synchronize;

/**
 * 描述：不同线程访问同一对象里的不同同步代码，不同同步代码会相互阻塞并同步执行
 * 介绍：http://www.cnblogs.com/skywang12345/p/3479202.html
 * 时间：2017/10/12 17:47
 * 码者: Administrator
 */
public class SynchronizedDemoFour {
    private void synMethodOne() {
        shareCode();
    }

    private void synMethodTwo() {
        shareCode();
    }

    private void shareCode() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " loop " + i);
            }
        }
    }

    public static void main(String[] args) {
        final SynchronizedDemoFour synchronizedDemoFour = new SynchronizedDemoFour();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedDemoFour.synMethodOne();
            }
        }, "thread1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedDemoFour.synMethodTwo();
            }
        }, "thread2").start();
    }
}