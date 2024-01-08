package com.hui.netty;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;

/**
 * @author Eirk
 * @Description
 * @Date 2023/12/17 21:53
 */
public class FileChannel {
    public static void main(String[] args) throws Exception {
        String str = "hello 尚硅谷";
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\file.txt");
        java.nio.channels.FileChannel channel = fileOutputStream.getChannel();
        final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(str.getBytes());
        byteBuffer.flip();
        channel.write(byteBuffer);
        fileOutputStream.close();
    }
}
