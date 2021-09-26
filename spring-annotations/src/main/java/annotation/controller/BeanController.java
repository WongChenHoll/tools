package annotation.controller;

import annotation.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author WangChenHol
 * @date 2021-9-14 16:18
 **/
@RequestMapping("/bean")
@RestController
public class BeanController {

    public static final Logger logger = LoggerFactory.getLogger(BeanController.class);

    @Autowired
    private User singletonUser;

    @Autowired
    private User prototypeUser;

    @Autowired
    private User requestUser;

    @Autowired
    private User sessionUser;

    @GetMapping("/testScope1")
    public void test1() {
        logger.info("singleton BeanController testScope1：{}", singletonUser);
    }

    @GetMapping("/testScope2")
    public void test2() {
        logger.info("singleton BeanController testScope2：{}", singletonUser);
    }

    @GetMapping("/testScope3")
    public void test3() {
        logger.info("prototype BeanController testScope3：{}", prototypeUser);
    }

    @GetMapping("/testScope4")
    public void test4() {
        logger.info("prototype BeanController testScope4：{}", prototypeUser);
    }

    @GetMapping("/testScope5")
    public void test5() {
        logger.info("request BeanController testScope5：{}", requestUser);
    }

    @GetMapping("/testScope6")
    public void test6() {
        logger.info("request BeanController testScope6：{}", requestUser);
    }

    @GetMapping("/testScope7")
    public void test7(HttpServletRequest request) {
        logger.info("SessionId:{},     Bean对象：{}", request.getSession().getId(), sessionUser);
    }

    @GetMapping("/testScope8")
    public void test8(HttpServletRequest request) {
        logger.info("SessionId:{},     Bean对象：{}", request.getSession().getId(), sessionUser);
    }


}
