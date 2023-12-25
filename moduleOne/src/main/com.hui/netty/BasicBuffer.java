package netty;

import java.nio.IntBuffer;

/**
 * @author Eirk
 * @Description
 * @Date 2023/12/17 17:33
 */
public class BasicBuffer {

    public static void main(String[] args) {
        final IntBuffer allocate = IntBuffer.allocate(5);
        for (int i = 0; i < allocate.capacity(); i++) {
            allocate.put(i * 2);
        }
        allocate.flip();
        while (allocate.hasRemaining()) {
            System.out.println(allocate.get());
        }
        // 面向缓冲区，面向块。 多路复用技术
    }
}
