package com.nextgood.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 描述：AtomicReference是作用是对"对象"进行原子操作
 * 介绍：http://www.cnblogs.com/skywang12345/p/3514623.html
 * 时间：2017/10/24
 * 码者: nextGood
 */
public class AtomicReferenceTest {
    private void atomicReferenceTest() {
        Person person1 = new Person(123);
        Person person2 = new Person(456);
        AtomicReference atomicReference = new AtomicReference(person1);
        atomicReference.compareAndSet(person1, person2);

        Person person3 = (Person) atomicReference.get();
        System.out.println("person3:" + person3.id);
        System.out.println("person1.equals(person3):" + person1.equals(person3));
        System.out.println("person2.equals(person3):" + person2.equals(person3));
    }

    public static void main(String[] args) {
        new AtomicReferenceTest().atomicReferenceTest();
    }

    class Person {
        volatile long id;

        Person(long id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "person:" + this.id;
        }
    }
}