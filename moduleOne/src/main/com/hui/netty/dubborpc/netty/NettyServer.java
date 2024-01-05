package hui.netty.dubborpc.netty;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * @author Eirk
 * @Description
 * @Date 2024/1/5 23:47
 */
public class NettyServer {
    // 编写一个方法，完成对nettyServer的初始化和启动
    private static void startServer0(String hostname, int port) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(1);

    }
}
