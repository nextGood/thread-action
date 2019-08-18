package com.nextgood.atomic;

import java.util.concurrent.atomic.AtomicLongArray;

/**
 * 描述：原子数组操作
 * 介绍：http://www.cnblogs.com/skywang12345/p/3514604.html
 * 时间：2017/10/24
 * 码者: nextGood
 */
public class AtomicArrayTest {
    private static void atomicLongArrayTest() {
        long[] arrayLong = new long[]{3, 7, 12, 23, 10, 454, 20, 5, 1};
        AtomicLongArray atomicLongArray = new AtomicLongArray(arrayLong);
        atomicLongArray.set(0, 100);
        for (int i = 0, len = atomicLongArray.length(); i < len; i++)
            System.out.printf("get(%d) : %s\n", i, atomicLongArray.get(i));

        System.out.printf("%20s : %s\n", "getAndDecrement(0)", atomicLongArray.getAndDecrement(0));
        System.out.printf("%20s : %s\n", "decrementAndGet(1)", atomicLongArray.decrementAndGet(1));
        System.out.printf("%20s : %s\n", "getAndIncrement(2)", atomicLongArray.getAndIncrement(2));
        System.out.printf("%20s : %s\n", "incrementAndGet(3)", atomicLongArray.incrementAndGet(3));

        System.out.printf("%20s : %s\n", "addAndGet(100)", atomicLongArray.addAndGet(0, 100));
        System.out.printf("%20s : %s\n", "getAndAdd(100)", atomicLongArray.getAndAdd(1, 100));

        System.out.printf("%20s : %s\n", "compareAndSet()", atomicLongArray.compareAndSet(2, 31, 1000));
        System.out.printf("%20s : %s\n", "get(2)", atomicLongArray.get(2));

    }

    public static void main(String[] args) {
        atomicLongArrayTest();
    }
}