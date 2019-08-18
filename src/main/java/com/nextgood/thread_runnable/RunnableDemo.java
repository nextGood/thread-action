package com.nextgood.thread_runnable;

/**
 * 描述：Runnable的多线程示例
 * 介绍：http://www.cnblogs.com/skywang12345/p/3479063.html
 * 时间：2017/10/11 17:19
 * 码者: Administrator
 */
public class RunnableDemo implements Runnable {
    int ticket = 10;

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            //添加synchronized。保证多个线程对是同一个ByRunnable对象的run()是互斥操作的
            //synchronized (this) {
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + "卖票：" + (ticket--));
                }
            //}
        }
    }

    public static void main(String[] args) {
        RunnableDemo thread = new RunnableDemo();
        //主线程main创建并启动3个子线程，而且这3个子线程都是基于“thread这个Runnable对象”而创建的。
        Thread thread1 = new Thread(thread);
        Thread thread2 = new Thread(thread);
        Thread thread3 = new Thread(thread);
        //运行结果是这3个子线程一共卖出了10张票。说明它们是共享了Runnable接口的资源
        thread1.start();
        thread2.start();
        thread3.start();
    }
}