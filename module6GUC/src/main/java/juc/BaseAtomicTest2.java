package juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author Eirk
 * @Description
 * @Date 2024/2/26 11:12
 */
public class BaseAtomicTest2 {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                atomicInteger.incrementAndGet();
            }
            countDownLatch.countDown();
        });
        t1.start();
//        t1.join();
        countDownLatch.await();
        System.out.println(atomicInteger.get());
    }
}
