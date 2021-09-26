package annotation.controller;

import annotation.bean.MyDataSource;
import annotation.bean.User;
import annotation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangChenHol
 * @date 2021-9-17 10:30
 **/
@RequestMapping("/user")
@RestController
public class AutowiredController {

    @Autowired
    private UserService userService;
//   或者 private UserService userServiceImpl;

    @Autowired
    private MyDataSource oracleDataSource;

    @Autowired
    private MyDataSource mysqlDataSource;

    @Qualifier("singletonUser")
    @Autowired
    private User user;

    @GetMapping("/autowired")
    public String getDataSource() {
        userService.getUser();
        return oracleDataSource.toString() + "\r\n" + mysqlDataSource.toString();
    }

    @GetMapping("qualifier")
    public String getUser() {
        return user.toString();
    }
}
