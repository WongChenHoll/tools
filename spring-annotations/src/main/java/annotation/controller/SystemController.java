package annotation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author WangChenHol
 * @date 2021-9-16 15:46
 **/
@RequestMapping("/system")
@RestController
public class SystemController {

    public static final Logger logger = LoggerFactory.getLogger(SystemController.class);

    // 获取Session  ID
    @GetMapping("/session")
    public String getSession(HttpServletRequest request) {
        String id = request.getSession().getId();
        logger.info("当前会话session ID:{}", id);
        return id;
    }

    @GetMapping("/getSession")
    public void getSession(HttpServletRequest request,String sessionId) {
        logger.info("Session ID:{}", sessionId);
    }
}
