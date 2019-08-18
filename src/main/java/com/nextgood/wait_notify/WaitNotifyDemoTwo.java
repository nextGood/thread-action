package com.nextgood.wait_notify;

/**
 * 描述：wait(long timeout)和notify()
 * 介绍：http://www.cnblogs.com/skywang12345/p/3479224.html
 * 时间：2017/10/13 18:33
 * 码者: Administrator
 */
public class WaitNotifyDemoTwo {
    private void notifyMethod() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " run()");
            while (true) ;
        }
    }

    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                new WaitNotifyDemoTwo().notifyMethod();
            }
        });
        synchronized (thread) {
            System.out.println(Thread.currentThread().getName() + " start()");
            thread.start();
            System.out.println(Thread.currentThread().getName() + " wait()");
            thread.wait(300);
            System.out.println(Thread.currentThread().getName() + " continue");
        }
    }
}