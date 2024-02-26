package juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author Eirk
 * @Description
 * @Date 2024/2/26 21:08
 */
public class ForkJoinTest extends RecursiveTask<Integer> {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTest forkJoinTest = new ForkJoinTest(1, 100);
        ForkJoinTask<Integer> result = forkJoinPool.submit(forkJoinTest);
        Integer integer = result.get();
        System.out.println(integer);
    }

    private static final int THRESHOLD = 2;
    private int start;
    private int end;

    public ForkJoinTest(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        System.out.println("come in");
        int sum = 0;
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int middle = (start + end) / 2;
            ForkJoinTest leftForkJoin = new ForkJoinTest(start, middle);
            ForkJoinTest rightForkJoin = new ForkJoinTest(middle + 1, end);
            // 执行子任务
            leftForkJoin.fork();
            rightForkJoin.fork();
            // 等待子任务执行完，得到其结果
            Integer leftResult = leftForkJoin.join();
            Integer rightResult = rightForkJoin.join();
            sum = leftResult + rightResult;
        }
        return sum;
    }
}
