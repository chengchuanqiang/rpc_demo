package com.ccq.server;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: ChengChuanQiang
 * @Description: 服务中心实现
 * @Date: Created in 2019/1/6 11:15
 */
public class ServerCenter implements Server {
    /**
     * map: 服务端所有的可供客户端访问的接口，都注册到map中
     * key: 接口名称
     * value: 真正的接口实现
     */
    private static Map<String, Class> serverRegister = new HashMap<>();
    private int port;

    // 连接池 线程个数：服务器cpu个数
    private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private static boolean isRunning = false;

    public ServerCenter(int port) {
        this.port = port;
    }

    @Override
    public void start() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(), port));

            isRunning = true;
            while (isRunning) {
                System.out.println("start server ...");
                Socket socket = null;
                try {
                    socket = serverSocket.accept(); // 等待客户端链接
                    System.out.println("socket: " + socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                executorService.execute(new ServiceTask(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        isRunning = false;
        executorService.shutdown();
    }

    @Override
    public void register(Class service, Class serviceImpl) {
        serverRegister.put(service.getName(), serviceImpl);
    }

    /**
     * 任务
     */
    private static class ServiceTask implements Runnable {

        private Socket socket;

        public ServiceTask() {
        }

        public ServiceTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            ObjectInputStream inputStream = null;
            ObjectOutputStream outputStream = null;
            try {
                // 接受到请求，处理该请求
                inputStream = new ObjectInputStream(socket.getInputStream());
                String serviceName = inputStream.readUTF();
                String methodName = inputStream.readUTF();
                Class<?>[] parameterTypes = (Class<?>[]) inputStream.readObject();
                Object[] args = (Object[]) inputStream.readObject();

                // 根据客户端请求，到map中找到具体请求的接口
                Class serviceClass = serverRegister.get(serviceName);
                Method method = serviceClass.getMethod(methodName, parameterTypes);
                Object result = method.invoke(serviceClass.newInstance(), args);

                // 向客户端将方法执行完毕的返回值 传给客户端
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(result);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
