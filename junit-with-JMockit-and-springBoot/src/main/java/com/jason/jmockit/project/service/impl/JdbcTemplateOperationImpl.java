package com.jason.jmockit.project.service.impl;

import com.jason.jmockit.project.service.JdbcTemplateOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author WangChenHol
 * @date 2021-12-8 21:45
 **/
@Service
public class JdbcTemplateOperationImpl implements JdbcTemplateOperation {

    private static final Logger logger = LoggerFactory.getLogger(JdbcTemplateOperationImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String getString() {
        String query = jdbcTemplate.queryForObject("select * from dual", String.class);

        logger.info("===== getString() [{}]=====", query);
        return query;
    }

    @Override
    public String getString(String p1, String p2) {
        String query = jdbcTemplate.queryForObject(p1, String.class);
        Integer integer = jdbcTemplate.queryForObject(p2, Integer.class);

        logger.info("===== getString() [{}，{}]=====", query, integer);
        return query;
    }

    @Override
    public String getString(Object[] param) {
        String query = jdbcTemplate.queryForObject("select * from dual", param, String.class);
        logger.info("===== getString() [{}]=====", query);
        return query;
    }
}
