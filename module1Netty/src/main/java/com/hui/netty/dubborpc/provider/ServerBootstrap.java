package com.hui.netty.dubborpc.provider;

import hui.netty.dubborpc.netty.NettyServer;

/**
 * @author Eirk
 * @Description
 * @Date 2024/1/5 23:41
 */
public class ServerBootstrap {
    public static void main(String[] args) {
        //
        NettyServer.startServer("127.0.0.1",7001);

    }
}
