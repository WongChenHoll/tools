package com.jason.threadLocal.demo;

/**
 * 使用ThreadLocal的例子。
 * 无论执行多少次，每个线程使用的数据总是本线程内的变量值，不会出现变量值混乱的情况。
 * <pre>
 *     因此我们可以得知 ThreadLocal 的作用是：
 *      提供线程内的局部变量，不同的线程之间不会相互干扰，这种变量在线程的生命周期内起作用，减少同一个线程内多个函数或组件之间一些公共变量传递的复杂度。
 *          1. 线程并发: 在多线程并发的场景下
 *          2. 传递数据: 我们可以通过ThreadLocal在同一线程，不同组件中传递公共变量
 *          3. 线程隔离: 每个线程的变量都是独立的，不会互相影响
 * </pre>
 *
 * @author WangChenHol
 * @date 2021-12-5 22:56
 **/
public class DemoUseThreadLocal {
    ThreadLocal<String> threadLocal = new ThreadLocal<>();
    private String content;

    public String getContent() {
//        return content;
        return threadLocal.get();
    }

    public void setContent(String content) {
//        this.content = content;
        threadLocal.set(content);
    }

    public static void main(String[] args) {
        DemoUseThreadLocal demo = new DemoUseThreadLocal();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                demo.setContent("当前线程:" + Thread.currentThread().getName());
                System.out.println("--------------------------------------");
                System.out.println(Thread.currentThread().getName() + "------>" + demo.getContent());
            });
            thread.setName("线程：" + i);
            thread.start();
        }
    }
}
