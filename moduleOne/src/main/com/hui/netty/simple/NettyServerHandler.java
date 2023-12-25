package netty.simple;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;


public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    // 读取数据的事件
    /*
           channelHandlerContext 上下文对象——含有管道，通道，pipeline
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println(msg);
//        System.out.println("server ctx =   " + ctx);
//        ByteBuf byteBuf = (ByteBuf) msg;
//        System.out.println("msg " + byteBuf.toString(CharsetUtil.UTF_8));
//        System.out.println("客户端地址 " + ctx.channel().remoteAddress());

        System.out.println("服务器读取线程  " + Thread.currentThread().getName() + "  channel =  " + ctx.channel());
        System.out.println("server ctx =   " + ctx);


//        ctx.channel().eventLoop().execute(()->{
//            try {
//                Thread.sleep(10 * 1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端222",CharsetUtil.UTF_8));
//        });
//        ctx.channel().eventLoop().execute(()->{
//            try {
//                Thread.sleep(10 * 1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端333",CharsetUtil.UTF_8));
//        });
        ctx.channel().eventLoop().schedule(() -> {
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端333", CharsetUtil.UTF_8));
        }, 5, TimeUnit.SECONDS);
        System.out.println("go on ..");
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
