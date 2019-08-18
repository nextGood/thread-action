package com.nextgood.thread_runnable;

/**
 * 描述：Thread的多线程示例
 * 介绍：http://www.cnblogs.com/skywang12345/p/3479063.html
 * 时间：2017/10/11 17:18
 * 码者: Administrator
 */
public class ThreadDemo extends Thread {
    int ticket = 10;

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (ticket > 0) {
                System.out.println(this.getName() + "买票：" + (ticket--));
            }
        }
    }

    public static void main(String[] args) {
        //主线程main创建并启动3个MyThread子线程
        ThreadDemo thread1 = new ThreadDemo();
        ThreadDemo thread2 = new ThreadDemo();
        ThreadDemo thread3 = new ThreadDemo();
        //每个子线程都各自卖出了10张票
        thread1.start();
        thread2.start();
        thread3.start();
    }
}