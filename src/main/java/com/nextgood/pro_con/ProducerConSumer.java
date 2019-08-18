package com.nextgood.pro_con;

/**
 * 描述：生产者/消费者问题
 * 介绍：http://www.cnblogs.com/skywang12345/p/3480016.html
 * 时间：2017/10/16 22:35
 * 码者: Administrator
 */
public class ProducerConSumer {

    class Depot {
        private volatile Integer capacity;//仓库最大容量
        private Integer size;//仓库目前容量

        Depot(Integer capacity) {
            this.capacity = capacity;
            size = 0;
        }

        private void produce(int value) {
            synchronized (this) {
                try {
                    while (size + value > capacity) {
                        wait();
                    }
                    size = size + value;
                    notifyAll();
                    System.out.println(Thread.currentThread().getName() + " produce " + value + ",size:" + size);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void consume(int value) {
            synchronized (this) {
                try {
                    while ((size - value) < 0) {
                        wait();
                    }
                    size = size - value;
                    notifyAll();
                    System.out.println(Thread.currentThread().getName() + " consume " + value + ",size:" + size);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Productor {
        private Depot depot;

        Productor(Depot depot) {
            this.depot = depot;
        }

        private void produce(final Integer value) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    depot.produce(value);
                }
            }, "productor").start();
        }
    }

    class Consumer {
        private Depot depot;

        Consumer(Depot depot) {
            this.depot = depot;
        }

        private void consume(final Integer value) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    depot.consume(value);
                }
            }, "consumer").start();
        }
    }

    private void test() {
        Depot depot = new Depot(5);
        Productor productor = new Productor(depot);
        Consumer consumer = new Consumer(depot);
        productor.produce(3);
        consumer.consume(4);
        productor.produce(1);
    }

    public static void main(String[] args) {
        new ProducerConSumer().test();
    }
}