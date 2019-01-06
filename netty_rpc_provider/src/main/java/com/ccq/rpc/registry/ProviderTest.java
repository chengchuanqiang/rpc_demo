package com.ccq.rpc.registry;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/1/6 22:46
 */
public class ProviderTest {

    public static void main(String[] args) {
        RpcRegister register = new RpcRegister(9999);
        register.start();
    }
}
