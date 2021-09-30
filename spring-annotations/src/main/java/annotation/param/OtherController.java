package annotation.param;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.Arrays;

/**
 * 其它注解
 *
 * @author WangChenHol
 * @date 2021-9-30 11:01
 **/
@RestController
@RequestMapping("/other")
public class OtherController {

    @GetMapping("/sessionId")
    public String sessionId(@CookieValue(value = "JSESSIONID", defaultValue = "") String sessionId) {
        return "会话的SeesionId：" + sessionId;
    }

    @GetMapping("/cookie")
    public String cookie(@CookieValue(value = "JSESSIONID") Cookie cookie) {
        return cookie.getComment() + "\r\n" + cookie.getDomain() + "\r\n" + cookie.getName() + "\r\n" + cookie.getPath() + "\r\n" + cookie.getValue();
    }

    @GetMapping("/header")
    public String header(@RequestHeader(name = "User-Agent") String userAgent,
                         @RequestHeader("Accept") String[] accept,
                         @RequestHeader("Host") String host) {
        return "User-Agent:" + userAgent + "\r\nHost:" + host + "\r\nAccept:" + Arrays.toString(accept);

    }
}
