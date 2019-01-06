package com.ccq.server;

import com.ccq.service.HelloService;
import com.ccq.service.impl.HelloServiceImpl;

/**
 * @Author: ChengChuanQiang
 * @Description: 测试 rpc 服务
 * @Date: Created in 2019/1/6 12:39
 */
public class RpcServerTest {

    public static void main(String[] args) {
        Server server = new ServerCenter(9999);
        server.register(HelloService.class, HelloServiceImpl.class);
        server.start();
    }

}
