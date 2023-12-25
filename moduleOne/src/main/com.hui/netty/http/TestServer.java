package netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author Eirk
 * @Description
 * @Date 2023/12/22 0:21
 */
public class TestServer {
    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 创建服务器端的启动对象，配置参数
            ServerBootstrap serverBootstrap = new ServerBootstrap()
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new TestServerInitializer());
            System.out.println("服务器 ready");
            // 绑定一个端口并且同步，生成一个channelfuture对象
            ChannelFuture channelFuture = serverBootstrap.bind(8668).sync(); // 启动服务器，绑定端口

            // 对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            bossGroup.shutdownGracefully();// 优雅关闭
            workerGroup.shutdownGracefully();
        }

    }
}
