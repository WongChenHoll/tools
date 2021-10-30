package test.thread.testsleepwait;

/**
 * 测试sleep方法不会释放lock锁
 *
 * @author ChenHol.Wong
 * @create 2020/8/26 22:17
 */
public class TestSleep {

    public static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                testSleep();
            }
        }, "线程1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                testSleep();
            }
        }, "线程2").start();
    }

    public static void testSleep() {
        synchronized (lock) {
            try {
                System.out.println(Thread.currentThread().getName() + "正在执行...");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "睡眠结束...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


}
