package juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Exchanger;

/**
 * @author Eirk
 * @Description
 * @Date 2024/2/26 15:22
 */
public class ExchangerTest {
    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            String aa = "aa";
            try {
                String exchange = exchanger.exchange(aa);
                System.out.println(aa + " " + exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
         });
        t1.start();
        Thread t2 = new Thread(() -> {
            String bb = "bb";
            try {
                String exchange = exchanger.exchange(bb);
                System.out.println(bb + " " + exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
         });
        t2.start();
        t1.join();
        t2.join();
        System.out.println("ok");
    }


}
