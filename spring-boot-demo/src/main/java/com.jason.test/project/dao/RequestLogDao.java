package com.jason.test.project.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author ChenHol.Wong
 * @create 2020/7/28 21:23
 */
@Mapper
public interface RequestLogDao {

    int addRequestLog(String requestInterface);
}
