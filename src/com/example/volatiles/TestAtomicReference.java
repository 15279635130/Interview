package com.example.volatiles;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Administrator
 * @title: TestAtomicReference
 * @projectName interview-demo
 * @description: TODO 原子引用
 * @date 2019/10/1 0001下午 6:30
 */

class User {
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class TestAtomicReference {

    public static void main(String[] args) {

        User zs = new User("zs", 20);
        User ls = new User("ls", 21);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(zs);

        System.out.println(atomicReference.compareAndSet(zs, ls) + ", data: " + atomicReference.get());
        System.out.println(atomicReference.compareAndSet(zs, ls) + ", data: " + atomicReference.get());
    }
}
