package com.jason.jmockit.project.service;

/**
 * @author WangChenHol
 * @date 2021-12-8 22:12
 **/
public interface JdbcTemplateOperation {
    public String getString();

    public String getString(String p1, String p2);

    public String getString(Object[] param);
}
