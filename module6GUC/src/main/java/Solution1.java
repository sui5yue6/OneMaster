import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * @author Eirk
 * @Description
 * @Date 2024/2/24 9:52
 */
public class Solution1 {

    Queue<Integer> queue = new LinkedList<>();

    static class MyTask implements Runnable {
        private Object data;
        private int retry_epoch;

        @Override
        public void run() {
        }
    }

    //    public static void main(String[] args) {
    ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 6000, TimeUnit.SECONDS, new LinkedBlockingDeque(1000), new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            MyTask task = (MyTask) r;
            System.out.println(task); // 这里的task进行持久化
            System.out.println("存入db或者发送到mq等待重试，此时retry_epoch为0");
            int windowsSize = 300;
            long currentTimeMillis = System.currentTimeMillis();
            if (!queue.isEmpty() && currentTimeMillis - queue.peek() > windowsSize * 1000) {
                queue.poll();
            }
            queue.offer((int) (System.currentTimeMillis()));
        }
    });

    //    }
    public static void main(String[] args) {


    }

    static class myexecutor extends ThreadPoolExecutor {
        Queue<Integer> queue = new LinkedList<>();

        public myexecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            this.getTaskCount();
            this.getActiveCount();
            this.getCompletedTaskCount();
            // 将这些监控信息发送到mq中，消费端通过指标生成监控图

        }
        /*
        hippo4j 需要中心的支持，比较重量级。

        频繁的发送监控消息对性能有影响吗

        滑动时间窗口的原理是什么


         */

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            if(!queue.isEmpty()&&this.getQueue().size()<1000    ){
                // 重试被拒绝的任务
                System.out.println("从db或者mq中读取被拒绝的任务");
                System.out.println("判断任务重试次数，如果超过某个阈值，则触发警告");
            }
        }
    }
}

























