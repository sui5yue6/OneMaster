package juc;

/**
 * @author Eirk
 * @Description
 * @Date 2024/2/23 17:31
 */
public class SyncUsingWay {
    // 谁调用这个方法，锁就所用与谁身上
    public synchronized void syncMethod() {
        System.out.println("syncMethod");
    }
    public synchronized static void staticSyncMethod() {
        System.out.println("staticSyncMethod");
    }
    public void method() {
        synchronized (this) {
            System.out.println("method");
        }
    }
}
