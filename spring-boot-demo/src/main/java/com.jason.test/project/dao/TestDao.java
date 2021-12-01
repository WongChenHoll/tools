package com.jason.test.project.dao;

import com.jason.test.project.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ChenHol.Wong
 * @create 2020/7/26 22:14
 */
@Mapper
public interface TestDao {

    int addUser(User user);

    User queryUserById(String id);
}
