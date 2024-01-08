package com.hui.netty.groupChat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.aspectj.weaver.ast.Var;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Eirk
 * @Description
 * @Date 2023/12/25 20:59
 */
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

//    public static List<Channel> channels = new ArrayList<Channel>();

    public static Map<String, Channel> channelMap = new HashMap<>();


    // 定义一个channel组，管理所有channel
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 连接建立的时候，第一个执行
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("客户端  " + channel.remoteAddress() + " 加入聊天  " + sdf.format(new Date()));
        channelGroup.add(channel);


    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "  上线了");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "  离线了");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " 客户端离开了");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            if (channel != ch) {
                ch.writeAndFlush("客户 " + channel.remoteAddress() + "  发送了消息" + msg);
            } else {
                ch.writeAndFlush("自己发送了消息");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("关闭通道");
    }
}
