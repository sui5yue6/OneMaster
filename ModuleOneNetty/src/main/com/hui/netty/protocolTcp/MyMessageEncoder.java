package hui.netty.protocolTcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author Eirk
 * @Description
 * @Date 2023/12/27 23:36
 */
public class MyMessageEncoder extends MessageToByteEncoder<MessageProtocol> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MessageProtocol msg, ByteBuf byteBuf) throws Exception {
        System.out.println("MessageToByteEncoder 被调用");
        byteBuf.writeInt(msg.getLen());
        byteBuf.writeBytes(msg.getContent());
    }
}
