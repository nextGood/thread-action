package com.nextgood.atomic;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 描述：JUC原子类
 * 介绍：http://www.cnblogs.com/skywang12345/p/3514593.html
 * 时间：2017/10/24
 * 码者: nextGood
 */
public class AtomicTest {
    private static void atomicBooleanTest() {
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        atomicBoolean.set(false);
        System.out.println("get():" + atomicBoolean.get());
    }

    private static void atomicIntegerTest() {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(359934);
        System.out.println("get():" + atomicInteger.get());
    }

    private static void atomicLongTest() {
        AtomicLong mAtoLong = new AtomicLong();
        mAtoLong.set(123456);
        System.out.printf("%20s : %s\n", "get()", mAtoLong.get());
        System.out.printf("%20s : %s\n", "intValue()", mAtoLong.intValue());
        System.out.printf("%20s : %s\n", "longValue()", mAtoLong.longValue());
        System.out.printf("%20s : %s\n", "doubleValue()", mAtoLong.doubleValue());
        System.out.printf("%20s : %s\n", "floatValue()", mAtoLong.floatValue());
        System.out.printf("%20s : %s\n", "getAndDecrement()", mAtoLong.getAndDecrement());
        System.out.printf("%20s : %s\n", "decrementAndGet()", mAtoLong.decrementAndGet());
        System.out.printf("%20s : %s\n", "getAndIncrement()", mAtoLong.getAndIncrement());
        System.out.printf("%20s : %s\n", "incrementAndGet()", mAtoLong.incrementAndGet());
        System.out.printf("%20s : %s\n", "addAndGet(10)", mAtoLong.addAndGet(10));
        System.out.printf("%20s : %s\n", "getAndAdd(10)", mAtoLong.getAndAdd(10));
        System.out.printf("%20s : %s\n", "get()", mAtoLong.get());
        System.out.printf("%20s : %s\n", "compareAndSet()", mAtoLong.compareAndSet(123456, 98765));
        System.out.printf("%20s : %s\n", "get()", mAtoLong.get());
    }

    public static void main(String[] args) {
        atomicBooleanTest();
        atomicIntegerTest();
        atomicLongTest();
    }
}