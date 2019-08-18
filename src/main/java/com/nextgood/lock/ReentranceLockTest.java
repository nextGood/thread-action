package com.nextgood.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：ReentrantLock是一个可重入的互斥锁，又被称为“独占锁”。
 * 问题：按照介绍里面的代码执行结果会出现消费数量超过实际库存时消费后实际库存不为0，实际代码中已去掉left变量
 * 介绍：http://www.cnblogs.com/skywang12345/p/3496101.html
 * 时间：2017/10/24
 * 码者: nextGood
 */
public class ReentranceLockTest {

    private void reentranceLockTest() {
        Depot depot = new Depot(10);
        Productor productor = new Productor(depot);
        Consumer consumer = new Consumer(depot);
        productor.product(10);
        productor.product(15);
        consumer.consum(20);
        consumer.consum(10);
        productor.product(5);
    }

    public static void main(String[] args) {
        new ReentranceLockTest().reentranceLockTest();
    }

    // 仓库类
    class Depot {
        Lock lock;
        Integer size, capacity;
        Condition fullCondition, emptyCondition;

        Depot(Integer capacity) {
            this.capacity = capacity;
            size = 0;
            lock = new ReentrantLock();
            fullCondition = lock.newCondition();
            emptyCondition = lock.newCondition();
        }

        public void product(Integer productNumber) {
            lock.lock();
            try {
                while (size >= capacity) {
                    fullCondition.await();
                }
                Integer increment = (size + productNumber) >= capacity ? (capacity - size) : productNumber;
                size = size + increment;
                System.out.println(Thread.currentThread().getName() + " add " + productNumber + " size:" + size);
                emptyCondition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void consume(Integer productNumber) {
            lock.lock();
            try {
                while (size <= 0) {
                    emptyCondition.await();
                }
                Integer decrement = (size < productNumber) ? size : productNumber;
                size = size - decrement;
                System.out.println(Thread.currentThread().getName() + " del " + productNumber + " size:" + size);
                fullCondition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    // 生产者类
    class Productor {
        private Depot depot;

        Productor(final Depot depot) {
            this.depot = depot;
        }

        public void product(final Integer productNumber) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    depot.product(productNumber);
                }
            }, "product").start();
        }
    }

    // 消费者类
    class Consumer {
        private Depot depot;

        Consumer(final Depot depot) {
            this.depot = depot;
        }

        public void consum(final Integer productNumber) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    depot.consume(productNumber);
                }
            }, "consume").start();
        }
    }
}