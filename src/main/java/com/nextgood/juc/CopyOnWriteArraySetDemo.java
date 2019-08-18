package com.nextgood.juc;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 描述：
 * 介绍：
 * 时间：2017/11/6
 * 码者: nextGood
 */
public class CopyOnWriteArraySetDemo {

    private CopyOnWriteArraySet copyOnWriteArraySet = new CopyOnWriteArraySet();

    private void printAll() {
        Iterator iterator = copyOnWriteArraySet.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + ",");
        }
        System.out.println();
    }

    private void test() {
        new Thread(new Demo(), "ta").start();
        new Thread(new Demo(), "da").start();
    }

    public static void main(String[] args) {
        new CopyOnWriteArraySetDemo().test();
    }

    class Demo implements Runnable {
        @Override
        public void run() {
            int i = 0;
            while (++i < 6) {
                String value = Thread.currentThread().getName() + "-" + i;
                copyOnWriteArraySet.add(value);
                printAll();
            }
        }
    }
}