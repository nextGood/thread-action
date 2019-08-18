package com.nextgood.wait_notify;

/**
 * 描述：wait()和notify()示例
 * 介绍：http://www.cnblogs.com/skywang12345/p/3479224.html
 * 时间：2017/10/13 18:11
 * 码者: Administrator
 */
public class WaitNotifyDemoOne {
    private void notifyMethod() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " call notify()");
            // 唤醒当前的wait线程
            notify();
        }
    }

    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                new WaitNotifyDemoOne().notifyMethod();
            }
        }, "thread");
        synchronized (thread) {
            // 启动“thread”
            System.out.println(Thread.currentThread().getName() + " start thread");
            thread.start();
            // 主线程等待thread通过notify()唤醒。
            System.out.println(Thread.currentThread().getName() + " call wait()");
            thread.wait();
            System.out.println(Thread.currentThread().getName() + " continue");
        }
    }
}