package com.jason.test.project.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ChenHol.Wong
 * @create 2020/11/8 15:17
 */
@RestController
@RequestMapping("/multiple-environment-configure")
public class MultipleEnvironmentConfigureController {

    @Value("${multiple.environment.configure}")
    private String value;

    @PostMapping("/getValue")
    public String test(){
        return value;
    }
}

