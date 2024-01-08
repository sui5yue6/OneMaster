package com.hui.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author Eirk
 * @Description
 * @Date 2023/12/18 22:58
 */
public class NIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        // 设置非阻塞模式
        socketChannel.configureBlocking(false);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);
        if (!socketChannel.connect(inetSocketAddress)) {
            while (!socketChannel.finishConnect()) {
                System.out.println("连接需要事件，客户端不会阻塞，额可以进行其他工作");
            }
        }
        String str = "hello world";
        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes()); // 就是这个字节数刚好和str的符合
        // 发送数据，将buffer的数据写入channel
        socketChannel.write(buffer);
        System.in.read();

    }
}
