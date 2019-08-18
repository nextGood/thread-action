package com.nextgood.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 描述：Semaphore示例
 * 介绍：http://www.cnblogs.com/skywang12345/p/3534050.html
 * 时间：2017/10/27
 * 码者: nextGood
 */
public class SemaphoreDemo {

    private static Integer SEM_MAX = 10;

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(SEM_MAX);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new SemaphoreThread(semaphore, 4));
        executorService.execute(new SemaphoreThread(semaphore, 5));
        executorService.execute(new SemaphoreThread(semaphore, 7));
        executorService.shutdown();
    }

    static class SemaphoreThread implements Runnable {
        private volatile Semaphore semaphore;//必须有volatile才能有示例的效果
        private Integer count;

        SemaphoreThread(Semaphore semaphore, Integer count) {
            this.semaphore = semaphore;
            this.count = count;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " acquire " + this.count);
                semaphore.acquire(count);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                semaphore.release(count);
                System.out.println(Thread.currentThread().getName() + " release " + this.count);
            }
        }
    }
}