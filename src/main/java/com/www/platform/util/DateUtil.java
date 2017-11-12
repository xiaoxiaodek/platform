package com.www.platform.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author qianjc
 * @version 0.0.1
 * @desc
 * @date 7/5/16
 */
public class DateUtil {

    public static final String YYYYMMDD = "yyyy-MM-dd";
    public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDDHHMMSSSSS = "yyyy-MM-dd HH:mm:ss SSS";
    public static final int DAY_TIME = 24 * 60 * 60 * 1000;

    /**
     * 得到时间的long形式
     *
     * @return long
     */
    public long getLongTime() {
        return new Date().getTime();
    }

    /**
     * 得到当前日期的字符串表现形式
     *
     * @return String
     *
     */
    public static String getDateStr() {
        return parse(new Date(), YYYYMMDD);
    }

    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 得到当前日期和时间的字符串表示形式
     *
     * @return String
     */
    public static String getDateTimeStr() {
        return parse(new Date(), YYYYMMDDHHMMSS);
    }

    /**
     *
     * @param format
     *            format
     * @return String
     */
    public static String format(String format) {
        try {
            return parse(new Date(), format);
        } catch (RuntimeException ex) {
            return "";
        }
    }

    /**
     * @param date
     *            date
     * @param format
     *            format
     * @return String
     */
    public static String format(Date date, String format) {
        try {
            return parse(date, format);
        } catch (RuntimeException ex) {
            return "";
        }
    }

    /**
     * 根据传入的Date返回字符串日期，格式为yyyy-MM-dd
     *
     * @param date
     *            date
     * @return String
     */
    public static String parse(Date date) {
        try {
            return parse(date, YYYYMMDD);
        } catch (RuntimeException ex) {
            return "";
        }
    }

    /**
     * 格式化当前日期，返回字符串
     *
     * @param format
     *            format
     * @return String
     */
    public static String toString(String format) {
        try {
            return parse(new Date(), format);
        } catch (RuntimeException ex) {
            return "";
        }
    }

    /**
     * 根据传入的Date和转换格式对日期进行格式化，并返回字符串表式形式
     *
     * @param date
     *            date
     * @param format
     *            format
     * @return String
     */
    public static String parse(Date date, String format) {
        if (date == null) {
            return "";
        }

        if (format == null || format.equals("")) {
            format = YYYYMMDD;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date).toString();
        } catch (RuntimeException ex) {

            return "";
        }
    }

    /**
     * 根据传入的时间和分钟数，得到相加后的时间字符串表示形式，格式为：yyyy-MM-dd HH:mm:ss
     *
     * @param dateStr
     *            dateStr
     * @param minutes
     *            minutes
     * @return String
     */
    public static String addMinute(String dateStr, int minutes) {
        if (dateStr == null) {
            return "";
        }
        return parse(addMinute(parse(dateStr), minutes));
    }

    /**
     * 根据传入的时间和月数，得到相加后的时间字符串表示形式，格式为：yyyy-MM-dd HH:mm:ss
     *
     * @param dateStr
     *            dateStr
     * @param monthes
     *            monthes
     * @return String
     */
    public static String addMonth(String dateStr, int monthes) {
        if (dateStr == null) {
            return "";
        }
        return parse(addMonth(parse(dateStr), monthes));
    }

    /**
     * 计算两个日期之间相隔的天数，date - anotherDate
     *
     * @param dateFront
     *            dateFront
     * @param dateBack
     *            dateBack
     * @return 两个日期之间相隔的天数
     */
    public static int getIntervalDay(Date dateFront, Date dateBack) {
        if (dateFront == null || dateBack == null) {
            return 0;
        }
        long time = dateBack.getTime() - dateFront.getTime();
        return (int) (time / DAY_TIME);
    }

    /**
     * 根据传入的时间返回该天是当月的第几周
     *
     * @param dateStr
     *            dateStr
     * @return int
     */
    public static int getDayMonthWeek(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
        return weekOfMonth;
    }

    /**
     * 根据传入的时间返回该天所在的月份
     *
     * @param dateStr
     *            dateStr
     * @return int
     */
    public static int getMonth(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        return month + 1;
    }

    /**
     *
     * @param dateStr
     *            dateStr
     * @return int
     */
    public static Date parse(String dateStr) {
        try {
            return parse(dateStr, YYYYMMDDHHMMSSSSS.substring(0, dateStr.length()));
        } catch (RuntimeException ex) {
            return null;
        }
    }

    /**
     *
     * @param dateStr
     *            dateStr
     * @param format
     *            format
     * @return int
     */
    public static Date parse(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据传入的时间和分钟数，得到相加后的时间字符串表示形式，格式为：yyyy-MM-dd HH:mm:ss
     *
     * @param date
     *            date
     * @param minutes
     *            minutes
     * @return Date
     */
    public static Date addMinute(Date date, int minutes) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    /**
     * 根据传入的时间和秒数，得到相加后的时间字符串表示形式，格式为：yyyy-MM-dd HH:mm:ss
     *
     * @param date
     *            date
     * @param seconds
     *            seconds
     * @return Date
     */
    public static Date addSecond(Date date, int seconds) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, seconds);

        return calendar.getTime();
    }

    /**
     * 根据传入的时间和秒数，得到相加后的时间表示形式，格式为：yyyy-MM-dd HH:mm:ss
     *
     * @param dateStr
     *            dateStr
     * @param seconds
     *            seconds
     * @return Date
     */
    public static String addSecond(String dateStr, int seconds) {
        if (dateStr == null) {
            return "";
        }
        return parse(addSecond(parse(dateStr), seconds));
    }

    /**
     * 增加月份
     *
     * @param date
     *            date
     * @param monthInt
     *            monthInt
     * @return 返回增加月份后的日期
     */
    public static Date addMonth(Date date, int monthInt) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, monthInt);
            return calendar.getTime();
        } catch (RuntimeException ex) {
            return null;
        }
    }

    /**
     * 获取当前日期day天后的日期
     *
     * @param day
     *            day
     * @return Date
     */
    public static Date addDay(int day) {
        return addDay(new Date(), day);
    }

    /**
     * 获取当前日期day天后的日期
     *
     * @param day
     *            day
     * @return Date
     */
    public static Date strAddDayToDate(String date, int day) {
        return parse(addDay(date, day));
    }

    /**
     * 日期加减
     *
     * @param date
     *            date
     * @param day
     *            day
     * @return 返回加day天后的日期
     */
    public static Date addDay(Date date, int day) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 字符串日期加减
     *
     * @param dateStr dateStr
     * @param day day
     * @return 返回加day天后的字符串日期
     */
    public static String addDay(String dateStr, int day) {
        return parse(addDay(parse(dateStr), day));
    }

    /**
     * 得到当天是周几，返回
     *
     * @return int
     */
    public static int getDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek < 0) {
            dayOfWeek = 0;
        }

        return dayOfWeek;
    }

    /**
     * 得到当天星期的中文表示形式
     *
     * @return String
     */
    public static String getDayOfWeekZh() {
        String[] dayNames = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        return dayNames[getDayOfWeek()];
    }

    /**
     * 传入周日到周六的星期数字，返回本周当天的日期，例如：weekInt=0，得到星期天的日期
     * 0:星期日；1：星期一；2：星期二；3：星期三；4：星期四；5：星期五；6：星期六 返回yyyy-MM-dd格式字符串
     * @param weekInt weekInt
     * @return String
     */
    public static String getWeekOfDate(int weekInt) {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        Date date = addDay(weekInt - dayOfWeek);
        return parse(date, YYYYMMDD);
    }

    /**
     * 传入日期的str，返回date的getTime值
     *
     * @param begin begin
     * @return long
     */
    public static long getTime(String begin) {
        return DateUtil.parse(begin).getTime();
    }

    /**
     * 根据传入的时间字符串，格式化成YYYYMMDDHHMMSS并转成字符串
     *
     * @param time time
     * @param format format
     * @return  String
     */
    public static String format(String time, String format) {
        return DateUtil.format(DateUtil.parse(time, format), format);
    }

    /**
     * 给定一个时间段范围，判断date是否在这个时间段内
     *
     * @param dateScope dateScope
     * @param date date
     * @return boolean
     */
    public static boolean isInScope(String dateScope, String date) {
        String today = DateUtil.getDateStr();
        String[] scope = dateScope.split("-");

        long begin = DateUtil.getTime(today + " " + scope[0]);
        long nw = DateUtil.getTime(date);
        long end = DateUtil.getTime(today + " " + scope[1]);

        if ((nw - begin) >= 0 && (end - nw) >= 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断smsDate是否在当前时间的延迟(dealDelay)内
     *
     * @param date date
     * @param dealDelay dealDelay
     * @return boolean
     */
    public static boolean checkTime(Timestamp date, long dealDelay) {
        if (date != null) {
            // 短信上行时间
            long smsTime = date.getTime();
            long now = System.currentTimeMillis();
            long timeDiff = now - smsTime;
            if (0 < timeDiff && timeDiff < dealDelay) {
                return true;
            }
        }
        return false;
    }

    /**
     * 得到当天的最后一秒的时间戳
     *
     * @return long
     */
    public static long getDayTime() {
        return addDay(parse(getDateStr()), 1).getTime() - 1;
    }

    /**
     * 得到给定日期的最后一秒的时间戳
     * @param date date
     * @return long
     */
    public static long getDateTime(String date) {
        return addDay(parse(date), 1).getTime() - 1;
    }
}
