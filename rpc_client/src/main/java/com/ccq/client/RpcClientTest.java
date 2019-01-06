package com.ccq.client;

import com.ccq.service.HelloService;

import java.net.InetSocketAddress;

/**
 * @Author: ChengChuanQiang
 * @Description:
 * @Date: Created in 2019/1/6 12:41
 */
public class RpcClientTest {

    public static void main(String[] args) throws ClassNotFoundException {
        HelloService helloService = Client.getRemoteProxyObj(HelloService.class, new InetSocketAddress(9999));
        String res = helloService.sayHi("ccq");
        System.out.println(res);
    }

}
