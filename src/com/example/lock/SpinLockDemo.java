package com.example.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Administrator
 * @title: SpinLockDemo
 * @projectName interview-demo
 * @description: TODO 自旋锁
 * @date 2019/10/2 0002下午 2:46
 *
 * 尝试获取锁的线程不会立即阻塞，而是通过循环的方式去尝试获取锁
 * 优点：减少线程上下文切换到的消耗
 * 缺点：循环消耗CPU
 */
public class SpinLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {

        // 获取当前线程
        Thread thread = Thread.currentThread();

        System.out.println(Thread.currentThread().getName() + "\t come in...");

        // 自旋获取锁(如果期望值不为空，说明已有线程持有锁)
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    public void myUnLock() {

        // 获取当前线程
        Thread thread = Thread.currentThread();

        System.out.println(Thread.currentThread().getName() + "\t invoked unLock...");

        // 模拟解锁
        atomicReference.compareAndSet(thread, null);
    }

    public static void main(String[] args) {

        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(() -> {
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();
        }, "t1").start();

        /*try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        new Thread(() -> {
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();
        }, "t2").start();
    }
}
