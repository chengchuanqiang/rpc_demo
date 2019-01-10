package com.ccq.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ccq.api.DemoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2019/1/7 17:43
 ***@Version 1.0.0
 ********************************/
@RestController
public class DemoController {

    @Reference(version = "1.0.0")
    private DemoService demoService;

    @RequestMapping("sayHello/{name}")
    public String sayHello(@PathVariable String name) {
        return demoService.sayHello(name);
    }

}
