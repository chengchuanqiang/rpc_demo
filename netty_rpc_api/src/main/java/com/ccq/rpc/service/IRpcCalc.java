package com.ccq.rpc.service;

/**
 * @Description: 远程调用接口
 * @Author: Chengchuanqiang
 * @Date: Created in 2019/1/6 21:01
 */
public interface IRpcCalc {

    int add(int a, int b);

    int sub(int a, int b);

    int mult(int a, int b);

    int div(int a, int b);

}
