package com.hui.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;
import java.nio.charset.Charset;

/**
 * @author Eirk
 * @Description
 * @Date 2023/12/22 0:22
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if (msg instanceof HttpRequest) {

            System.out.println("pipeline hashcode "+ctx.pipeline().hashCode()+"  testHttpServerHandler hash= "+this.hashCode());
            // http协议用完断掉，每次刷新都会产生新的pipeline


            System.out.println("msg 类型 " + msg.getClass());
            System.out.println("客户端地址 " + ctx.channel().remoteAddress());
            // 回复信息给浏览器

            HttpRequest httpRequest = (HttpRequest)msg;
            URI uri = new URI(httpRequest.getUri());
            if("/favicon.ico".equals(uri.getPath())){
                System.out.println("请求了favicon.ico,不做响应");
                return;
            }


            ByteBuf content = Unpooled.copiedBuffer("hello 我是服务器", CharsetUtil.UTF_8);
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
//            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=UTF-8");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            ctx.writeAndFlush(response);
        }
    }
}
