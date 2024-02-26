package juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Eirk
 * @Description
 * @Date 2024/2/26 15:04
 */
public class CyclicBarrierTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        int[] array = new int[2];
        Thread t1 = new Thread(() -> {
            array[0] = 3 * 4;
            countDownLatch.countDown();
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            array[1] = 3 + 4;
            countDownLatch.countDown();
        });
        t2.start();
        countDownLatch.await();
        System.out.println(array[0] + array[1]);
    }

}

class CyclicBarrierTest2 {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cb = new CyclicBarrier(2);
        int[] array = new int[2];
        Thread t1 = new Thread(() -> {
            array[0] = 3 * 4;
            try {
                cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            array[1] = 3 + 4;
            try {
                cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        t2.start();
        try {
            cb.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        // 都所有的线程都到达屏障了，才可以执行
        System.out.println(array[0] + array[1]);
    }

}

class CyclicBarrierTest3 implements Runnable {
    @Override
    public void run() {
        // 都所有的线程都到达屏障了，才可以执行
        System.out.println(array[0] + array[1]);
    }

    CyclicBarrier cb = new CyclicBarrier(2, this);
    int[] array = new int[2];

    public static void main(String[] args) {
        CyclicBarrierTest3 cyclicBarrierTest3 = new CyclicBarrierTest3();
        cyclicBarrierTest3.cal();
    }
    public void cal() {
        Thread t1 = new Thread(() -> {
            array[0] = 3 * 4;
            try {
                cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            array[1] = 3 + 4;
            try {
                cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        t2.start();
    }
}