package com.example.jucapi;

import java.util.concurrent.CountDownLatch;

/**
 * @author Administrator
 * @title: CountDownLatchDemo
 * @projectName interview-demo
 * @description: TODO
 * @date 2019/10/2 0002下午 5:25
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "国灭亡...");
                countDownLatch.countDown();
            }, CountryEnum.forEach_CountryEnum(i).getCountryName()).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("秦国统一....");
    }
}
