package com.nextgood.deadlock;

/**
 * 描述：模拟死锁
 * 介绍：
 * 时间：2017/9/22
 * 码者: nextGood
 */
public class DeadLockDemo {

    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) throws Exception {
        deadTest();
    }

    public static void deadTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A) {
                    try {
                        Thread.sleep(2000);
                        System.out.println("A1");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    synchronized (B) {
                        System.out.println("B1");
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B) {
                    System.out.println("B2");
                    synchronized (A) {
                        System.out.println("A2");
                    }
                }
            }
        }).start();
    }
}