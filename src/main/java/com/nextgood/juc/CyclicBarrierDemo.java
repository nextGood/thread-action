package com.nextgood.juc;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier示例
 * 参考https://blog.csdn.net/dot_life/article/details/80982606
 *
 * @author nextGood
 * @date 2019/8/17
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        //并发线程数
        int count = 10;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count);
        ExecutorService executorService = Executors.newFixedThreadPool(count);
        int n = 0;
        for (int i = 0; i < count; i++) {
            executorService.execute(new CyclicBarrierDemo().new Task(cyclicBarrier, n));
            n++;
        }
        executorService.shutdown(); // 关闭线程池
        // 判断是否所有的线程已经运行完
        while (!executorService.isTerminated()) {
            try {
                // 所有线程池中的线程执行完毕，执行后续操作
                System.out.println("==============is sleep============");
                Thread.sleep(1000);
                System.out.println("==============is wake============");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class Task implements Runnable {
        private CyclicBarrier cyclicBarrier;
        int n = 0;

        public Task(CyclicBarrier cyclicBarrier, int n) {
            this.cyclicBarrier = cyclicBarrier;
            this.n = n;
        }

        @Override
        public void run() {
            try {
                // 等待所有任务准备就绪
                System.out.println("赛马" + n + "到达栅栏前");
                cyclicBarrier.await();
                System.out.println("赛马" + n + "开始跑");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}