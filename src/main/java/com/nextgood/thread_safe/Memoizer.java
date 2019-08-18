package com.nextgood.thread_safe;

import java.util.concurrent.*;

/**
 * 构建高效且可伸缩的结果缓存
 *
 * @author nextGood
 * @date 2019/8/17
 */
public class Memoizer<A, V> implements Computable<A, V> {

    private final ConcurrentMap<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
    private final Computable<A, V> c;

    public Memoizer(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A args) throws InterruptedException {
        Future<V> data = cache.get(args);
        if (null == data) {
            FutureTask<V> futureTask = new FutureTask<>(new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return c.compute(args);
                }
            });
            data = futureTask;
            futureTask.run();
            cache.putIfAbsent(args, futureTask);
        }
        try {
            return data.get();
        } catch (CancellationException e) {
            // 防止缓存污染
            cache.remove(args, data);
        } catch (Exception e) {
            throw new InterruptedException(e.getMessage());
        }
    }
}