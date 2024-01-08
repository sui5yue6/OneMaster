package com.hui.netty.websocket;

 import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author Eirk
 * @Description
 * @Date 2023/12/25 22:32
 */
public class MyServer {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            // 获取到pipeline
                            channel.pipeline()
                                    .addLast(new HttpServerCodec()) // 用块写的，添加chuckedWrite处理器
                                    .addLast(new ChunkedWriteHandler())
                                    .addLast(new HttpObjectAggregator(8192)) // 可以将多个端聚合起来。数据量很大的时候会有多次http请求
                                    // 对于websocket数据是以帧的形式传递
                                    // 核心是将http协议升级为ws协议，保持长连接
                                    .addLast(new WebSocketServerProtocolHandler("/hello"))
                                    .addLast("decoder", new StringDecoder())
                                    .addLast("encoder", new StringEncoder())
                                    .addLast(new IdleStateHandler(3, 5, 7, TimeUnit.SECONDS))
                                    // 没有读，就会发送心跳检测包，没有写发送。既没有读也没有写
                                    // 将会传递给下一个handler  userEventTiggered
                                    .addLast(new MyTextWebsocketFrameHandler());

                        }
                    });
            System.out.println("netty 服务器启动");
            ChannelFuture channelFuture = bootstrap.bind(7001).sync();

            // 这段代码不加的话，无法启动
            // 监听关闭
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
