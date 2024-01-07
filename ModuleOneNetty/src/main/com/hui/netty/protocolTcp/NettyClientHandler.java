package hui.netty.protocolTcp;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


public class NettyClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client " + ctx);
        for (int i = 0; i < 20; i++) {
            //
            String msg = "今天天气冷，吃火锅";
            byte[] content = msg.getBytes(StandardCharsets.UTF_8);
            int length = msg.getBytes(StandardCharsets.UTF_8).length;
            MessageProtocol messageProtocol = new MessageProtocol(content, length);
            ctx.writeAndFlush(messageProtocol);
        }
    }


    int count;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageProtocol msg) throws Exception {
        int len = msg.getLen();
        byte[] content = msg.getContent();
        System.out.println("客户端接受到消息如下");
        System.out.println("长度如下 " + len);
        System.out.println("内容 " + new String(content, Charset.forName("utf-8")));
        System.out.println("客户端接收数量的长度 " + (++count));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("发生异常");
    }
}
