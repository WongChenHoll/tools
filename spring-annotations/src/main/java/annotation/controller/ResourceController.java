package annotation.controller;

import annotation.bean.MyDataSource;
import annotation.bean.TestDataSource;
import annotation.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author WangChenHol
 * @date 2021-9-17 16:03
 **/
@RequestMapping("/resource")
@RestController
public class ResourceController {

    @Resource
    private UserService hahahahahha; // 根据名字差找不到名为hahahahahha的bean，会自动根据类型UserService去查找bean

    @Resource(name = "mysqlDataSource")
    private MyDataSource myDataSource;

    @Resource(name = "oracleDataSource")
    private MyDataSource lalalalal;

//    注入失败
//    @Resource(name = "RedisDataSource")
//    private DataSource notExistName;

    @Resource(type = TestDataSource.class)
    private Object mysqlDataSource;

    @Resource(type = MyDataSource.class)
    private TestDataSource oracleDataSource;

    // 容器中不存在名为dotExistUser的bean，报异常。
//    @Resource(type = DataSource.class)
//    private DataSource notExistDataSource;

    @GetMapping("default")
    public String userService() {
        return hahahahahha.getUser();
    }

    @GetMapping("/byName")
    public String getMysqlDataSource() {
        return myDataSource.toString() + "\r\n" + lalalalal.toString();
    }

    @GetMapping("/byType")
    public String user() {
        return "ORACLE:" + oracleDataSource.toString() + "\r\nMYSQL:" + mysqlDataSource.toString();
    }
}
