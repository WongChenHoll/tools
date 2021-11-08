package annotation.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 测试定时任务的。Scheduled
 * 第一位，表示秒，取值0-59
 * 第二位，表示分，取值0-59
 * 第三位，表示小时，取值0-23
 * 第四位，月份中日期，取值1-31
 * 第五位，日期月份，取值1-12
 * 第六位，星期，取值1-7，1表示星期天，2表示星期一
 * 第七位，年份，可以留空，取值1970-2099
 * </p>
 * corn从左到右（用空格隔开）：秒 分 小时 月份中的日期 月份 星期中的日期 年份
 *
 * <p>
 * 每一个域都使用数字，但还可以出现如下特殊字符，它们的含义是：
 * 　　（1）*：表示匹配该域的任意值。假如在Minutes域使用*, 即表示每分钟都会触发事件。
 * 　　（2）?：只能用在DayofMonth和DayofWeek两个域。它也匹配域的任意值，但实际不会。
 * 因为DayofMonth和DayofWeek会相互影响。例如想在每月的20日触发调度，不管20日到底是星期几，
 * 则只能使用如下写法： 13 13 15 20 * ?, 其中最后一位只能用？，而不能使用*，如果使用*表示不管星期几都会触发，实际上并不是这样。
 * 　　（3）-：表示范围。例如在Minutes域使用5-20，表示从5分到20分钟每分钟触发一次
 * 　　（4）/：表示起始时间开始触发，然后每隔固定时间触发一次。例如在Minutes域使用5/20,则意味着5分钟触发一次，而25，45等分别触发一次.
 * 　　（5）,：表示列出枚举值。例如：在Minutes域使用5,20，则意味着在5和20分每分钟触发一次。
 * 　　（6）L：表示最后，只能出现在DayofWeek和DayofMonth域。如果在DayofWeek域使用5L,意味着在最后的一个星期四触发。
 * 　　（7）W:表示有效工作日(周一到周五),只能出现在DayofMonth域，系统将在离指定日期的最近的有效工作日触发事件。
 * 例如：在 DayofMonth使用5W，如果5日是星期六，则将在最近的工作日：星期五，即4日触发。
 * 如果5日是星期天，则在6日(周一)触发；如果5日在星期一到星期五中的一天，则就在5日触发。另外一点，W的最近寻找不会跨过月份 。
 * 　　（8）LW:这两个字符可以连用，表示在某个月最后一个工作日，即最后一个星期五。
 * 　　（9）#:用于确定每个月第几个星期几，只能出现在DayofMonth域。例如在4#2，表示某月的第二个星期三。
 * </p>
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
