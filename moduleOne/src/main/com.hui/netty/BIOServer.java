package netty;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Eirk
 * @Description
 * @Date 2023/12/17 11:28
 */
public class BIOServer {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(6666);
        while (true) {
            final Socket accept = serverSocket.accept(); // 阻塞
            System.out.println("连接到一个客户端");
            executorService.execute(() -> {
                handler(accept);
            });
        }
    }

    public static void handler(Socket socket) {
        try {
            final byte[] bytes = new byte[1024];
            final InputStream inputStream = socket.getInputStream();
            while (true) {
                final int read = inputStream.read(bytes); // 阻塞
                if (read != -1) {
                    System.out.println(new String(bytes, 0, read));// 输出客户端发出的数据
                }else{
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭client连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
