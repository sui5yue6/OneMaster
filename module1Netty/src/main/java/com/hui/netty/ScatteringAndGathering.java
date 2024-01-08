package com.hui.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author Eirk
 * @Description
 * @Date 2023/12/18 19:10
 */
public class ScatteringAndGathering {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);
        // 绑定端口到socket
        serverSocketChannel.socket().bind(inetSocketAddress);
// 创建buffer数组
        ByteBuffer[] byteBuffer = new ByteBuffer[2];
        byteBuffer[0] = ByteBuffer.allocate(5);
        byteBuffer[1] = ByteBuffer.allocate(3);
        SocketChannel socketChannel = serverSocketChannel.accept();
        int messageLength = 8;
        while (true) {
            int byteRead = 0;
            while (byteRead < messageLength) {
                long l = socketChannel.read(byteBuffer);
                byteRead += l;
                System.out.println("byteRead " + byteRead);
                Arrays.asList(byteBuffer).stream().map(buffer -> "position " + buffer .position() + " limit " + buffer.limit()).forEach(System.out::println);
            }
            // 将所有的buffer进行反转
            Arrays.asList(byteBuffer).forEach(buffer -> buffer.flip());
            // 将数据读出显示到客户端
            long byteWrite = 0;
            while (byteWrite < messageLength) {
                long l = socketChannel.write(byteBuffer);
                byteWrite += l;
            }
            // 将所有的buffer进行clear
            Arrays.asList(byteBuffer).forEach(buffer -> buffer.clear());
            System.out.println("byteRead " + byteRead + "   byteWrite " + byteWrite + "  messageLength " + messageLength);
        }
    }
}
