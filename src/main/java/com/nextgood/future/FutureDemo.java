package com.nextgood.future;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * 描述：Future、Callable和Executor
 * 介绍：Java网络编程(第4版)
 * 时间：2017/11/6
 * 码者: nextGood
 */
public class FutureDemo {
    private Integer count = 8;
    private ExecutorService executorService = Executors.newCachedThreadPool();

    private Integer task() {
        List<Future<Integer>> taskList = new ArrayList<Future<Integer>>();
        for (int i = 0; i < count; i++) {
            taskList.add(executorService.submit(new Task()));
        }
        List<Integer> resultList = new ArrayList<Integer>();

        System.out.print("获取的随机数：");
        for (int i = 0; i < taskList.size(); i++) {
            try {
                Integer value = taskList.get(i).get(3, TimeUnit.MILLISECONDS);
                System.out.print(value + ",");
                resultList.add(value);
            } catch (Exception e) {
                e.printStackTrace();
                count--;
            }
        }
        System.out.println("有效个数：" + count);
        Integer value = Collections.max(resultList);
        System.out.println("获取的最大随机数：" + value);
        return value;
    }

    public static void main(String[] args) {
        new FutureDemo().task();
    }

    class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            Thread.sleep(30);
            return (int) (100 * Math.random());
        }
    }
}