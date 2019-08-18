package com.nextgood.param;

import java.util.Random;

/**
 * 通过回调函数传递数据
 * 介绍：http://www.cnblogs.com/GarfieldEr007/p/5746362.html
 * Created by nextGood on 2017/9/30.
 */
class Data {
    public int value = 0;
}

class Work {
    public void process(Data data, Integer... numbers) {
        for (int n : numbers) {
            data.value += n;
        }
    }
}

public class ParamThread extends Thread {
    private Work work;

    public ParamThread(Work work) {
        this.work = work;
    }

    public void run() {
        Random random = new Random();
        Data data = new Data();
        int n1 = random.nextInt(1000);
        int n2 = random.nextInt(2000);
        int n3 = random.nextInt(3000);
        work.process(data, n1, n2, n3); // 使用回调函数
        System.out.println(String.valueOf(n1) + " + " + String.valueOf(n2) + " + " + String.valueOf(n3) + " = " + data.value);
    }

    public static void main(String[] args) {
        new ParamThread(new Work()).start();
    }
}