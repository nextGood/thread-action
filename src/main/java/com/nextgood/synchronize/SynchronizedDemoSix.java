package com.nextgood.synchronize;

/**
 * 描述：实例锁和全局锁
 * 介绍：http://www.cnblogs.com/skywang12345/p/3479202.html
 * 时间：2017/10/12 18:35
 * 码者: Administrator
 */
public class SynchronizedDemoSix {
    private synchronized void isSyncA() {
        shareCode();
    }

    private synchronized void isSyncB() {
        shareCode();
    }

    private static synchronized void cSyncA() {
        shareCode();
    }

    private static synchronized void cSyncB() {
        shareCode();
    }

    private static void shareCode() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(10);
                System.out.println(Thread.currentThread().getName() + " loop " + i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void testA() {
        final SynchronizedDemoSix synchronizedDemoSix = new SynchronizedDemoSix();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedDemoSix.isSyncA();
            }
        }, "threadA").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedDemoSix.isSyncB();
            }
        }, "threadB").start();
    }

    private static void testB() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new SynchronizedDemoSix().isSyncA();
            }
        }, "threadA").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                new SynchronizedDemoSix().isSyncA();
            }
        }, "threadB").start();
    }

    private static void testC() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new SynchronizedDemoSix().cSyncA();
            }
        }, "threadA").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                new SynchronizedDemoSix().cSyncB();
            }
        }, "threadB").start();
    }

    private static void testD() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new SynchronizedDemoSix().isSyncA();
            }
        }, "threadA").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                SynchronizedDemoSix.cSyncA();
            }
        }, "threadB").start();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("测试 x.isSyncA()与x.isSyncB()->不能被同时访问");
        testA();
        Thread.sleep(100);
        System.out.println("测试 x.isSyncA()与y.isSyncA()->可以同时被访问");
        testB();
        Thread.sleep(100);
        System.out.println("测试 x.cSyncA()与y.cSyncB()->不能被同时访问");
        testC();
        Thread.sleep(100);
        System.out.println("测试 x.isSyncA()与Class.cSyncA()->可以被同时访问");
        testD();
    }
}