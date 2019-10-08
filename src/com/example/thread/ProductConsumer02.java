package com.example.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Administrator
 * @title: ProductConsumer02
 * @projectName interview-demo
 * @description: TODO 生产者消费者问题(lock版)
 * @date 2019/10/3 0003上午 11:10
 */

class ShareData2 {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() {
        lock.lock();
        try {
            // 判断
            while (number != 0) {
                condition.await();
            }
            // 干活
            number++;
            // 通知
            System.out.println(Thread.currentThread().getName() + "\t 生产 \t" + number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        try {
            // 判断
            while (number == 0) {
                condition.await();
            }
            // 干活
            number--;
            // 通知
            System.out.println(Thread.currentThread().getName() + "\t 消费 \t" + number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
public class ProductConsumer02 {

    public static void main(String[] args) {

        ShareData2 shareData2 = new ShareData2();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData2.increment();
            }
        }, "t1").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData2.increment();
            }
        }, "t2").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData2.decrement();
            }
        }, "t3").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData2.decrement();
            }
        }, "t4").start();
    }
}
