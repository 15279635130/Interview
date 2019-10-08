package com.example.threadpool;

import java.util.concurrent.*;

/**
 * @author Administrator
 * @title: MyThreadPoolDemo
 * @projectName interview-demo
 * @description: TODO 使用线程池获取多线程(底层：ThreadPoolExecutor)
 * @date 2019/10/3 0003下午 4:38
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {

        //ExecutorService threadPool = Executors.newFixedThreadPool(5);// 一池固定数线程池
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();// 一池一线程
        //ExecutorService threadPool = Executors.newCachedThreadPool();//一池 N线程

        // 自定义线程池
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        // 模拟 10个用户办理业务
        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " \t 办理业务");
                });
                /*try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
