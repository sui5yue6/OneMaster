package juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Eirk
 * @Description
 * @Date 2024/2/27 13:53
 */
public class ScheduledThreadPoolExecutorDemo {
    public static void main(String[] args) {
//        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(5);
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(5);
        // 适用于一次性的执行，一个任务执行一次
        executor.schedule(new STask(5), 5, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(new STask(6), 5, 2, TimeUnit.SECONDS);
    }

    public static class STask implements Runnable {
        private int taskNum;

        public STask(int taskNum) {
            this.taskNum = taskNum;
        }

        @Override
        public void run() {
            System.out.println("scheduled task is running  " + taskNum);
        }
    }
}
