package hui.netty.protocolTcp;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


public class NettyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {
    int count ;
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageProtocol messageProtocol) throws Exception {
        System.out.println(messageProtocol);
        System.out.println(++count);

        byte[] responseContent = UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8);
         int length = responseContent.length;
        MessageProtocol messageProtocol2  = new MessageProtocol(responseContent, length);
        channelHandlerContext.writeAndFlush(messageProtocol2);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端", CharsetUtil.UTF_8));// 将数据写入到缓存并刷新
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 关闭通道
        ctx.close();
    }
}
