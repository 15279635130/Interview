package com.example.volatiles;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Administrator
 * @title: TestSAS
 * @projectName interview-demo
 * @description: TODO CAS
 * @date 2019/10/1 0001下午 3:39
 *
 * CAS: 比较并交换
 */
public class TestSAS {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(5);
        // 如果期望值与原值相同，进行值的交换，否则不交换
        System.out.println(atomicInteger.compareAndSet(5, 100) + ", current value: " + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 200) + ", current value: " + atomicInteger.get());
    }
}
