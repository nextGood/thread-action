package com.nextgood.thread_safe;

import java.math.BigInteger;

/**
 * 构建高效且可伸缩的结果缓存
 *
 * @author nextGood
 * @date 2019/8/17
 */
public class ExpensiveFuntion implements Computable<String, BigInteger> {
    @Override
    public BigInteger compute(String args) throws InterruptedException {
        return new BigInteger(args);
    }
}