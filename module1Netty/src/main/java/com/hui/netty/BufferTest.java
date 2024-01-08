package com.hui.netty;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Eirk
 * @Description
 * @Date 2023/12/18 13:08
 */
public class BufferTest {
    public static void main(String[] args) {
        final ByteBuffer allocate = ByteBuffer.allocate(64);
        allocate.putInt(100);
        allocate.putLong(9);
        allocate.putChar('c');
        allocate.putShort((short) 4);
        allocate.flip();
        System.out.println(allocate.getInt());
        System.out.println(allocate.getLong());
//        System.out.println(allocate.getLong());
        System.out.println(allocate.getChar());
        System.out.println(allocate.getShort());

    }
}

class BufferTest2 {
    public static void main(String[] args) {
        final ByteBuffer buffer = ByteBuffer.allocate(64);
        for (int i = 0; i < 64; i++) {
            buffer.put((byte) i);
        }
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.getClass());
        readOnlyBuffer.flip();
        while (readOnlyBuffer.hasRemaining()) {
            System.out.println(readOnlyBuffer.get());
        }
        readOnlyBuffer.put((byte) 1);
    }

}

class BufferTest3 {
    public static void main(String[] args) throws IOException {
        // 让文件直接在内存中修改，操作系统不需要拷贝一次，堆外内存
        RandomAccessFile randomAccessFile = new RandomAccessFile("1.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);//将字节映射到内存
        mappedByteBuffer.put(0, (byte) 'H');
        mappedByteBuffer.put(3, (byte) '9');
        randomAccessFile.close();

    }
}