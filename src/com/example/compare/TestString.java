package com.example.compare;

/**
 * @author Administrator
 * @title: TestString
 * @projectName interview-demo
 * @description: TODO
 * @date 2019/10/5 0005下午 4:15
 */
public class TestString {

    public static void main(String[] args) {

        String s1 = new String("abc");
        String s2 = "abc";
        String s3 = new String("abc");

        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s2 == s3);
    }
}
