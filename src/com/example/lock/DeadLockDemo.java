package com.example.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @title: DeadLockDemo
 * @projectName interview-demo
 * @description: TODO 死锁案例
 * @date 2019/10/3 0003下午 8:04
 *
 * 死锁是指两个或者两个以上的进程在执行过程中，
 * 因争抢资源而造成一种相互等待的现象，
 * 若无外力干涉那他们都将无法推进下去
 *
 * 排查
 * cls ：清屏
 * jsp -l ：查看进程
 * jstack 进程号： 查看原因
 */
class HoldLockThread implements Runnable {

    String lockA;
    String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName()
                    + " \t 持有：" + lockA + " \t 尝试获取：" + lockB);

            // 暂停线程，确保线程 A启动后线程 B启动
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName()
                        + " \t 持有：" + lockB + " \t 尝试获取：" + lockA);
            }
        }
    }
}

public class DeadLockDemo {

    public static void main(String[] args) {

        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA, lockB), "threadA").start();
        new Thread(new HoldLockThread(lockB, lockA), "threadB").start();
    }
}
