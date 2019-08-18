package com.nextgood.synchronize;

/**
 * 描述：同步方法和同步代码块执行效率
 * 介绍：http://www.cnblogs.com/skywang12345/p/3479202.html
 * 时间：2017/10/12 18:19
 * 码者: Administrator
 */
public class SynchronizedDemoFive {
    private synchronized void synMethod() {
        commonCode();
    }

    private void synCode() {
        synchronized (this) {
            commonCode();
        }
    }

    private void commonCode() {
        double count = 0;
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            count = count + Math.random() * i + i;
        }
        Long end = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + "执行完成耗时：" + (end - start));
    }

    public static void main(String[] args) {
        final SynchronizedDemoFive synchronizedDemoFive = new SynchronizedDemoFive();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedDemoFive.synMethod();
            }
        }, "synMethod").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedDemoFive.synCode();
            }
        }, "synCode").start();
    }
}