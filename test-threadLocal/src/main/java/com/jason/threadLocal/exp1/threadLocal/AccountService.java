package com.jason.threadLocal.exp1.threadLocal;

import com.jason.threadLocal.utils.JdbcThreadLocalUtil;

import java.sql.Connection;

/**
 * 使用数据库的事务功能进行控制.
 * 并使用ThreadLocal线程池，使在统一线程中使用的Connection是同一个
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
        try {
            // 开启事务
            Connection connection = JdbcThreadLocalUtil.getConnection();
            connection.setAutoCommit(false);

            // 业务代码
            dao.out(from, money);
//            int i = 1 / 0;
            dao.in(to, money);

            // 关闭连接
            JdbcThreadLocalUtil.commitAndClose();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            // 事务回滚
            JdbcThreadLocalUtil.rollbackAndClose();
        }
        return true;
    }
}
