package com.example.thread;

/**
 * @author Administrator
 * @title: ProductConsumer01
 * @projectName interview-demo
 * @description: TODO 生产者消费者问题(sync版)
 * @date 2019/10/3 0003上午 11:00
 *
 * 线程 操作 资源类
 * 判断 干活 通知
 * 防止虚假唤醒机制
 */

// 资源类
class ShareData {
    private int number = 0;

    public synchronized void increment() {
        // 判断
        while (number != 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 干活
        number++;
        // 通知
        System.out.println(Thread.currentThread().getName() + "\t 生产 \t" + number);
        this.notifyAll();
    }

    public synchronized void decrement() {
        // 判断
        while (number == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 干活
        number--;
        // 通知
        System.out.println(Thread.currentThread().getName() + "\t 消费 \t" + number);
        this.notifyAll();
    }

}
public class ProductConsumer01 {

    public static void main(String[] args) {

        //资源类
        ShareData shareData = new ShareData();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.increment();
            }
        }, "t1").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.increment();
            }
        }, "t2").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.decrement();
            }
        }, "t3").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.decrement();
            }
        }, "t4").start();
    }
}
