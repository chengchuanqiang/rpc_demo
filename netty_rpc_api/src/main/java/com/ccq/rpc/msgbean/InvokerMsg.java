package com.ccq.rpc.msgbean;

import java.io.Serializable;

/**
 * @Description: 传输协议对象 国际公约传输协议对象
 * @Author: ChengChuanQiang
 * @Date: 2019/1/6 21:08
 */
public class InvokerMsg implements Serializable {

    // 类名称
    private String className;
    // 方法名称
    private String methodName;
    // 参数类型列表
    private Class<?>[] paramTypes;
    // 参数列表
    private Object[] values;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class<?>[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }
}
