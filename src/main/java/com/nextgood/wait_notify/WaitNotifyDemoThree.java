package com.nextgood.wait_notify;

/**
 * 描述：wait()和notifyAll()
 * 介绍：http://www.cnblogs.com/skywang12345/p/3479224.html
 * 时间：2017/10/13 18:34
 * 码者: Administrator
 */
public class WaitNotifyDemoThree implements Runnable {
    private static Object object = new Object();

    @Override
    public void run() {
        synchronized (object) {
            try {
                System.out.println(Thread.currentThread().getName() + " wait()");
                object.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " continue");
    }

    public static void main(String[] args) throws Exception {
        new Thread(new WaitNotifyDemoThree()).start();
        new Thread(new WaitNotifyDemoThree()).start();
        new Thread(new WaitNotifyDemoThree()).start();
        Thread.sleep(1000);
        synchronized (object) {
            System.out.println(Thread.currentThread().getName() + " notifyAll()");
            object.notifyAll();
            System.out.println(Thread.currentThread().getName() + " continue");
        }
    }
}