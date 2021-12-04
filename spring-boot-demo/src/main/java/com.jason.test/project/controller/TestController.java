package com.jason.test.project.controller;

import com.jason.test.common.bean.BaseRequest;
import com.jason.test.common.bean.BaseResponse;
import com.jason.test.common.bean.PageQuery;
import com.jason.test.common.constans.BaseConstans;
import com.jason.test.common.constans.UserInfo;
import com.jason.test.project.model.User;
import com.jason.test.project.service.HotFilmService;
import com.jason.test.project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author ChenHol.Wong
 * @create 2020/7/26 22:16
 */
@RequestMapping("/test-jason")
@RestController
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private HotFilmService hotFilmService;

    @PostMapping("/test")
    public BaseResponse test(@RequestBody BaseRequest<User> request) {
        return BaseResponse.success(BaseConstans.OPER_SUCCESS_MES, userService.addUser(request.getData()));
    }

    @PostMapping("/userName")
    public BaseResponse test() {
        return BaseResponse.success(BaseConstans.OPER_SUCCESS_MES, UserInfo.user());
    }

    @PostMapping("/login")
    public BaseResponse login(@RequestBody BaseRequest<String> request, HttpServletRequest servletRequest) {
        User login = userService.login(request.getData());
        HttpSession session = servletRequest.getSession();
        session.setAttribute(BaseConstans.LOGIN_SESSION_ATTRIBUTE, login);
        return BaseResponse.success(login);
    }

    @PostMapping("/add-hot-film")
    public BaseResponse addHotFilm() {
        try {
            long s = System.currentTimeMillis();
            BaseResponse<Integer> success = BaseResponse.success(hotFilmService.addHotFilm());
            long e = System.currentTimeMillis();
            logger.info("耗时：{}", e - s);
            return success;

        } catch (Exception e) {
            logger.error("批量新增热门电影异常：", e);
            return BaseResponse.error(BaseConstans.OPER_ERROR_MES);
        }
    }

    @PostMapping("/add-hot-film-thread")
    public BaseResponse<?> addHotFilmThread() {
        try {
            long s = System.currentTimeMillis();
            BaseResponse<Integer> success = BaseResponse.success(hotFilmService.addHotFilmThread());
            long e = System.currentTimeMillis();
            logger.info("耗时：{}", e - s);
            return success;

        } catch (Exception e) {
            logger.error("批量新增热门电影异常：", e);
            return BaseResponse.error(BaseConstans.OPER_ERROR_MES);
        }
    }

    @PostMapping("/add-hot-film-executor-thread")
    public BaseResponse<?> addHotFilmExecutorThread() {
        try {
            long s = System.currentTimeMillis();
            BaseResponse<Integer> success = BaseResponse.success(hotFilmService.addHotFilmExecutorThread());
            long e = System.currentTimeMillis();
            logger.info("耗时：{}", e - s);
            return success;

        } catch (Exception e) {
            logger.error("批量新增热门电影异常：", e);
            return BaseResponse.error(BaseConstans.OPER_ERROR_MES);
        }
    }

    @PostMapping("/list")
    public BaseResponse<?> queryList(@RequestBody BaseRequest<PageQuery> request) {
        return BaseResponse.error(hotFilmService.queryList(request.getData()));
    }
}
