/**
 * @author Eirk
 * @Description
 * @Date 2024/2/23 10:02
 */
public class SleepReleaseCPU {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new MyThread(), "thread - " + i).start();
        }

    }

    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(50000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("finish");
            }
        }
    }
}
