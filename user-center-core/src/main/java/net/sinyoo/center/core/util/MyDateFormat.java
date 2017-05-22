package net.sinyoo.center.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理类
 * <p>
 * File: MyDateFormat.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2012 ecbox.com<br/>
 * Company: ECBOX,Inc.<br/>
 *
 * @author WangHui
 * @version 1.0
 * @date 2013-4-22
 */
public class MyDateFormat {
    public final static String FORMAT_WECHAT = "yyyyMMddHHmmss";
    public final static String FORMAT_WECHAT_MILL = "yyyyMMddHHmmssS";

    public final static String FORMAT_DATE = "yyyy-MM-dd";

    public final static String FORMAT_DATE_ = "yyyyMMdd";

    // 默认格式
    public final static String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 格式化日期输出
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DEFAULT_FORMAT);
        return df.format(date);
    }

//    public static String format(String date) {
//        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
//        try {
//            return df.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static String format(String date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return df.format(df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 日期格式化成文本
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(DEFAULT_FORMAT);
        if (null != pattern) {
            df.applyPattern(pattern);
        }
        return df.format(date);
    }

    /**
     * 解析成日期
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parse(String date) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(DEFAULT_FORMAT);
        return df.parse(date);
    }

    /**
     * 解析成日期
     *
     * @param millis
     * @return
     * @throws ParseException
     */
    public static Date parseMill(Long millis) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        if (millis != null && millis > 0) {
            calendar.setTimeInMillis(millis);
            return calendar.getTime();
        } else {
            return null;
        }
    }

    /**
     * 解析成日期
     *
     * @param date
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date parse(String date, String pattern) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(DEFAULT_FORMAT);
        if (null != pattern) {
            df.applyPattern(pattern);
        }
        return df.parse(date);
    }

    /**
     * 获取到日期的0点 传入2014-12-01，获取到 2014-12-01 00:00:00.000 不传参数，获取到当天的0点
     *
     * @return
     */
    public static String getTimeMillisString0(String str_date) {
        Calendar calendar = Calendar.getInstance();
        if (str_date != null && !"".equals(str_date)) {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = f.parse(str_date);
                calendar.setTime(date);
                return calendar.getTimeInMillis() + "";
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return "" + calendar.getTimeInMillis();
    }

    /**
     * 获取到日期的24点 传入2014-12-01，获取到 2014-12-01 23:59:59.999 不传参数，获取到当天的24点
     *
     * @return
     */
    public static String getTimeMillisString24(String str_date) {
        Calendar calendar = Calendar.getInstance();
        if (str_date != null && !"".equals(str_date)) {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = f.parse(str_date);
                calendar.setTime(date);
                calendar.set(Calendar.HOUR_OF_DAY, 23);
                calendar.set(Calendar.MINUTE, 59);
                calendar.set(Calendar.SECOND, 59);
                calendar.set(Calendar.MILLISECOND, 999);
                return calendar.getTimeInMillis() + "";
            } catch (ParseException e) {
                e.printStackTrace();
                return "0";
            }
        }
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return "" + calendar.getTimeInMillis();
    }

    /**
     * 获取几天后的时间
     *
     * @param date
     * @param day
     * @return
     */
    public static synchronized Date getDateNextDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + day);
        return calendar.getTime();
    }

    public static synchronized Date getDateLastDay10(String date, int day) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - day);
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 获取几天前的时间
     *
     * @param date
     * @param day
     * @return
     */
    public static synchronized Date getDateLastDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - day);
        return calendar.getTime();
    }

    /**
     * 获取几小时前的时间
     *
     * @param date
     * @param hour
     * @return
     */
    public static synchronized Date getDateLastHour(Date date, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - hour);
        return calendar.getTime();
    }

    /**
     * 获取几小时后的时间
     *
     * @param date
     * @param hour
     * @return
     */
    public static synchronized Date getDateNextHour(Date date, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);
        return calendar.getTime();
    }

    /**
     * 获取多少分钟以后的时间
     *
     * @param date
     * @param minute
     * @return
     */
    public static synchronized Date getDateNextSomeMinute(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        long timeMillis = calendar.getTimeInMillis() + (minute * 60 * 1000);
        calendar.setTimeInMillis(timeMillis);
        return calendar.getTime();
    }

    /**
     * 获取多少分钟之前的时间
     *
     * @param date
     * @param minute
     * @return
     */
    public static synchronized Date getDateLastSomeMinute(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        long timeMillis = calendar.getTimeInMillis() - (minute * 60 * 1000);
        calendar.setTimeInMillis(timeMillis);
        return calendar.getTime();
    }

    /**
     * 获取到日期的0点 传入2014-12-01，获取到 2014-12-01 00:00:00.000 不传参数，获取到当天的0点
     *
     * @return
     */
    public static Date getDate0(Date mDate) {
        Calendar calendar = Calendar.getInstance();
        if (mDate != null) {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = f.parse(f.format(mDate));
                calendar.setTime(date);
                return calendar.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取到日期的24点 传入2014-12-01，获取到 2014-12-01 23:59:59.999 不传参数，获取到当天的24点
     *
     * @return
     */
    public static Date getDate24(Date mDate) {
        Calendar calendar = Calendar.getInstance();
        if (mDate != null) {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = f.parse(f.format(mDate));
                calendar.setTime(date);
                calendar.set(Calendar.HOUR_OF_DAY, 23);
                calendar.set(Calendar.MINUTE, 59);
                calendar.set(Calendar.SECOND, 59);
                calendar.set(Calendar.MILLISECOND, 999);
                return calendar.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
                return calendar.getTime();
            }
        }
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 获取一个星期的开始
     *
     * @param millis
     * @return
     */
    public static Date getWeekStart(Long millis) {
        Calendar calendar = Calendar.getInstance();
        if (millis != null && millis > 0) {
            calendar.setTimeInMillis(millis);
        }
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        if (weekDay == Calendar.SUNDAY) {
            weekDay = 7;
        } else {
            weekDay--;
        }
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - weekDay + 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取一个星期的开始
     *
     * @param date
     * @return
     */
    public static Date getWeekStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        if (weekDay == Calendar.SUNDAY) {
            weekDay = 7;
        } else {
            weekDay--;
        }

        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - weekDay + 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取一个星期的结束
     * 获取到周日后一天的0点
     *
     * @param millis
     * @return
     */
    public static Date getWeekEnd(Long millis) {
        Calendar calendar = Calendar.getInstance();
        if (millis != null && millis > 0) {
            calendar.setTimeInMillis(millis);
        }
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        if (weekDay == Calendar.SUNDAY) {
            weekDay = 7;
        } else {
            weekDay--;
        }
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - weekDay + 1);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 7);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取传入时间所在年的最后一周
     *
     * @param mDate
     * @return
     */
    public static int getYearLastWeek(Date mDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
        calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
        calendar.set(Calendar.DAY_OF_YEAR, -1);

        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取一个月的开始
     *
     * @param millis
     * @return
     */
    public static Date getMonthStart(Long millis) {
        Calendar calendar = Calendar.getInstance();
        if (millis != null && millis > 0) {
            calendar.setTimeInMillis(millis);
        }
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取一个月的开始
     *
     * @param date
     * @return
     */
    public static Date getMonthStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取一个月的结束
     *
     * @param millis
     * @return
     */
    public static Date getMonthEnd(Long millis) {
        Calendar calendar = Calendar.getInstance();
        if (millis != null && millis > 0) {
            calendar.setTimeInMillis(millis);
        }
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


    /**
     * 获取年龄
     *
     * @param date
     * @return
     */
    public static int getAge(Date date) {
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.YEAR);
        calendar.setTime(date);
        return today - calendar.get(Calendar.YEAR);
    }

    /**
     * 构造
     *
     * @param args
     */
//    public static void main(String[] args) {
////        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
////
////        Calendar c = Calendar.getInstance();
////        System.out.println("当前时间：" + sdf.format(c.getTime()));
////
////        c.add(Calendar.DAY_OF_YEAR, -1);// 1天前
////        System.out.println("1天前：" + sdf.format(c.getTime()));
////
////        c = Calendar.getInstance();
////        c.add(Calendar.HOUR_OF_DAY, -1);// 1小时前
////        System.out.println("1小时前：" + sdf.format(c.getTime()));
////
////        c = Calendar.getInstance();
////        c.add(Calendar.MINUTE, -1);// 1分钟前
////        System.out.println("1分钟前：" + sdf.format(c.getTime()));
////
////        System.out.println(sdf.format(c.getTime()));
////        System.out.println(System.currentTimeMillis() / 1000);
//
////        System.out.println(getYearLastWeek(new Date()));
//
//        System.out.println(format("2018-12-2"));
//
//
//
//    }
}
