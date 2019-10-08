package com.example.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Administrator
 * @title: ProductConsumer03
 * @projectName interview-demo
 * @description: TODO 生产者消费者问题(BlockingQueue)
 * @date 2019/10/3 0003上午 11:53
 */
class MyResource {

    private volatile boolean FLAG = true;   // 是否开启生产和消费
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void myProduct() throws Exception {

        String data = null;
        boolean result;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";
            result = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (result) {
                System.out.println(Thread.currentThread().getName() + "\t 生产队列 \t" + data + "\t 成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t 生产队列 \t" + data + "\t 失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println();
        System.out.println();
        System.out.println(Thread.currentThread().getName() + "\t 停止生产活动....");
    }

    public void myConsumer() throws Exception {

        String result = null;
        while (FLAG) {
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (null == result || result.equalsIgnoreCase("")) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t 停止消费活动....");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t 消费队列 \t" + result + "\t 成功");
            System.out.println();
            System.out.println();
        }
    }

    public void stop() {
        this.FLAG = false;
    }
}

public class ProductConsumer03 {

    public static void main(String[] args) {

        MyResource myResource = new MyResource(new ArrayBlockingQueue<String>(10));

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 生产线程启动...");
            try {
                myResource.myProduct();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Pro").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 消费线程启动...");
            System.out.println();
            System.out.println();
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myResource.stop();
    }
}
