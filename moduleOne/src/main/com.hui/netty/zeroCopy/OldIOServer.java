package netty.zeroCopy;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Eirk
 * @Description
 * @Date 2023/12/20 1:05
 */
public class OldIOServer {
    public static void main(String[] args)throws Exception {
        ServerSocket serverSocket = new ServerSocket(8899);
        System.out.println("server start");
        while (true){
            //阻塞，等待连接到来
            Socket socket = serverSocket.accept();

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            try {
                byte[] bytes = new byte[4096];

                while (true){
                    int readCount = dataInputStream.read(bytes);
                    if(readCount == -1){
                        break;
                    }
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}

