package com.nextgood.yield;

/**
 * 描述：yield()示例
 * 介绍：http://www.cnblogs.com/skywang12345/p/3479243.html
 * 时间：2017/9/27 16:24
 * 码者: Administrator
 */
public class YieldTest extends Thread {

    String name;

    public YieldTest(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println("线程：" + this.getName() + "，[" + this.getPriority() + "]，输出：" + i);
            if (i == 30) {
                this.yield();
            }
        }
    }

    public static void main(String[] args) {

        YieldTest yieldTestA = new YieldTest("A");
        YieldTest yieldTestB = new YieldTest("B");

        //yieldTestB.setPriority(Thread.MAX_PRIORITY);

        yieldTestA.start();
        yieldTestB.start();
    }
}