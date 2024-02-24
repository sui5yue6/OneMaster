import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Eirk
 * @Description
 * @Date 2024/2/24 13:35
 */
public class t1 {

    public static void main(String[] args) {
        ArrayList<Object> objects = new ArrayList<>();
        AtomicReference<Object> objectAtomicReference = new AtomicReference<>();
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
    }
}
