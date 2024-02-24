package juc;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author Eirk
 * @Description
 * @Date 2024/2/23 10:28
 */
public class ReadStackLog {
    public static void main(String[] args) throws JsonProcessingException {
        new Thread(new TimeWaitingThread(), "timeWaitingThread").start();
        new Thread(new Waiting(), "juc.Waiting").start();
        new Thread(new Blocked(), "juc.Blocked-1").start();
        new Thread(new Blocked(), "juc.Blocked-2").start();
    }

}

class TimeWaitingThread implements Runnable {
    //    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Waiting implements Runnable {
    @Override
    public void run() {
        synchronized (Waiting.class) {
            try {
                Waiting.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Blocked implements Runnable {
    @Override
//    @SneakyThrows
    public void run() {
        synchronized (Blocked.class) {
            while (true) {
                try {
                    Thread.sleep(10000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
