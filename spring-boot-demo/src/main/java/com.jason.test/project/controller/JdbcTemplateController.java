package com.jason.test.project.controller;

import com.jason.test.project.service.JdbcTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * @author ChenHol.Wong
 * @create 2020/10/7 10:20
 */
@RestController
@RequestMapping("/jdbcTemplate")
public class JdbcTemplateController {

    @Autowired
    private JdbcTemplateService jdbcTemplateService;

    @PostMapping("/query")
    public Object query() {
        return jdbcTemplateService.query();
    }

    @PostMapping("/execute")
    public Object execute() {
        return jdbcTemplateService.execute();
    }

    @PostMapping("/queryForObject")
    public Object queryForObject() {
        return jdbcTemplateService.queryForObject();
    }

    @PostMapping("/queryForList")
    public Object queryForList() {
        return jdbcTemplateService.queryForList(null);
    }

    @PostMapping("/update")
    public void update() {
        jdbcTemplateService.update();
    }

    @PostMapping("/batchUpdate")
    public void batchUpdate() {
        try {
            jdbcTemplateService.batchUpdate();
        } catch (ParseException e) {
            System.out.println("异常：" + e);
        }
    }

    @PostMapping("/call")
    public void call() {
        jdbcTemplateService.call();
    }
}
