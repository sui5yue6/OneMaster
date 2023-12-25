package hui.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author Eirk
 * @Description
 * @Date 2023/12/25 15:29
 */
public class NettyByteBuf01 {
    public static void main(String[] args) {
        // 创建一个对象，该对象包含一个数组arr，是一个byte【10】
        // netty的buffer中，不需要使用flip    底层维护了readIndex,writeIndex
        ByteBuf buffer = Unpooled.buffer(10);
        for (int i = 0; i < 10; i++) {
            buffer.writeByte(i);
        }
        for (int i = 0; i < buffer.capacity(); i++) {
            System.out.println(buffer.readByte());
//            System.out.println(buffer.getByte(i));
        }

    }
}
