package juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Eirk
 * @Description
 * @Date 2024/2/27 15:52
 */
public class FutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            return "hello";
        });
        futureTask.run();
        System.out.println(futureTask.get());
    }
}
