package com.jason.test.project.model;

/**
 * @author WongChenHoll
 * @description
 * @date 2023-2-1 星期三 15:57
 **/
public class CurlCommand {

    private String curl; // curl命令
    private String bodyData; // 请求体数据
    private String formData; // form表单数据
    private long timeout = 3; // 超时时间，单位秒（s），默认3秒

    public String getCurl() {
        return curl;
    }

    public void setCurl(String curl) {
        this.curl = curl;
    }

    public String getBodyData() {
        return bodyData;
    }

    public void setBodyData(String bodyData) {
        this.bodyData = bodyData;
    }

    public String getFormData() {
        return formData;
    }

    public void setFormData(String formData) {
        this.formData = formData;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }
}
