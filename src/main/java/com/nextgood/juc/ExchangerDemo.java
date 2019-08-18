package com.nextgood.juc;

import java.util.concurrent.Exchanger;

/**
 * Exchanger示例
 * 参考https://blog.csdn.net/zaoanmiao/article/details/84786186
 *
 * @author nextGood
 * @date 2019/8/17
 */
public class ExchangerDemo {
    public static void main(String[] args) {
        //new出交换机
        Exchanger<String> exchanger = new Exchanger<>();
        //从构造方法传入对象中
        new Thread(new Spy01(exchanger)).start();
        new Thread(new Spy02(exchanger)).start();
    }

    private static class Spy01 implements Runnable {

        Exchanger<String> exchanger;

        public Spy01(Exchanger<String> exchanger) {
            //从构造方法传入交换机
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            //暗号，也就是要传给另一方的数据
            String str01 = "天苍苍";
            try {
                String spy02 = exchanger.exchange(str01);
                //该方法将自己的数据传给对方，同时接收的是对方传来的数据
                System.out.println("spy02说：" + spy02);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Spy02 implements Runnable {

        Exchanger<String> exchanger;

        public Spy02(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            String str02 = "野茫茫";
            try {
                String spy01 = exchanger.exchange(str02);
                System.out.println("spy01说：" + spy01);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}