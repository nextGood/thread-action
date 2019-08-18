package com.nextgood.wait_notify;

/**
 * 描述：两个线程交替执行，一个输出偶数，一个输出奇数
 * 介绍：http://blog.csdn.net/woainiwss/article/details/52013810
 * 时间：2017/10/13 16:24
 * 码者: Administrator
 */
public class NumWithQiAndOu {
    static int num = 1;
    static boolean flag = false; //两个线程，交替执行的一个标志

    /**
     * 输出偶数
     */
    private void printOuNum() {
        while (num <= 5) {
            synchronized (this)/* 必须要用一把锁对象，这个对象是num*/ {
                if (!flag) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " wait");
                        wait();  //操作wait()函数的必须和锁是同一个
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + "，偶数：" + num);
                    num++;
                    flag = false;
                    System.out.println(Thread.currentThread().getName() + " notify()");
                    notify();
                }
            }
        }
    }

    /**
     * 输出奇数
     */
    private void printQiNum() {
        while (num <= 5) {
            synchronized (this) {//获取对象的锁
                if (flag) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " wait");
                        wait();//当前线程进入等待状态，并释放该对象的锁
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + "，奇数：" + num);
                    num++;
                    flag = true;
                    System.out.println(Thread.currentThread().getName() + " notify()");
                    notify();//唤醒在对象上等待的单一线程，但此时仍持有对象的锁
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        final NumWithQiAndOu numWithQiAndOu = new NumWithQiAndOu(); //声明一个资源

        new Thread(new Runnable() {
            @Override
            public void run() {
                numWithQiAndOu.printOuNum();
            }
        }, "thread-ou").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                numWithQiAndOu.printQiNum();
            }
        }, "thread-qi").start();
    }
}