package com.example.demo.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期转换工具类
 */
public class DateUtil {
    /**
     * 定义日常格式模板
     */
    private static final String TIME_NORMAL_PATTERN="yyyy-MM-dd HH:mm:ss";

    /**
     * 最常用的日期格式转换器
     */
    private static DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern(TIME_NORMAL_PATTERN);
    /**
     * 获取当前日期时间(String)
     * @return
     */
    public static String getCurrentTime(){
        LocalDateTime now = LocalDateTime.now();

        return dateTimeFormatter.format(now);
    }

    public static String getStringTime(Instant instant){
        LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();

        return dateTimeFormatter.format(localDateTime);
    }

    /**
     * 根据时间和格式获取String时间
     * @param localDateTime
     * @param pattern
     * @return
     */
    public static String getStringTime(LocalDateTime localDateTime,String pattern){
        if (pattern!=null && !TIME_NORMAL_PATTERN.equals(pattern)){
            return DateTimeFormatter.ofPattern(pattern).format(localDateTime);
        }
        return DateUtil.getStringTime(localDateTime);
    }

    /**
     * 根据时间获取String时间
     * @param localDateTime
     * @return
     */
    public static String getStringTime(LocalDateTime localDateTime){
        return dateTimeFormatter.format(localDateTime);
    }

}
