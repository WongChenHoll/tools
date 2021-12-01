package com.jason.test.common.bean;

import java.util.Date;

/**
 * 请求类
 *
 * @author ChenHol.Wong
 * @create 2020/7/27 21:25
 */
public class BaseRequest<T> {
    private Date requestTime = new Date();
    private String version;
    private T data;

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
