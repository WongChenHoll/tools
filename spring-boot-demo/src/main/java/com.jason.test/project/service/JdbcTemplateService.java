package com.jason.test.project.service;

import java.text.ParseException;
import java.util.List;

/**
 * @author ChenHol.Wong
 * @create 2020/9/27 9:57
 */
public interface JdbcTemplateService {

    Object query();

    Object queryForObject();

    Object queryForList(Object object);

    Object execute();

    void update();

    void batchUpdate() throws ParseException;

    void call();
}
