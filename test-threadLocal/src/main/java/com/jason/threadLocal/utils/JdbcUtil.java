package com.jason.threadLocal.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author WangChenHol
 * @date 2021-12-12 14:16
 **/
public class JdbcUtil {
    private JdbcUtil(){}

    static String driver;
    static String url;
    static String userName;
    static String password;

    static {
        Properties properties = new Properties();
        InputStream stream = JdbcUtil.class.getClassLoader().getResourceAsStream("application.properties");
        try {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver = properties.getProperty("spring.datasource.driver-class-name");
        url = properties.getProperty("spring.datasource.url");
        userName = properties.getProperty("spring.datasource.username");
        password = properties.getProperty("spring.datasource.password");
    }

    public static DataSource getDataSource() {
        DruidDataSource build = DruidDataSourceBuilder.create().build();
        build.setDriverClassName(driver);
        build.setUrl(url);
        build.setUsername(userName);
        build.setPassword(password);
        return build;
    }

    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    /**
     * 关闭资源
     */
    public static void close(AutoCloseable... autoCloseables) {
        for (AutoCloseable autoCloseable : autoCloseables) {
            if (autoCloseable != null) {
                try {
                    autoCloseable.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 提交并关闭连接
     */
    public static void commitAndClose(Connection connection) {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.commit();
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 事务回滚并关闭资源
     */
    public static void rollbackAndClose(Connection connection) {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.rollback();
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
