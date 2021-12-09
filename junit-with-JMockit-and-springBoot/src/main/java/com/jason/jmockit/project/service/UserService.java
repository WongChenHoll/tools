package com.jason.jmockit.project.service;

import com.jason.jmockit.project.model.User;

import java.util.List;

/**
 * @author WangChenHol
 * @date 2021-12-8 16:18
 **/
public interface UserService {
    public void addUser(User user);

    public User getUser(String name);

    public List<User> getUsers() throws Exception;

    public int updateUser(User user);

    public boolean checkUser(User user);

    public String getUserName(User user);

    public int getUserAge(User user);
}
