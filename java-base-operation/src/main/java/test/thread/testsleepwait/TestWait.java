package test.thread.testsleepwait;

/**
 * 测试wait会释放lock锁
 *
 * @author ChenHol.Wong
 * @create 2020/8/26 22:31
 */
public class TestWait {
    public static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                testWait();
            }
        }, "线程1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                testWait();
            }
        }, "线程2").start();
    }

    public static void testWait() {
        synchronized (lock) {
            try {
                System.out.println(Thread.currentThread().getName() + "正在执行...");
                lock.wait(1000);
                System.out.println(Thread.currentThread().getName() + "睡眠结束...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
