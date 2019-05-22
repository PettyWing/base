package com.xyc.event;

/**
 * Created by lenovo on 2018/1/28.
 */
public class BaseHttpRspEvent extends BaseEvent {
    private boolean isSuccess;
    private Class<?> target;
    private String msg;

    public boolean isSuccess() {
        return isSuccess;
    }

    public BaseHttpRspEvent setSuccess(boolean success) {
        isSuccess = success;
        return this;
    }

    public Class<?> getTarget() {
        return target;
    }

    public BaseHttpRspEvent setTarget(Class<?> target) {
        this.target = target;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public BaseHttpRspEvent setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
