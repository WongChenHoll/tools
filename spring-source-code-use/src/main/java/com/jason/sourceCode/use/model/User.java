package com.jason.sourceCode.use.model;

import org.springframework.stereotype.Component;

/**
 * @author WangChenHol
 * @date 2021-12-31 17:40
 **/
@Component
public class User {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("给用户命名");
    }

    @Override
    public String toString() {
        return "user:哈哈哈哈";
    }
}
