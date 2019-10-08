package com.example.volatiles;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author Administrator
 * @title: TestABA
 * @projectName interview-demo
 * @description: TODO ABA问题解决
 * @date 2019/10/1 0001下午 7:03
 */
public class TestABA {

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {

        System.out.println("==========ABA问题的产生==========");
        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }, "T1").start();

        new Thread(() -> {
            // 线程暂停一秒中，确保 T1线程完成一次 ABA操作
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean cas = atomicReference.compareAndSet(100, 2019);
            System.out.println(Thread.currentThread().getName() + "修改: " + cas + ", data: " + atomicReference.get());
        }, "T2").start();

        // 暂停线程
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("==========ABA问题的解决==========");

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println("T3第一次版本号: " + stamp);
            // 保证 T4线程拿到第一次版本号
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100, 101, stamp, stamp + 1);
            System.out.println("T3第二次版本号: " + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101, 100,atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println("T3第三次版本号: " + atomicStampedReference.getStamp());

        }, "T3").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println("T4第一次版本号: " + stamp);
            // 保证 T3线程执行一次 ABA操作
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean cas = atomicStampedReference.compareAndSet(100, 101, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + "修改: " + cas + ", data: " + atomicStampedReference.getReference());

        }, "T4").start();
    }
}
