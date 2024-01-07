package netty.zeroCopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author Eirk
 * @Description
 * @Date 2023/12/20 0:22
 */
public class NewIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(7001));

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
        while(true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(true);

            int readcount = 0;
            while(-1!=readcount){
                try {
                    readcount = socketChannel.read(byteBuffer);
                } catch (IOException e) {
                    break;
                }
                byteBuffer.rewind();
            }
        }

    }
}
