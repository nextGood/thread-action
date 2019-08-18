package com.nextgood.juc;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 描述：CopyOnWriteArrayList示例
 * 介绍：http://www.cnblogs.com/skywang12345/p/3498483.html
 * 时间：2017/11/6
 * 码者: nextGood
 */
public class CopyOnWriteArrayListDemo {

    private List<String> list = new CopyOnWriteArrayList<String>();

    private void printAll() {
        Iterator iterator = list.iterator();
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
        new CopyOnWriteArrayListDemo().test();
    }

    class Demo implements Runnable {
        @Override
        public void run() {
            int i = 0;
            while (++i < 6) {
                String value = Thread.currentThread().getName() + "-" + i;
                list.add(value);
                printAll();
            }
        }
    }
}