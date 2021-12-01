package com.jason.test.project.service;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author ChenHol.Wong
 * @create 2020/7/28 21:15
 */
public interface RequestLogService {

    int addLog(String interfaceName);
}
