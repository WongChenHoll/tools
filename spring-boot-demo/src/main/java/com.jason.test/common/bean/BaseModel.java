package com.jason.test.common.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体类
 *
 * @author ChenHol.Wong
 * @create 2020/7/27 20:26
 */
public class BaseModel extends PageResponse implements Serializable {
    private String id;
    private Date createTime;
    private String createUser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "id='" + id + '\'' +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                '}';
    }
}
