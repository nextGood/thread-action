package com.nextgood.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CountDownLatch模拟压测
 *
 * @author nextGood
 * @date 2019/8/16
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        Runnable taskTemp = new Runnable() {

            // 注意，此处是非线程安全的，留坑
            private AtomicInteger iCounter = new AtomicInteger(0);

            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    // 发起请求
                    // HttpClientOp.doGet("https://www.baidu.com/");
                    System.out.println(System.nanoTime() + " [" + Thread.currentThread().getName() + "] iCounter = " + iCounter.incrementAndGet());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        CountDownLatchDemo latchTest = new CountDownLatchDemo();
        latchTest.startTaskAllInOnce(5, taskTemp);
    }

    public void startTaskAllInOnce(int threadNums, final Runnable task) throws InterruptedException {
        // 启动门将使得主线程能够同时释放所有工作线程
        final CountDownLatch startGate = new CountDownLatch(1);
        // 结束门使主线程能够等待最后一个线程执行完成，而不是顺序地等待每个线程执行完成
        final CountDownLatch endGate = new CountDownLatch(threadNums);
        for (int i = 0; i < threadNums; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        // 使线程在此等待，当开始门打开时，一起涌入门中
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            // 将结束门减1，减到0时，就可以开启结束门了
                            endGate.countDown();
                        }
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            };
            t.start();
        }
        long startTime = System.currentTimeMillis();
        System.out.println(startTime + " [" + Thread.currentThread() + "] All thread is ready, concurrent going...");
        // 因开启门只需一个开关，所以立马就开启开始门
        startGate.countDown();
        // 等等结束门开启
        endGate.await();
        long endTime = System.currentTimeMillis();

        System.out.println(endTime + " [" + Thread.currentThread() + "] All thread is completed.costs:" + (endTime - startTime));
    }
}