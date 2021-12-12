package com.jason.threadLocal.exp1.threadLocal;

import com.jason.threadLocal.utils.JdbcThreadLocalUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 资源管理统一放在Service层
 *
 * @author WangChenHol
 * @date 2021-12-12 14:34
 **/
public class AccountDao {

    /**
     * 转入
     */
    public boolean in(String name, double money) throws SQLException {
        Connection connection = JdbcThreadLocalUtil.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("update account set money=money+? where name=?");
        preparedStatement.setDouble(1, money);
        preparedStatement.setString(2, name);
        preparedStatement.executeUpdate();

        return true;
    }

    /**
     * 转出
     */
    public boolean out(String name, double money) throws SQLException {
        Connection connection = JdbcThreadLocalUtil.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("update account set money=money-? where name=?");
        preparedStatement.setDouble(1, money);
        preparedStatement.setString(2, name);
        preparedStatement.executeUpdate();

        return true;
    }

}
