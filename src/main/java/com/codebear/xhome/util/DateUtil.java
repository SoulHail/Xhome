package com.codebear.xhome.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间处理工具类
 */
@Slf4j
public class DateUtil {

    /**
     * yyyy-MM-dd
     */
    private static final String SHORT_DATE_FORMAT_ONE = "yyyy-MM-dd";

    /**
     * yyyy/MM/dd
     */
    private static final String SHORT_DATE_FORMAT_TWO = "yyyy/MM/dd";

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    private static final String LONG_DATE_FORMAT_ONE = "yyyy-MM-dd HH:mm:ss";

    /**
     * yyyy/MM/dd HH/mm/ss
     */
    private static final String LONG_DATE_FORMAT_TWO = "yyyy/MM/dd HH/mm/ss";

    /**
     * 秒、毫秒互转
     */
    private static final long SECONDS_TO_MILL = 1000L;

    /**
     * 毫秒数
     */
    private static final long MILL_OF_DAY = 60 * 60 * 24L * 1000;

    /**
     * 秒数
     */
    private static final long SECONDS_OF_DAY = 60 * 60 * 24L;

    /**
     * 短时间戳长度
     */
    private static final int SHORT_TIME_STAMP_LENGTH = 10;

    /**
     * 长时间戳长度
     */
    private static final int LONG_TIME_STAMP_LENGTH = 13;

    private static final String SECOND_TO_MILL = "000";

    /**************************************************Date转String****************************************************/

    // 日期转字符串， yyyy-MM-dd HH:mm:ss
    public static String getFormatTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(LONG_DATE_FORMAT_ONE);
        return sdf.format(date);
    }

    // 日期转字符串， yyyy/MM/dd HH/mm/ss
    public static String getFormatTimer(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(LONG_DATE_FORMAT_TWO);
        return sdf.format(date);
    }

    // 日期转短时间字符串 yyyy-MM-dd
    public static String getShortTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(SHORT_DATE_FORMAT_ONE);
        return sdf.format(date);
    }

    // 日期转短时间字符串 yyyy/MM/dd
    public static String getShortTimer(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(SHORT_DATE_FORMAT_TWO);
        return sdf.format(date);
    }

    /**************************************************String转Date****************************************************/

    // String转Date,时间类型可变SimpleDateFormat
    public static Date getDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat(SHORT_DATE_FORMAT_ONE);
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            log.error("====时间转换异常====", e);
            return null;
        }
        return date;
    }

    /**************************************************获取时间戳********************************************************/

    // 获取当前时间戳，10位,精确到秒 ShortTimeStamp
    public static String getSts() {
        return String.valueOf(System.currentTimeMillis() / SECONDS_TO_MILL);
    }

    // 获取当前时间戳，13位,精确到毫秒 LongTimeStamp
    public static String getLts() {
        return String.valueOf(System.currentTimeMillis());
    }

    // 获取x天后的时间戳,13位
    public static String getFutureLs(int day) {
        return String.valueOf(System.currentTimeMillis() + MILL_OF_DAY * day);
    }

    // 获取x天后的时间戳,10位
    public static String getFutureSs(int day) {
        return String.valueOf(System.currentTimeMillis() / SECONDS_TO_MILL + SECONDS_OF_DAY * day);
    }

    // 获取几周前后的时间戳
    public static String getWeekStamp() {
        Calendar curr = Calendar.getInstance();
        curr.set(Calendar.DAY_OF_MONTH, curr.get(Calendar.DAY_OF_MONTH) + 7);
        Date date = curr.getTime();
        String timeStamp = String.valueOf(date.getTime());
        System.out.println(timeStamp);
        return timeStamp;
    }

    // 获取几个月前后的时间戳
    public static String getMonthStamp() {
        Calendar curr = Calendar.getInstance();
        curr.set(Calendar.MONTH, curr.get(Calendar.MONTH) - 6);
        Date date = curr.getTime();
        String timeStamp = String.valueOf(date.getTime());
        System.out.println(timeStamp);
        return timeStamp;
    }

    // 获取几个月前后的时间戳
    public static String getYearStamp() {
        Calendar curr = Calendar.getInstance();
        curr.set(Calendar.YEAR, curr.get(Calendar.YEAR) + 1);
        Date date = curr.getTime();
        String timeStamp = String.valueOf(date.getTime());
        System.out.println(timeStamp);
        return timeStamp;
    }

    /************************************************时间戳转Date********************************************************/

    // 10位时间戳转日期
    public static Date getDateBySts(String stamp) {
        if (StringUtils.isBlank(stamp) || stamp.length() != SHORT_TIME_STAMP_LENGTH) {
            return null;
        }
        return new Date(Long.valueOf(stamp + SECOND_TO_MILL));
    }

    // 13位时间戳转日期
    public static Date getDateByLts(String stamp) {
        if (StringUtils.isBlank(stamp) || stamp.length() != LONG_TIME_STAMP_LENGTH) {
            return null;
        }
        return new Date(Long.valueOf(stamp));
    }

    /*********************************************时间戳转String********************************************************/

    // 10位时间戳转日期字符串，输出 yyyy-MM-dd HH:mm:ss
    public static String getFormatBySts(String stamp) {
        if (StringUtils.isBlank(stamp) || stamp.length() != SHORT_TIME_STAMP_LENGTH) {
            return null;
        }
        Date date = new Date(Long.valueOf(stamp + SECOND_TO_MILL));
        return new SimpleDateFormat(LONG_DATE_FORMAT_ONE).format(date);
    }

    // 13位时间戳转日期，输出 yyyy-MM-dd HH:mm:ss
    public static String getFormatByLts(String stamp) {
        if (StringUtils.isBlank(stamp) || stamp.length() != LONG_TIME_STAMP_LENGTH) {
            return null;
        }
        Date date = new Date(Long.valueOf(stamp));
        return new SimpleDateFormat(LONG_DATE_FORMAT_ONE).format(date);
    }

    /*********************************************String转时间戳********************************************************/
    // String转时间戳，注意SimpleDateFormat格式
    public static String strToStamp(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat(LONG_DATE_FORMAT_ONE);
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            log.error("====时间转换异常====", e);
            return null;
        }
        return String.valueOf(date.getTime());
    }

    /*********************************************Date转时间戳**********************************************************/

    // Date转时间戳，注意SimpleDateFormat格式
    public static String dateToStamp(Date date) {
        return String.valueOf(date.getTime());
        //return String.valueOf(date.getTime() / SECONDS_TO_MILL);
    }

    /*public static void main(String[] args) {
        String time = "2019-12-10";
        DateUtil dateUtil = new DateUtil();
        System.out.println(dateUtil.strToStamp(time));
    }*/

}
