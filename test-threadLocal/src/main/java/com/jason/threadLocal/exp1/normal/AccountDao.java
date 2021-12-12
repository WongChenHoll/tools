package com.jason.threadLocal.exp1.normal;

import com.jason.threadLocal.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author WangChenHol
 * @date 2021-12-12 14:34
 **/
public class AccountDao {

    /**
     * 转入
     */
    public boolean in(String name, double money) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("update account set money=money+? where name=?");
        preparedStatement.setDouble(1, money);
        preparedStatement.setString(2, name);
        preparedStatement.executeUpdate();
        JdbcUtil.close(conn, preparedStatement); // 关闭资源
        return true;
    }

    /**
     * 转出
     */
    public boolean out(String name, double money) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("update account set money=money-? where name=?");
        preparedStatement.setDouble(1, money);
        preparedStatement.setString(2, name);
        preparedStatement.executeUpdate();
        JdbcUtil.close(conn, preparedStatement);// 关闭资源
        return true;
    }

}
