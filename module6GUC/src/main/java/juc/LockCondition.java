package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Eirk
 * @Description
 * @Date 2024/2/26 0:44
 */
public class LockCondition<T> {
    private Object[] items;
    private int addIndex, removeIndex, count;
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public LockCondition(int size) {
        items = new Object[size];
    }

    // 添加一个元素
    public void add(T t) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();  // 生产者暂停生产，到等待队列
            }
            items[addIndex] = t;
            if (++addIndex == items.length) {
                addIndex = 0;
            }
            ++count;
            notEmpty.signal();   // 告诉消费者进行消费
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public T remove() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await(); // 消费者的线程无法进行消费，进去等待队列
            }
            Object x = items[removeIndex];
            if (++removeIndex == items.length)
                removeIndex = 0;
            count--;
            notFull.signal(); // 通知生产者线程工作
            return (T) x;
        } finally {
            lock.unlock();
        }
    }
}



















