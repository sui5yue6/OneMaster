/**
 * @author Eirk
 * @Description
 * @Date 2024/2/23 10:02
 */
public class ThreadPriority {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "  " + Thread.currentThread().getPriority());
        Thread thread = new MyThread("t1");
        thread.setPriority(10);
        Thread thread1 = new MyThread("t2");
        thread1.setPriority(1);
        thread1.start();
        thread.start();

    }

    public static class MyThread extends Thread {
        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "  " + i);
            }
        }
    }
}
