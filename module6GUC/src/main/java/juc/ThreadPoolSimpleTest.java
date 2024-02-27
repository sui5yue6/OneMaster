package juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Eirk
 * @Description
 * @Date 2024/2/27 10:38
 */
public class ThreadPoolSimpleTest {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(5));
        for (int i = 0; i < 15; i++) {
            MyTask task = new MyTask(i);
            executor.execute(task);
            System.out.println(executor.getPoolSize() + "  " + executor.getQueue().size() + "  " + executor.getCompletedTaskCount());
        }
    }

    public static class MyTask implements Runnable {
        private int taskNum;

        public MyTask(int i) {
            taskNum = i;
        }

        @Override
        public void run() {
            System.out.println("正在执行" + taskNum);
            try {
                Thread.currentThread().sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行完毕" + taskNum);
        }
    }
}
