package com.ccq.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @Author: ChengChuanQiang
 * @Description:
 * @Date: Created in 2019/1/6 11:28
 */
public class Client {

    /**
     * 获取代表服务端接口的动态代理对象
     *
     * @param serviceInterface 请求接口
     * @param address          待请求服务端的ip:port
     * @param <T>              类型
     * @return 获取代表服务端接口的动态代理对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T getRemoteProxyObj(Class serviceInterface, InetSocketAddress address) {

        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class<?>[]{serviceInterface}, (proxy, method, args) -> {
            ObjectOutputStream outputStream = null;
            ObjectInputStream inputStream = null;
            try {
                // 客户端向服务端发送请求：请求某个具体的接口
                Socket socket = new Socket();
                socket.connect(address);
                outputStream = new ObjectOutputStream(socket.getOutputStream()); // 发送通过序列化流

                // 接口名, 方法名,
                outputStream.writeUTF(serviceInterface.getName());
                outputStream.writeUTF(method.getName());
                // 方法参数类型, 方法参数
                outputStream.writeObject(method.getParameterTypes());
                outputStream.writeObject(args);

                // 等待服务端处理
                // 同步阻塞等待服务器返回应答, 获取应答后返回
                inputStream = new ObjectInputStream(socket.getInputStream());
                return inputStream.readObject();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
