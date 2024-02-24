package juc;

/**
 * @author Eirk
 * @Description
 * @Date 2024/2/23 10:28
 */
public class ThreadDeamon {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("finally"); // finally不能保障守护线程的最终执行
            }
            System.out.println("finish");
        }, "deamonThread");
        thread.start();
        thread.setDaemon(true);

    }
}
