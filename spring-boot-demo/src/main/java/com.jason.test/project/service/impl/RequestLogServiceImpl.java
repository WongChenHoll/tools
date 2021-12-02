package com.jason.test.project.service.impl;

import com.jason.test.project.dao.RequestLogDao;
import com.jason.test.project.service.RequestLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ChenHol.Wong
 * @create 2020/7/28 21:16
 */
@Service
public class RequestLogServiceImpl implements RequestLogService {

    @Autowired
    private RequestLogDao requestLogDao;

    @Override
    public int addLog(String interfaceName) {
        return requestLogDao.addRequestLog(interfaceName);
    }
}
