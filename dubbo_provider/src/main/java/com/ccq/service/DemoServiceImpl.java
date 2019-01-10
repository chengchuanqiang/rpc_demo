package com.ccq.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.ccq.api.DemoService;
import org.springframework.beans.factory.annotation.Value;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2019/1/7 17:32
 ***@Version 1.0.0
 ********************************/
@Service(version = "1.0.0")
public class DemoServiceImpl implements DemoService {

    /**
     * The default value of ${dubbo.application.name} is ${spring.application.name}
     */
    @Value("${dubbo.application.name}")
    private String serviceName;

    @Override
    public String sayHello(String name) {
        System.out.println("test1111111111");
        return String.format("[%s] : Hello, %s", serviceName, name);
    }
}
