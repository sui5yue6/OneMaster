package com.hui.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * @author Eirk
 * @Description
 * @Date 2023/12/25 15:29
 */
public class NettyByteBuf02 {
    public static void main(String[] args) {
         ByteBuf buffer = Unpooled.copiedBuffer("hello,world", Charset.forName("utf-8"));
        if (buffer.hasArray()) {
            byte[] content = buffer.array();
            System.out.println(new String(content,Charset.forName("utf-8")));
            System.out.println("byteBuf = "+buffer);
            System.out.println(buffer.getByte(0 ));
            System.out.println(buffer.readerIndex());
            System.out.println(buffer.writerIndex());
            System.out.println(buffer.capacity());
            System.out.println(buffer.readableBytes());
            System.out.println(buffer.getCharSequence(0,4,Charset.forName("utf-8")));
        }

//        for (int i = 0; i < 10; i++) {
//            buffer.writeByte(i);
//        }
//        for (int i = 0; i < buffer.capacity(); i++) {
//            System.out.println(buffer.readByte());
////            System.out.println(buffer.getByte(i));
//        }

    }
}
