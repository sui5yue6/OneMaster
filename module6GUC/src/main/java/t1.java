import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Eirk
 * @Description
 * @Date 2024/2/24 13:35
 */
public class t1 {

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Object> objects = new ArrayList<>();
        AtomicReference<Object> objectAtomicReference = new AtomicReference<>();
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.lockInterruptibly();
        lock.isHeldByCurrentThread();  // 当前线程是不是拿到执行权的线程
        lock.unlock();

        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        readWriteLock.readLock().lock();
        readWriteLock.writeLock().lock();

        ArrayBlockingQueue<Integer> integers = new ArrayBlockingQueue<Integer>(5);
        LinkedBlockingQueue<Integer> integers2 = new LinkedBlockingQueue<>();

        PriorityBlockingQueue<Integer> integers3 = new PriorityBlockingQueue<>();


    }
}
