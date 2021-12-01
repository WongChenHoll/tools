package com.jason.test.project.model;

import com.jason.test.common.bean.BaseModel;

/**
 * @author ChenHol.Wong
 * @create 2020/7/27 20:26
 */
public class User extends BaseModel {
    private String userName;
    private int age;
    private String address;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
