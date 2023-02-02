package com.jason.test.project.controller;

import com.jason.base.exception.ServiceException;
import com.jason.base.utils.CurlUtil;
import com.jason.test.common.bean.BaseResponse;
import com.jason.test.project.model.CurlCommand;
import org.springframework.web.bind.annotation.*;

/**
 * @author WongChenHoll
 * @description 测试curl命令的执行
 * @date 2023-2-1 星期三 15:55
 **/
@RestController
@RequestMapping("curl")
public class CURLController {

    @PostMapping("/execute")
    public BaseResponse<Object> exe(@RequestBody CurlCommand curlCommand) throws ServiceException {
        String curl = CurlUtil.executeCURL(CurlUtil.parse(curlCommand.getCurl(), curlCommand.getBodyData(), curlCommand.getFormData(), curlCommand.getTimeout()));
        return BaseResponse.success(curl);
    }

    @GetMapping("/testGet")
    public BaseResponse<String> test(String userName){
        return BaseResponse.success(userName);
    }

}
