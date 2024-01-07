package hui.netty.protocolTcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @author Eirk
 * @Description
 * @Date 2023/12/27 23:36
 */
public class MyMessageDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext,
                          ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("mymessagedecoder 被调用");
        // 将二进制字节码
        int length = byteBuf.readInt();
        byte[] content = new byte[length];
        byteBuf.readBytes(content);

        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(length);
        messageProtocol.setContent(content);
        list.add(messageProtocol);
    }
}
