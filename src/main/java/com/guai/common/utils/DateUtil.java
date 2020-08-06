package com.guai.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author gqw
 * @date 2020-08-05
 */
public class DateUtil {

    /**
     * 获取当前时间戳
     * @return
     */
    public static long getNowDateTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 按格式取当前时间字符串
     *
     * @param formatString 格式字符串
     * @return
     */
    public static String getCurrentDateString(String formatString) {
        Date currentDate = new Date();
        return getDateString(currentDate, formatString);
    }

    /**
     * 取时间字符串
     *
     * @param date         时间
     * @param formatString 转换格式
     * @return 时间字符串
     */
    public static String getDateString(Date date, String formatString) {
        return getDateString(date, formatString, Locale.PRC);
    }

    /**
     * 取时间字符串
     *
     * @param date         时间
     * @param formatString 转换格式
     * @param locale       地区
     * @return 时间字符串
     */
    public static String getDateString(Date date, String formatString, Locale locale) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatString, locale);
        return dateFormat.format(date);
    }
}
