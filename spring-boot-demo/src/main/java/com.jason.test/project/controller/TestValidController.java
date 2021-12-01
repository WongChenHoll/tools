package com.jason.test.project.controller;

import com.jason.test.project.model.ValidRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author ChenHol.Wong
 * @create 2020/10/12 21:42
 */
@RestController
@RequestMapping("/valid")
public class TestValidController {


    @PostMapping("/login")
    public Object test(@RequestBody @Valid ValidRequest request) {
        return request.toString();
    }

}
