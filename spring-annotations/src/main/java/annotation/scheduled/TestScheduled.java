package annotation.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 测试定时任务的。Scheduled
 * 第一位，表示秒，取值0-59
 * 第二位，表示分，取值0-59
 * 第三位，表示小时，取值0-23
 * 第四位，日期天/日，取值1-31
 * 第五位，日期月份，取值1-12
 * 第六位，星期，取值1-7，1表示星期天，2表示星期一
 * 第七位，年份，可以留空，取值1970-2099
 *
 * @author WangChenHol
 * @date 2021-9-30 16:02
 **/
@Component
public class TestScheduled {
    public static final Logger logger = LoggerFactory.getLogger(TestScheduled.class);

    @Scheduled(cron = "0 0/1 * * * ?")
    public void test1() {
        logger.info("每分钟执行一次，时间：{}", getDate());
    }

    @Scheduled(cron = "0 * * * * ?")
    public void test2() {
        logger.info("每分钟执行一次，时间：{}", getDate());
    }

    @Scheduled(cron = "0 0/5 * * * ?")
    public void test3() {
        logger.info("每5分钟执行一次，时间：{}", getDate());
    }

    @Scheduled(cron = "0 0 * * * ?")
    public void test4() {
        logger.info("每小时执行一次，时间：{}", getDate());
    }


    public String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

}
