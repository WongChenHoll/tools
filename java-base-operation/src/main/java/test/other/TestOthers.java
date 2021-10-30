package test.other;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/**
 * @author ChenHol.Wong
 * @create 2020/8/25 12:25
 */
public class TestOthers {
    private volatile long counter;
    private static final AtomicLongFieldUpdater<TestOthers> updater = AtomicLongFieldUpdater.newUpdater(TestOthers.class, "counter");

    public void incr() {
        updater.incrementAndGet(this);
    }

    public static void main(String[] args) {
        // 自增长 线程安全计数器
        AtomicLong atomicLong = new AtomicLong();
        atomicLong.incrementAndGet();
        atomicLong.incrementAndGet();
        System.out.println(atomicLong.getAndIncrement());

        TestOthers testOthers = new TestOthers();
        testOthers.incr();
        testOthers.incr();
        testOthers.incr();
        testOthers.incr();
        testOthers.incr();
        testOthers.incr();
        System.out.println(testOthers.counter);


        int a = 80;
        int b = 50;
        int c = 90;

        //结果是条件不满足！
        if (a > 60 & b > 60) {
            System.out.println("a:" + a + "   b:" + b);
        } else {
            System.out.println("条件不满足！");
        }

        //结果是a:80   c:90
        if (a > 60 & c > 60) {
            System.out.println("a:" + a + "   c:" + c);
        } else {
            System.out.println("条件不满足！");
        }


        //结果是a:80   c:90
        if (a > 60 && c > 60) {
            System.out.println("a:" + a + "   c:" + c);
        } else {
            System.out.println("条件不满足！");
        }

        //结果是条件不满足！
        if (b > 60 && c > 60) {
            System.out.println("b:" + b + "   c:" + c);
        } else {
            System.out.println("条件不满足！");
        }

    }
}
