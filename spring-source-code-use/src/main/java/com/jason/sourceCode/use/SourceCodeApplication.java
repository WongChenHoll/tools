package com.jason.sourceCode.use;

import com.jason.sourceCode.use.config.TestBeanFactoryAware;
import com.jason.sourceCode.use.project.controller.UserControlle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author WangChenHol
 * @date 2021-12-31 17:32
 **/
@SpringBootApplication
public class SourceCodeApplication {
    public static void main(String[] args) {
        SpringApplication.run(SourceCodeApplication.class, args);
//        Object test = TestBeanFactoryAware.getBean("user");
//        System.out.println(test.toString());

        UserControlle controller = (UserControlle) TestBeanFactoryAware.getBean("userControlle");
        controller.add();
        controller.update();
        controller.test("aaa");
    }
}
