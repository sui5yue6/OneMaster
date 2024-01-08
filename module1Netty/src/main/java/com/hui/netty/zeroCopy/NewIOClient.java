package com.hui.netty.zeroCopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author Eirk
 * @Description
 * @Date 2023/12/20 0:22
 */
public class NewIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 7001));
        String filename = "a2.zip";
        FileChannel fileChannel = new FileInputStream(filename).getChannel();
        long startTime = System.currentTimeMillis();
        // windows下，一次调用transferto只能发送8m

        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        long endTime = System.currentTimeMillis();
        System.out.println("一共耗时 " + (endTime - startTime));

    }
}
