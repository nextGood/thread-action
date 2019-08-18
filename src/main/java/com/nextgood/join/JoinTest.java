package com.nextgood.join;

/**
 * 描述：join()示例
 * 介绍：http://www.cnblogs.com/skywang12345/p/3479275.html
 * 时间：2017/9/27 16:24
 * 码者: Administrator
 */
public class JoinTest extends Thread {

    String name;

    public JoinTest(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("当前线程：" + Thread.currentThread().getName() + "开始。");
        for (int i = 0; i < 5; i++) {
            System.out.println("当前线程：" + Thread.currentThread().getName() + "，输出：" + i);
            try {
                Thread.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("主线程：" + Thread.currentThread().getName() + "开始。");

        JoinTest joinTest1 = new JoinTest("A");
        JoinTest joinTest2 = new JoinTest("B");

        joinTest1.start();
        joinTest2.start();

        joinTest1.join();
        joinTest2.join();

        System.out.println("主线程：" + Thread.currentThread().getName() + "结束。");

    }
}