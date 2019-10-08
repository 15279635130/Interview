package com.example.jucapi;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Administrator
 * @title: CyclicBarrierDemo
 * @projectName interview-demo
 * @description: TODO
 * @date 2019/10/2 0002下午 6:19
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙....");
        });

        for (int i = 1; i <= 7; i++) {
            int temp = i;
            new Thread(() -> {
                System.out.println("收集到第 " + temp + "可龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
