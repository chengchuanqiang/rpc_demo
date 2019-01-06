package com.ccq.Proxy;

import com.ccq.rpc.service.IRpcCalc;
import com.ccq.rpc.service.IRpcHello;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/1/6 22:44
 */
public class ConsumerTest {

    public static void main(String[] args) {
        IRpcHello rpcHello = RpcProxy.create(IRpcHello.class);

        System.out.println(rpcHello.hello("ccq"));

        IRpcCalc rpcCalc = RpcProxy.create(IRpcCalc.class);
        System.out.println("6 + 2 =" + rpcCalc.add(6, 2));
    }
}
