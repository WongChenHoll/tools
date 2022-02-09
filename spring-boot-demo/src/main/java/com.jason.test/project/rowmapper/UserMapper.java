package com.jason.test.project.rowmapper;

import com.jason.test.project.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author WangChenHol
 * @date 2022-1-10 17:09
 **/
public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setAge(rs.getInt("age"));
        user.setUserName(rs.getString("userName"));
        user.setAddress(rs.getString("address"));
        return user;
    }
}
