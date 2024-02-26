package juc;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author Eirk
 * @Description
 * @Date 2024/2/26 11:12
 */
public class BaseAtomicTest {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        atomicInteger.incrementAndGet();
        atomicInteger.get();
        int[] array = new int[2];
        array[0] = 10;
        array[1] = 10;
        // 原子的操作类型
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(array);
        atomicIntegerArray.compareAndSet(0, 10, 11);


        AtomicReference<Object> reference = new AtomicReference<>();
        reference.compareAndSet(new Object(), new Object());
        AtomicReferenceFieldUpdater<Object,Object> reference2 = new AtomicReferenceFieldUpdater<Object,Object> () {
            @Override
            public boolean compareAndSet(Object obj, Object expect, Object update) {
                return false;
            }
            @Override
            public boolean weakCompareAndSet(Object obj, Object expect, Object update) {
                return false;
            }
            @Override
            public void set(Object obj, Object newValue) {
            }
            @Override
            public void lazySet(Object obj, Object newValue) {
            }
            @Override
            public Object get(Object obj) {
                return null;
            }
            @Override
            public Object getAndSet(Object obj, Object newValue) {
                return super.getAndSet(obj, newValue);
            }
        };

    }
}
