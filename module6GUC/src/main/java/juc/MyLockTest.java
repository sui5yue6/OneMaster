package juc;

import java.util.concurrent.locks.Lock;

/**
 * @author Eirk
 * @Description
 * @Date 2024/2/24 19:20
 */
public class MyLockTest {

    public static void main(String[] args) {
        new Thread(() -> {
            testLock();
        }, "t1").start();

        new Thread(() -> {
            testLock();
        }, "t2").start();

    }

    static Lock lock = new MyLock();

    public static void testLock() {
        lock.lock();
        try {
            System.out.println("get lock " + Thread.currentThread().getName());
            while (true) {
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
