package com.ccq.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.ccq.api.DemoService;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2019/1/7 17:32
 ***@Version 1.0.0
 ********************************/
@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello, " + name + " (from Spring Boot)";
    }
}
