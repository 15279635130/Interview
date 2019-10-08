package com.example.volatiles;

/**
 * @author Administrator
 * @title: TestSingleton
 * @projectName interview-demo
 * @description: TODO 防止指令重排的单例模式
 * @date 2019/10/1 0001下午 3:29
 */
public class TestSingleton {

    // 加入 volatile防止先创建对象在初始化
    private static volatile TestSingleton instance = null;

    private TestSingleton(){
        System.out.println("单例模式中私有化构造方法...");
    }

    // DCL(双端检锁机制)
    public static TestSingleton getInstance() {

        if (instance == null) {
            synchronized (TestSingleton.class) {
                if (instance == null) {
                    instance = new TestSingleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                TestSingleton.getInstance();
            }, String.valueOf(i)).start();
        }
    }
}
