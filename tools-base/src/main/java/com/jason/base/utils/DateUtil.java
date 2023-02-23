package com.jason.base.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author WangChenHol
 * @date 2021-11-16 16:20
 **/
public class DateUtil {

    public static final String DEFAULT_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DEFAULT_FULL_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前时间的字符串类型值
     */
    public static String currDateStr() {
        return new SimpleDateFormat(DEFAULT_FULL_FORMAT).format(new Date());
    }

    public static String toDateStr(Date date) {
        return new SimpleDateFormat(DEFAULT_FULL_FORMAT).format(date);
    }

    public static LocalDate toLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(DEFAULT_FORMAT_YYYY_MM_DD));
    }

    /**
     * 取两个日期之间的所有日期
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     *                  @param includeEndDate 是否包含结束那一天
     * @return 日期LocalDate集合
     */
    public static List<LocalDate> betweenLocalDate(String startDate, String endDate, boolean includeEndDate) {
        LocalDate sd = toLocalDate(startDate);
        LocalDate ed = toLocalDate(endDate);
        long numOfDaysBetween = ChronoUnit.DAYS.between(sd, ed);
        if (includeEndDate) {
            numOfDaysBetween += 1;
        }
        return IntStream.iterate(0, i -> i + 1).limit(numOfDaysBetween).mapToObj(sd::plusDays).collect(Collectors.toList());
    }

    /**
     * @param startDate      开始日期
     * @param endDate        结束日期
     * @param includeEndDate 是否包含结束那一天
     * @return 日期String集合
     */
    public static List<String> betweenString(String startDate, String endDate, boolean includeEndDate) {
        List<LocalDate> localDates = betweenLocalDate(startDate, endDate, includeEndDate);
        return localDates.stream().map(localDate -> DateTimeFormatter.ofPattern(DEFAULT_FORMAT_YYYY_MM_DD).format(localDate)).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(betweenString("2022-12-20", "2023-01-10", true));
    }
}
