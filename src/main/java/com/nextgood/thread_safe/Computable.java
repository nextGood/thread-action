package com.nextgood.thread_safe;

/**
 * 构建高效且可伸缩的结果缓存
 *
 * @author nextGood
 * @date 2019/8/17
 */
public interface Computable<A, V> {
    V compute(A args) throws InterruptedException;
}