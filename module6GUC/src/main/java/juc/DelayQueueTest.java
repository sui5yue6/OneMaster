package juc;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author Eirk
 * @Description
 * @Date 2024/2/26 19:25
 */
public class DelayQueueTest {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<Message> delayQueue = new DelayQueue<>();
        Message m1 = new Message("first job", 10, TimeUnit.SECONDS);
        Message m2 = new Message("sec job", 15, TimeUnit.SECONDS);
        delayQueue.add(m2);
        delayQueue.add(m1);
        int size = delayQueue.size();
        for (int i = 0; i < size; i++) {
            Message take = delayQueue.take();
            System.out.println(take);

        }
    }

    public static class Message implements Delayed {
        private String name;
        private long time;

        public Message(String name, long time, TimeUnit timeUnit) {

            this.name = name;
            this.time = System.currentTimeMillis() + timeUnit.toMillis(time);
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return time - System.currentTimeMillis();
        }

        @Override
        public int compareTo(Delayed o) {
            Message msg = (Message) o;
            long timeDiff = this.time - msg.time;
            if (timeDiff <= 0) {
                return -1;
            }
            return 1;
        }

        @Override
        public String toString() {
            return "Message{" +
                    "name='" + name + '\'' +
                    ", time=" + time +
                    '}';
        }
    }


}
