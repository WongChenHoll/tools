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
 * 通过使用ThreadLocal保存Connection
 *
 * @author WangChenHol
 * @date 2021-12-12 14:16
 **/
public class JdbcThreadLocalUtil {

    static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    static String driver;
    static String url;
    static String userName;
    static String password;

    static {
        Properties properties = new Properties();
        InputStream stream = JdbcThreadLocalUtil.class.getClassLoader().getResourceAsStream("application.properties");
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
        // 取出当前线程池中Connection
        Connection connection = threadLocal.get();
        if (connection == null) {
            // 如果当前线程池中没有Conneciton，则重新获取，并将其存放在ThreadLocal
            connection = getDataSource().getConnection();
            threadLocal.set(connection);
        }
        return connection;
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
    public static void commitAndClose() {
        try {
            // 直接从ThreadLocal获取Connection
            Connection connection = getConnection();
            if (connection != null) {
                if (!connection.isClosed()) {
                    connection.commit();

                    // 关闭连接之前将Connection从ThreadLocal中删除
                    threadLocal.remove();

                    connection.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 事务回滚并关闭资源
     */
    public static void rollbackAndClose() {
        try {
            // 直接从ThreadLocal获取Connection
            Connection connection = getConnection();
            if (connection != null) {
                if (!connection.isClosed()) {
                    connection.rollback();

                    // 关闭连接之前将Connection从ThreadLocal中删除
                    threadLocal.remove();

                    connection.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
