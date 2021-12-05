package com.jason.threadLocal.demo;

/**
 * 不使用ThreadLocal时的例子。
 * 每个线程取出来的值和放进去的值有时并不对应正确。就会出现线程A使用了线程B中的数据。没有起到线程隔离。
 *
 * @author WangChenHol
 * @date 2021-12-5 22:46
 **/
public class Demo01 {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static void main(String[] args) {
        Demo01 demo = new Demo01();
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
