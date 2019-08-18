package com.nextgood.atomic;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/**
 * 描述：AtomicLongFieldUpdater可以对指定"类的'volatile long'类型的成员"进行原子更新
 * 介绍：http://www.cnblogs.com/skywang12345/p/3514635.html
 * 时间：2017/10/24
 * 码者: nextGood
 */
public class AtomicFieldUpdaterTest {
    private void atomicLongFieldUpdater() {
        AtomicLongFieldUpdater atomicLongFieldUpdater = AtomicLongFieldUpdater.newUpdater(Person.class, "id");
        Person person = new Person(12345);
        atomicLongFieldUpdater.compareAndSet(person, 12345, 98765);
        System.out.println(person);
    }

    public static void main(String[] args) {
        new AtomicFieldUpdaterTest().atomicLongFieldUpdater();
    }

    class Person {
        volatile long id;

        Person(long id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Person{" + "id=" + id + '}';
        }
    }
}