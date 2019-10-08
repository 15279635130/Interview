package com.example.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @title: BlockingQueue
 * @projectName interview-demo
 * @description: TODO
 * @date 2019/10/2 0002下午 7:20
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(1);

        // TODO 使用 add()、remove()超出界限抛出异常
        System.out.println(blockingQueue.add("a"));
        // Exception in thread "main" java.lang.IllegalStateException: Queue full
        // System.out.println(blockingQueue.add("b"));

        System.out.println(blockingQueue.remove());
        // Exception in thread "main" java.util.NoSuchElementException
        // System.out.println(blockingQueue.remove());

        //检查是否为空，返回顶层元素
        //System.out.println(blockingQueue.element());


        // TODO 使用 offer()、poll()超出界限返回 false/null
        System.out.println(blockingQueue.offer("a"));
        // false
        //System.out.println(blockingQueue.offer("b"));

        System.out.println(blockingQueue.poll());
        // null
        System.out.println(blockingQueue.poll());

        //检查是否为空，返回顶层元素
        //System.out.println(blockingQueue.peek());


        // TODO 使用 put()、take()超出界限进入阻塞状态
        try {
            blockingQueue.put("a");
            //blockingQueue.put("b");
            blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // TODO 使用 offer()限定超时时间
        try {
            blockingQueue.offer("a", 2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
