package com.jason.threadLocal.exp1.transaction;

import com.jason.threadLocal.utils.JdbcUtil;

import java.sql.Connection;

/**
 * 使用数据库的事务功能进行控制，通过加锁控制Connection在同一个线程中是同一个，但是加锁会使在并发情况下性能降低
 *
 * @author WangChenHol
 * @date 2021-12-12 16:22
 **/
public class AccountService {

    AccountDao dao = new AccountDao();

    /**
     * 转账功能，即使代码会抛出异常，但因为数据库的事务控制，所以会符合业务
     *
     * @param from  转出
     * @param to    转入
     * @param money 金额
     */
    public boolean transfer(String from, String to, double money) {
        Connection conn = null;
        try {
            synchronized (AccountService.class) { // 为了防止在并发情况下，别的线程强用另一个线程的Connection，所以加上锁。
                conn = JdbcUtil.getConnection();

                // 开启事务
                conn.setAutoCommit(false);

                dao.out(conn, from, money);
                int i = 1 / 0;
                dao.in(conn, to, money);

                // 提交并关闭连接
                JdbcUtil.commitAndClose(conn);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            // 事务回滚并关闭连接
            JdbcUtil.rollbackAndClose(conn);
        }
        return true;
    }
}
