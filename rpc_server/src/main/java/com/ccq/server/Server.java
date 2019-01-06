package com.ccq.server;

/**
 * @Author: ChengChuanQiang
 * @Description: 服务中心
 * @Date: Created in 2019/1/6 11:14
 */
public interface Server {

    void start();

    void stop();

    /**
     * 注册服务
     */
    void register(Class service, Class serviceImpl);
}
