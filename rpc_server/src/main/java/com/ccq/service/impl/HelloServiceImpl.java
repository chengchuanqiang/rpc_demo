package com.ccq.service.impl;

import com.ccq.service.HelloService;

/**
 * @Author: ChengChuanQiang
 * @Description:
 * @Date: Created in 2019/1/6 11:12
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHi(String name) {
        return "hi, " + name;
    }
}
