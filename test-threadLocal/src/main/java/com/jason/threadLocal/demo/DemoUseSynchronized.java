package com.jason.threadLocal.demo;

/**
 * 使用synchronized关键字实现处理多线程并发时变量的问题
 * <pre>
 *      synchronized和ThreadLocal区别：
 *      1.原理：synchronized采用的是“时间换空间”的方式。只提供了一份变量,让不同的线程排队访问。
 *              ThreadLocal采用的是“空间换时间”的方式，为每一个线程都提供了一份变量的副本,从而实现同时访问而互相不干扰。
 *      2.侧重点：synchronized：多个线程之间访问资源的同步。ThreadLocal：多线程中让每个线程之间的数据相互隔离。
 * </pre>
 *
 * @author WangChenHol
 * @date 2021-12-11 16:46
 **/
public class DemoUseSynchronized {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static void main(String[] args) {
        DemoUseSynchronized demo = new DemoUseSynchronized();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                synchronized (DemoUseSynchronized.class) {
                    demo.setContent("当前线程:" + Thread.currentThread().getName());
                    System.out.println("--------------------------------------");
                    System.out.println(Thread.currentThread().getName() + "------>" + demo.getContent());
                }
            });
            thread.setName("线程：" + i);
            thread.start();
        }
    }
}
