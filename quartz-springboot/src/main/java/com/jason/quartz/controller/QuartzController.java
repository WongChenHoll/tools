package com.jason.quartz.controller;

import com.jason.base.response.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangChenHol
 * @date 2021-11-18 10:34
 **/
@RestController
@RequestMapping("/operation")
public class QuartzController {

    @GetMapping("/test")
    public BaseResponse<String> test() {
        return BaseResponse.success();
    }
}
