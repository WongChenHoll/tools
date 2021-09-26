package annotation;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.Inet4Address;


/**
 * @author WangChenHol
 * @date 2021-9-14 16:07
 **/
@SpringBootApplication
public class SpringAnnotationApplication {
    public static final Logger logger = LoggerFactory.getLogger(SpringAnnotationApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringAnnotationApplication.class, args);
        logger.info("========== 项目启动成功 ==========");
        String ip = Inet4Address.getLoopbackAddress().getHostAddress();
        logger.info("当前节点：{}", ip);
    }
}
