package juc;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author Eirk
 * @Description
 * @Date 2024/2/23 18:39
 */
public class SyncLockFlag {

    /*
    <dependency>
        <groupId>org.openjdk.jol</groupId>
        <artifactId>jol-core</artifactId>
        <version>0.16</version>
    </dependency>
    引包
     */
    /*
         -XX:BiasedLockingStartupDelay=0    偏向锁马上生效
     */
    static MyObject object = new MyObject();

    public static void main(String[] args) {
        System.out.println("无锁/  未偏向线程的偏向锁");
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
        object.hashCode();  // 如果有hashcode就会变成轻量级锁
        synchronized (object) {
            System.out.println("偏向锁");
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }
    }

    static class MyObject {

    }
}
