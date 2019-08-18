package com.nextgood.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：通过Condition的await(),signal()来演示线程的休眠/唤醒功能
 * 介绍：http://www.cnblogs.com/skywang12345/p/3496716.html
 * 时间：2017/10/26
 * 码者: nextGood
 */
public class ConditionDemoOne {
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    private static void conditionSignal() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " call signal()");
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                conditionSignal();
            }
        }, "thread");
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " call start()");
            thread.start();
            System.out.println(Thread.currentThread().getName() + " call await()");
            condition.await();
            System.out.println(Thread.currentThread().getName() + " continue");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}