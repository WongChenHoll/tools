package com.jason.threadLocal.demo;

/**
 * 在多线程中使用ThreadLocal
 *
 * @author WangChenHol
 * @date 2021-12-18 14:59
 **/
public class TestThreadLocalAndThread {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            int tempV = i;
            Runnable runnable = () -> {
                ThreadLocal<Integer> local = new ThreadLocal<>();
                local.set(tempV);
                System.out.println(Thread.currentThread().getName() + "  值：" + local.get());
            };
            Thread thread = new Thread(runnable);
            thread.setName("线程" + tempV);
            thread.start();
        }
    }
}
