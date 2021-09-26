package annotation.controller;

import annotation.bean.DruidDataSource;
import annotation.bean.MyDataSource;
import annotation.bean.User;
import annotation.config.CustConfig;
import annotation.config.DataSourceConfigurationProperties;
import annotation.config.TestConfiguration;
import annotation.config.TestPropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author WangChenHol
 * @date 2021-9-14 16:52
 **/
@RequestMapping("/configController")
@RestController
public class ConfigController {
    @Resource
    private User singletonUser;

    @Autowired
    private CustConfig custConfig;

    @Autowired
    private TestConfiguration testConfiguration;
    @Autowired
    private User testConfigurationUser;
    @Autowired
    private MyDataSource otherDataSource;

    @Autowired
    private DataSourceConfigurationProperties dataSourceConfigurationProperties;
    @Autowired
    private DruidDataSource readDruidDataSource;
    @Autowired
    private DruidDataSource writeDruidDataSource;

    @Autowired
    private TestPropertySource testPropertySource;

    @GetMapping("/test")
    public String test() {

        return singletonUser.getName() + singletonUser.getAge();
    }

    @GetMapping("/test2")
    public String test2() {
        return "哈哈哈哈哈";
    }

    @GetMapping("cust")
    public String cust() {
        return custConfig.toString();
    }

    @GetMapping("configuration")
    public String Configuration() {
        return "通过类调用User类型Bean：\r\t" + testConfiguration.testConfigurationUser().toString() +
                "\r\n通过类调用DataSource类型Bean：\r\t" + testConfiguration.otherDataSource().toString() +
                "\r\n直接获取User类型Bean：\r\t" + testConfigurationUser.toString() +
                "\r\n直接获取DataSource类型Bean：\r\t" + otherDataSource.toString();
    }

    @GetMapping("byInstance")
    public String method1() {
        return testConfiguration.testConfigurationUser().toString();
    }

    @GetMapping("useBean1")
    public String method2() {
        return testConfigurationUser.toString();
    }

    @GetMapping("useBean2")
    public String method3() {
        return otherDataSource.toString();
    }

    @GetMapping("ConfigurationPropertiesByClass")
    public String ConfigurationPropertiesByClass() {
        return dataSourceConfigurationProperties.toString();
    }

    @GetMapping("ConfigurationPropertiesByMethod")
    public String ConfigurationPropertiesByMethod() {
        return readDruidDataSource.toString() + "\r\n\r\n" + writeDruidDataSource.toString();
    }

    @GetMapping("testPropertySource")
    public String testPropertySource() {
        return testPropertySource.toString();
    }
}
