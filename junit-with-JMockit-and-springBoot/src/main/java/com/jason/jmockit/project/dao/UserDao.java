package com.jason.jmockit.project.dao;


import com.jason.jmockit.project.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WangChenHol
 * @date 2021-12-8 16:14
 **/
@Component
public class UserDao {

    public void addUser(User user) {
        // do nothing
    }

    public User getUser(String name) {
        return new User(name, 30);
    }

    public List<User> getUsers() {
        ArrayList<User> list = new ArrayList<>();
        list.add(new User("张三", 10));
        list.add(new User("李四", 23));
        return list;
    }

    public int updateUser(User user) {
        return 1;
    }
}
