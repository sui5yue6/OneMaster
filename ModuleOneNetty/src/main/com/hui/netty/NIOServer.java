package netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Eirk
 * @Description
 * @Date 2023/12/18 22:00
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        Selector selector = Selector.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        // 设置非阻塞
        serverSocketChannel.configureBlocking(false);
        // serversocketchannel注册到selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            if (selector.select(1000) == 0) {
                System.out.println("服务器等待了1秒，无连接");
                continue;
            }
            // 返回的是关注事件的集合。通过selectionKeys返回关注的事件的集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                //
                SelectionKey key = iterator.next();
                // 根据key对应的通道发生的事件做相应处理
                if (key.isAcceptable()) {
                    // 给该客户端生成一个socketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept(); // 虽然这个方法是阻塞的，但是这里已经知道有事件过来了
                    // 将socketchannel设置为非阻塞
                    socketChannel.configureBlocking(false);
                    System.out.println("客户端连接成功" + socketChannel.hashCode());
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    System.out.println("注册的selectionkey的数量" + selector.keys().size());
                }
                if (key.isReadable()) {
                    // 通过key反向获取到channel
                    SocketChannel channel = (SocketChannel) key.channel();
                    // 获取到该channel关联的buffer
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    channel.read(buffer);
                    System.out.println("from 客户端 " + new String(buffer.array()));
                }


                // 手动从集合中移除当前的selectionKey防止重复操作
                iterator.remove();
            }
        }
    }
}
