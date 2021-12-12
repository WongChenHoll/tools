package com.jason.threadLocal.exp1.normal;

import com.jason.threadLocal.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 普通转账
 *
 * @author WangChenHol
 * @date 2021-12-12 16:22
 **/
public class AccountService {

    AccountDao dao = new AccountDao();

    /**
     * 转账功能，由于代码会抛出异常，所以转出会成功，转入会失败。所以不符合业务
     *
     * @param from  转出
     * @param to    转入
     * @param money 金额
     */
    public boolean transfer(String from, String to, double money) {
        try {
            dao.out(from, money);
            int i = 1 / 0;
            dao.in(to, money);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
