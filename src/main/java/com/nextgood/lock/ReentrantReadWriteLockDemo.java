package com.nextgood.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 描述：ReentrantReadWriteLock示例
 * 介绍：http://www.cnblogs.com/skywang12345/p/3505809.html
 * 时间：2017/10/26
 * 码者: nextGood
 */
public class ReentrantReadWriteLockDemo {
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        User user = new User("account",10l);
        for(long i = 1;i < 5;i++){
            getCash(user);
            setCash(user, i);
        }
    }

    private static void getCash(final User user) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reentrantReadWriteLock.readLock().lock();
                    System.out.println(Thread.currentThread().getName() + " getCash() start");
                    user.getMoney();
                    System.out.println(Thread.currentThread().getName() + " getCash() end");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    reentrantReadWriteLock.readLock().unlock();
                }
            }
        }).start();
    }

    private static void setCash(final User user, final Long monney) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reentrantReadWriteLock.writeLock().lock();
                    System.out.println(Thread.currentThread().getName() +" setCash start");
                    user.setMoney(monney);
                    System.out.println(Thread.currentThread().getName() + " setCash end");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    reentrantReadWriteLock.writeLock().unlock();
                }
            }
        }).start();
    }

    static class User {
        String account;
        Long money;

        User(String account, Long money) {
            this.account = account;
            this.money = money;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public Long getMoney() {
            System.out.println(Thread.currentThread().getName() + " getMoney()");
            return money;
        }

        public void setMoney(Long money) {
            System.out.println(Thread.currentThread().getName() + " setMoney()");
            this.money = money;
        }
    }
}