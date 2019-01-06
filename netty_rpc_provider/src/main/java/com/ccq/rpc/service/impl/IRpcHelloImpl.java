package com.ccq.rpc.service.impl;

import com.ccq.rpc.service.IRpcHello;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/1/6 21:15
 */
public class IRpcHelloImpl implements IRpcHello {
    @Override
    public String hello(String msg) {
        return "Hello " + msg;
    }
}
