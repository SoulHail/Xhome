package com.codebear.xhome.util.own;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 时间运算
 */
public class DataOperation {
    public static void main(String[] args) {
        // 获取当前时间两天后的日期 （还有间隔周、月、年的方法）
        LocalDate futureDay = LocalDate.now().plusDays(2);
        System.out.println(futureDay.toString());

        // 带时分秒格式
        LocalDateTime futureDayTime = LocalDateTime.now().plusDays(3);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dtf.format(futureDayTime));
    }
}
