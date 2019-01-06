package com.ccq.rpc.registry;

import com.ccq.rpc.msgbean.InvokerMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: 自定义的 Handler 要实现适配器
 * @Author: ChengChuanQiang
 * @Date: 2019/1/6 21:40
 */
public class RegistryHandler extends ChannelInboundHandlerAdapter {

    // 提供方地址
    public static Map<String, Object> registryMap = new ConcurrentHashMap<>();

    // 全自动扫描集合 全限定类名
    private List<String> classCache = new ArrayList<>();

    public RegistryHandler() {
        // 服务发现
        scannerClass("com.ccq.rpc.service.impl");
        // 服务注册
        doRegister();
    }

    /**
     * 全自动扫描
     *
     * @param packageName 包名
     */
    private void scannerClass(String packageName) {
        URL url = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.", "/"));
        if (null != url) {
            File dir = new File(url.getFile());
            File[] files = dir.listFiles();
            if (null != files) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        scannerClass(packageName + "." + file.getName());
                    } else {
                        classCache.add(packageName + "." + file.getName().replace(".class", "").trim());
                    }
                }
            }
        }
    }

    /**
     * 服务注册
     */
    private void doRegister() {
        if (classCache.size() <= 0) {
            System.out.println("没有发现任何服务名称，初始化有问题");
            return;
        }
        for (String className : classCache) {
            try {
                Class<?> clazz = Class.forName(className);
                Class<?> interfaces = clazz.getInterfaces()[0];
                registryMap.put(interfaces.getName(), clazz.newInstance());
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Object result = new Object();
        InvokerMsg request = (InvokerMsg) msg;
        if (registryMap.containsKey(request.getClassName())) {
            Object clazz = registryMap.get(request.getClassName());
            Method method = clazz.getClass().getMethod(request.getMethodName(), request.getParamTypes());
            result = method.invoke(clazz, request.getValues());
        }

        // netty异步写给客户端
        ctx.write(result);
        ctx.flush();
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常异常！！！！！！！");
    }
}
