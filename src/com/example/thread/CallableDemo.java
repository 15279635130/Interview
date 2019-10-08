package com.example.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Administrator
 * @title: CallableDemo
 * @projectName interview-demo
 * @description: TODO
 * @date 2019/10/3 0003下午 3:55
 *
 * Callable 与 Runnable
 * 异常       没有异常
 * call         run
 * 有返回值     无返回值
 */

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        return 1024;
    }
}
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 一个 futureTask只计算一次
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        new Thread(futureTask, "t1").start();

        Integer value = futureTask.get();

        // 未计算完成
        //while (!futureTask.isDone())

        System.out.println("callable返回值：" + value);

    }
}
