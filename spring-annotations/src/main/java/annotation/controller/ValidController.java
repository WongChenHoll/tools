package annotation.controller;

import annotation.bean.UserRequest;
import com.jason.base.exception.ServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 参数校验相关
 *
 * @author WangChenHol
 * @date 2021-9-23 13:59
 **/
@RequestMapping("/valid")
@RestController
public class ValidController {

    @PostMapping("add")
    public String add(@RequestBody @Valid UserRequest request) {
        System.out.println(request.toString());
        System.out.println(request.getUserName() == null ? "姓名为空" : "姓名合格");
        return "成功";
    }
}
