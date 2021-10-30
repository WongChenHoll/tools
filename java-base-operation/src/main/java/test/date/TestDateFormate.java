package test.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * @author ChenHol.Wong
 * @create 2020/5/1 17:58
 */
public class TestDateFormate {
    public static void main(String[] args) throws ParseException {
        String date="2020年5月01日 18:00:21";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

        System.out.println(simpleDateFormat.format(new Date()));
        System.out.println(simpleDateFormat.parse(date));

    }
}
