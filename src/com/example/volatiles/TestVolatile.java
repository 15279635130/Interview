package com.example.volatiles;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Administrator
 * @title: TestValidate
 * @projectName interview-demo
 * @description: TODO 测试 volatile可见性
 * @date 2019/10/1 0001下午 12:01
 *
 * validate:Java虚拟机提供的轻量级的同步机制
 * 1.保证可见性
 * 2.不保证原子性
 * 3.禁止指令重排
 *
 * 1. 添加 volatile可以解决可见性问题
 * 2.  原子性：不可分割，完整性，某个线程在做某项业务时，中间不可以加塞或者分割，
 *     需要整体完整，要么同时成功，要么同时失败
 *  2.1 volatile不能保证原子性
 * 3.保证原子性
 *  3.1 synchronized
 *  3.2 AtomicInteger
 *
 */

class MyData {

    // 使用 volatile关键字进行通知其他线程
    volatile int number = 0;

    public void changeNum() {
        number = 20;
    }

    public void addPlus() {
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addAtomic() {
        atomicInteger.getAndIncrement();
    }
}

public class TestVolatile {

    public static void main(String[] args) {

        MyData myData = new MyData();

        // 20个线程，每个线程加 1000次
        for (int i = 0; i < 20; i++) {

            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    //myData.addPlus();
                    myData.addAtomic();
                }
            }, String.valueOf(i)).start();
        }

        // 等待上面20个线程全部执行完
        // 大于 2说明上面还有线程没有执行完(2: main线程， GC线程)
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        //System.out.println(Thread.currentThread().getName() + ", finally number value: " + myData.number);
        System.out.println(Thread.currentThread().getName() + ", finally number value: " + myData.atomicInteger);

    }

    // 验证 volatile可以保证可见性
    private static void seeOkByVolatile() {
        // 资源类
        MyData myData = new MyData();

        // 子线程修改数值
        new Thread(() -> {
            System.out.println("A thread come in ...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.changeNum();
            System.out.println("A thread update number, number: " + myData.number);
        }, "A").start();

        // 测试是否获取到数据的修改
        while (myData.number == 0) {
            // 勇于子线程修改数据，需要通过 volatile进行通知其他线程
        }

        System.out.println("main tread is over ...");
    }
}
