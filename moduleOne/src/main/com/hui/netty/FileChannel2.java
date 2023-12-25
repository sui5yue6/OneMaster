package netty;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;

/**
 * @author Eirk
 * @Description
 * @Date 2023/12/17 21:53
 */
public class FileChannel2 {
    public static void main(String[] args) throws Exception {
        final File file = new File("d:\\file.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        java.nio.channels.FileChannel channel = fileInputStream.getChannel();
        final ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        channel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();
    }
}
