package com.hui.netty.dubborpc.customer;

import hui.netty.dubborpc.netty.NettyClient;
import com.hui.netty.dubborpc.publicInterface.HelloService;

/**
 * @author Eirk
 * @Description
 * @Date 2024/1/7 0:33
 */
public class ClientBootstrap {

    // 这里定义协议头
    public static final String providerName = "HelloService#hello#";

    public static void main(String[] args) throws InterruptedException {
        // 创建一个消费者
        NettyClient customer = new NettyClient();
        HelloService service = (HelloService) customer.getBean(HelloService.class, providerName);
        for(;;){
            Thread.sleep(2*1000);
            String res = service.hello("你好 dubbo");
            System.out.println("调用结果 " + res);
        }


    }
}
