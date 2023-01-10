package annotation.controller;

import annotation.bean.Product;
import annotation.service.JSONService;
import com.jason.base.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 测试JSON的功能
 *
 * @author WongChenHoll
 * @description 测试代码中JSON字符串和JSON注解的使用
 * @date 2023-1-9 星期一 16:59
 **/
@RequestMapping("/json")
@RestController()
public class JSONController {

    @Autowired
    private JSONService jsonService;

    @GetMapping("/list")
    public BaseResponse<List<Product>> list() {
        return BaseResponse.success(jsonService.list());
    }
}
