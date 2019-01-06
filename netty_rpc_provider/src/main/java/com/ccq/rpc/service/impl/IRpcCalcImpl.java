package com.ccq.rpc.service.impl;

import com.ccq.rpc.service.IRpcCalc;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/1/6 21:14
 */
public class IRpcCalcImpl implements IRpcCalc {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int sub(int a, int b) {
        return a - b;
    }

    @Override
    public int mult(int a, int b) {
        return a * b;
    }

    @Override
    public int div(int a, int b) {
        return a / b;
    }
}
