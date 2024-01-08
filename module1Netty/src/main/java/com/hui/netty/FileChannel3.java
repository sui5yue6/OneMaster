package com.hui.netty;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Eirk
 * @Description
 * @Date 2023/12/17 21:53
 */
public class FileChannel3 {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("1.txt");
        final FileChannel channel1 = fileInputStream.getChannel();
        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");
        final FileChannel channel2 = fileOutputStream.getChannel();
        final ByteBuffer byteBuffer = ByteBuffer.allocate(20);
        while (true) {
            byteBuffer.clear();
            final int read = channel1.read(byteBuffer);
            System.out.println(read);
            if (read == -1) {
                break;
            }
            byteBuffer.flip();
            channel2.write(byteBuffer);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
