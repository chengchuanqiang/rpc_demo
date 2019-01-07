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
        // 可以使用扫描的方式，将所有的提供的接口，在项目初始化的时候进行注册操作
        server.register(HelloService.class, HelloServiceImpl.class);
        server.start();
    }

}
