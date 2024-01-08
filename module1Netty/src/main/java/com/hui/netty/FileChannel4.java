package com.hui.netty;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * @author Eirk
 * @Description
 * @Date 2023/12/17 21:53
 */
public class FileChannel4 {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("a.jpeg");
        final FileChannel channel1 = fileInputStream.getChannel();
        FileOutputStream fileOutputStream = new FileOutputStream("b.jpeg");
        final FileChannel channel2 = fileOutputStream.getChannel();

        channel2.transferFrom(channel1, 0, channel1.size());
        channel1.close();
        channel2.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
