package com.nextgood.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：对于同一个锁，可以创建多个Condition，在不同的情况下使用不同的Condition。
 * 介绍：http://www.cnblogs.com/skywang12345/p/3496716.html
 * 时间：2017/10/26
 * 码者: nextGood
 */
public class ConditionDemoTwo {
    static BoundedBuffer boundedBuffer = new BoundedBuffer();

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new PutThread("put" + i, i).start();
            new TakeThread("take" + i).start();
        }
    }

    static class BoundedBuffer {
        Lock lock = new ReentrantLock();
        Condition fullLock = lock.newCondition();
        Condition emptyLock = lock.newCondition();
        Object[] items = new Object[5];
        Integer count = 0, prePut = 0, takeNex = 0;

        public void put(Object obj) {
            try {
                lock.lock();
                while (count >= items.length) {
                    fullLock.await();
                }
                if (prePut == items.length) prePut = 0;
                items[prePut++] = obj;
                count++;
                emptyLock.signal();
                System.out.println(Thread.currentThread().getName() + " put " + obj);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public Object take() {
            try {
                lock.lock();
                while (count == 0) {
                    emptyLock.await();
                }
                if (takeNex == items.length) takeNex = 0;
                Object obj = items[takeNex++];
                count--;
                fullLock.signal();
                System.out.println(Thread.currentThread().getName() + " take " + obj);
                return obj;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            return null;
        }
    }

    static class PutThread extends Thread {
        private Integer num;

        PutThread(String name, Integer num) {
            super(name);
            this.num = num;
        }

        @Override
        public void run() {
            boundedBuffer.put(num);
        }
    }

    static class TakeThread extends Thread {
        TakeThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            boundedBuffer.take();
        }
    }
}