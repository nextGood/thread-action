package com.nextgood.synchronize;

/**
 * 描述：不同线程访问同一个对象的同步代码和普通代码时，同步代码和普通代码的执行互不影响
 * 介绍：http://www.cnblogs.com/skywang12345/p/3479202.html
 * 时间：2017/10/12 17:34
 * 码者: Administrator
 */
public class SynchronizedDemoThree {
    private void synMethod() {
        synchronized (this) {
            shareCode();
        }
    }

    private void nonSynMethod() {
        shareCode();
    }

    private void shareCode() {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " loop " + i);
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final SynchronizedDemoThree synchronizedDemoThree = new SynchronizedDemoThree();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedDemoThree.synMethod();
            }
        }, "thread1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedDemoThree.nonSynMethod();
            }
        }, "thread2").start();
    }
}