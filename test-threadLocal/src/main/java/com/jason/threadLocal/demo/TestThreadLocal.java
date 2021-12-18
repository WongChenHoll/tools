package com.jason.threadLocal.demo;

/**
 * @author WangChenHol
 * @date 2021-12-17 17:13
 **/
public class TestThreadLocal {
    static ThreadLocal<Integer> t1 = new ThreadLocal<>();
    static ThreadLocal<Integer> t2 = new ThreadLocal<>();

    public static void main(String[] args) {
        t1.set(9);
        t1.set(8);
        t2.set(15);
        t2.set(10);

        System.out.println(t1.get());
        System.out.println(t2.get());
    }
}
