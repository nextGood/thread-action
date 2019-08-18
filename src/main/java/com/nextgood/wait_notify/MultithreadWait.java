package com.nextgood.wait_notify;

/**
 * Created by nextGood on 2017/9/26.
 */
public class MultithreadWait implements Runnable {

    private String name;
    private Object prev;
    private Object self;

    private MultithreadWait(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        int count = 10;
        while (count > 0) {
            synchronized (prev) {
                synchronized (self) {
                    System.out.print(name);
                    count--;
                    self.notify();
                }
                try {
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        MultithreadWait pa = new MultithreadWait("A", c, a);
        MultithreadWait pb = new MultithreadWait("B", a, b);
        MultithreadWait pc = new MultithreadWait("C", b, c);

        new Thread(pa).start();
        Thread.sleep(100);  //确保按顺序A、B、C执行  
        new Thread(pb).start();
        Thread.sleep(100);
        new Thread(pc).start();
        Thread.sleep(100);
    }
}