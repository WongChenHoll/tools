package com.jason.test.common.constans;

import com.jason.test.project.model.User;

/**
 * @author ChenHol.Wong
 * @create 2020/7/30 21:02
 */
public class UserInfo {

    private static ThreadLocal<User> userNameThreadLocal = new ThreadLocal<>();

    public static void setUserNameThreadLocal(User user) {
        User name = userNameThreadLocal.get();
        if (name == null) {
            userNameThreadLocal.set(user);
        }
    }

    public static User user() {
        return userNameThreadLocal.get();
    }

    public static void clear() {
        userNameThreadLocal.remove();
    }
}
