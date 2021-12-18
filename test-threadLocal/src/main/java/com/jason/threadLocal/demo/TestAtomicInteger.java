package com.jason.threadLocal.demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试AtomicInteger类
 *
 * @author WangChenHol
 * @date 2021-12-18 15:30
 **/
public class TestAtomicInteger {
    private static final int HASH_INCREMENT = 0x61c88647;

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                AtomicInteger atomicInteger = new AtomicInteger();
                int andAdd = atomicInteger.getAndAdd(HASH_INCREMENT);
                System.out.println(Thread.currentThread().getName() + "  循环：" + finalI + "   AtomicInteger值：" + andAdd);
            });
            thread.setName("线程" + finalI);
            thread.start();
        }
    }
}
