package com.jason.test.project.model;

import com.jason.test.common.annotation.ByteLength;

import javax.validation.constraints.NotEmpty;

/**
 * @author ChenHol.Wong
 * @create 2020/10/12 21:44
 */
public class ValidRequest {

    @NotEmpty
    @ByteLength(length = 5, message = "姓名字段超长")
    private String name;

    @ByteLength(length = 2, message = "账户字段超长")
    private String account;

    @ByteLength(length = 10, message = "密码超长")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ValidRequest{" +
                "name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
