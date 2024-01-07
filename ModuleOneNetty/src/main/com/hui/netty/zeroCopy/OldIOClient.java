package netty.zeroCopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.Socket;

/**
 * @author Eirk
 * @Description
 * @Date 2023/12/20 1:05
 */
public class OldIOClient {
    public static void main(String[] args)throws Exception {
        Socket socket = new Socket("localhost", 8899);

        String filePath = "a2.zip";
        FileInputStream fileInputStream = new FileInputStream(filePath);
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        byte[] bytes = new byte[4096];
        int readCount = 0;
        //读取数据的总数量
        int total = 0;

        //开始时间
        long startTime = System.currentTimeMillis();

        while ((readCount = fileInputStream.read(bytes)) >= 0){
            total += readCount;
            //向服务端写数据
            dataOutputStream.write(bytes);
        }

        System.out.println("发送总字节数: " + total + ", 耗时: " + (System.currentTimeMillis() - startTime));

        dataOutputStream.close();
        socket.close();
        fileInputStream.close();
    }
}
