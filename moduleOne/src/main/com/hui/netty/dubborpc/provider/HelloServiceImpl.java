package hui.netty.dubborpc.provider;

import hui.netty.dubborpc.publicInterface.HelloService;

/**
 * @author Eirk
 * @Description
 * @Date 2024/1/5 23:33
 */
public class HelloServiceImpl implements HelloService {
    // 当有消费方调用该方法时就返回一个结果
    @Override
    public String hello(String mes) {
        System.out.println("收到客户端消息 = "+mes);
        if(mes!=null){
            return "你好客户端 已收到消息 : "+mes;
        }else {
            return "你好客户端 已收到消息   "+mes;
        }
     }
}
