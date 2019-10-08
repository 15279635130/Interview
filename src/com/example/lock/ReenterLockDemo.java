package com.example.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Administrator
 * @title: ReenterLock
 * @projectName interview-demo
 * @description: TODO 可重入锁(递归锁)
 * @date 2019/10/2 0002上午 11:39
 *
 * 同一线程外层函数获得锁之后，内层递归函数仍然能获得该锁的代码
 * 在同一个线程在外层获取锁的时候，在进入内层方法会自动获得锁
 *
 * 也即是说，线程可以进入任何一个已拥有锁所在的同步着的代码块
 */

class Phone implements Runnable{

    // synchronized
    public synchronized void sendSMS() {
        System.out.println(Thread.currentThread().getName() + ": invoked sendSMS ...");
        sendEmail();
    }

    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName() + ": invoked sendEmail ...");
    }

    // ReentrantLock(必须两两配对，否则将一直锁)
    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        getMethod();
    }

    public void getMethod() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + ": invoked getMethod ...");
            setMethod();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void setMethod() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + ": invoked setMethod ...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class ReenterLockDemo {

    public static void main(String[] args) {

        Phone phone = new Phone();

        System.out.println("==========Synchronized==========");
        new Thread(() -> {
            phone.sendSMS();
        }, "t1").start();

        new Thread(() -> {
            phone.sendSMS();
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n==========ReentrantLock==========");
        new Thread(phone, "t3").start();
        new Thread(phone, "t4").start();
    }
}
