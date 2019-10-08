package com.example.blockingqueue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @title: SynchronousQueueDemo
 * @projectName interview-demo
 * @description: TODO 同步队列
 * @date 2019/10/2 0002下午 8:02
 */
public class SynchronousQueueDemo {

    public static void main(String[] args) {

        SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\t put: " + 1);
                synchronousQueue.put(1);

                System.out.println(Thread.currentThread().getName() + "\t put: " + 2);
                synchronousQueue.put(2);

                System.out.println(Thread.currentThread().getName() + "\t put: " + 3);
                synchronousQueue.put(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t take: " + synchronousQueue.take());

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t take: " + synchronousQueue.take());

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t take: " + synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}
