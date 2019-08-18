package com.nextgood.start_run;

/**
 * 描述：start()和run()的区别示例
 * 介绍：http://www.cnblogs.com/skywang12345/p/3479083.html
 * 时间：2017/10/11 18:17
 * 码者: Administrator
 */
public class StartAndRunDemo extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running");
    }

    public static void main(String[] args) throws Exception {
        StartAndRunDemo startAndRunDemo = new StartAndRunDemo();
        System.out.println(Thread.currentThread().getName() + " call start()");
        startAndRunDemo.start();
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + " call run()");
        startAndRunDemo.run();
    }
}