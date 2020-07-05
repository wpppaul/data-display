package org.wpp.frogdata.datadisplay.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description:  
 * @Param:
 * @Return: 
 * @Author: wupp
 * @Date: 2020/7/4 18:18
 */
@Slf4j
public class DateUtil {

    public static final String ISO_DATE_FORMAT = "yyyy-MM-dd";
    public static final String ISO_DATETIME_NO_T_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String ISO_DATE_NO_HYPHEN_FORMAT = "yyyyMMdd";
    public static final String ISO_DATETIME_NO_HYPHEN_AND_T_FORMAT = "yyyyMMdd HH:mm:ss";
    public static final String ISO_DATETIME_NO_HYPHEN_AND_ALL_FORMAT = "yyyyMMddHH:mm:ss";
    public static final String ISO_DATETIME_NO_HYPHEN_AND_NO_FORMAT = "yyyyMMddHHmmss";

    /**
     * 将timestamp转换为Date类型
     */
    public static Date convertTimestampToDate(Timestamp timestamp) {
        return new Date(timestamp.getTime());
    }

    /**
     * 将日期按照指定模式转换为字符�?
     *
     * @param date    日期
     * @param pattern 指定模式
     * @return 转换后的字符�?
     */
    public static String convertToString(final Date date, final String pattern) {
        if (date != null) {
            return new SimpleDateFormat(pattern).format(date);
        } else {
            return null;
        }
    }

    /**
     * 将字符串按照指定格式转换为日�?
     *
     * @param source  字符�?
     * @param pattern 指定格式
     * @return 日期
     * @throws ParseException
     */
    public static Date converToDate(final String source, final String pattern) throws ParseException {
        return new SimpleDateFormat(pattern).parse(source);
    }

    /**
     * 将某种格式的日期字符串转换成另外�?种格式的日期字符�?
     *
     * @param source
     * @param pattern1
     * @param pattern2
     * @return 字符�?
     * @throws ParseException
     */
    public static String converStringToString(final String source, final String pattern1, final String pattern2)
            throws ParseException {
        if ("".equals(source) || null == source) {
            return source;
        }
        return new SimpleDateFormat(pattern2).format(new SimpleDateFormat(pattern1).parse(source));
    }

    /**
     * 取得当前年份
     *
     * @return 当前年份
     */
    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 取得当前月份
     *
     * @return 当前月份
     */
    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 取得当前月份的第�?天对应的日期字符�?
     */
    public static String getBeginDateOfMonth() {
        Calendar calendar = Calendar.getInstance();
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = calendar.get(Calendar.MONTH) < 9 ? "0" + String.valueOf(calendar.get(Calendar.MONTH) + 1)
                : String.valueOf(calendar.get(Calendar.MONTH) + 1);
        return year + "-" + month + "-01";
    }

    /**
     * 取得今天对应的日期字符串
     */
    public static String getCurrentDate() {
        return convertToString(new Date(), ISO_DATE_FORMAT);
    }

    /**
     * 取得昨天对应的日期字符串
     */
    public static String getYesterdayDate() {
        String time = DateUtil.getCurrentDate();
        System.out.println("time = "+time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date=null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        int day=calendar.get(Calendar.DATE);
        // 后一天为 +1   前一天 为-1
        calendar.set(Calendar.DATE,day-1);
        String lastDay = sdf.format(calendar.getTime());
        return lastDay;
    }

    public static String getCurrentDate(Date date,String formart) {
        return convertToString(new Date(), formart);
    }

    /**
     * 获取当前时间字符串
     *
     * @return 返回年月日时分秒格式 yyyyMMddHHmmss
     */
    public static String getCurrentLongDate() {
        return convertToString(new Date(), ISO_DATETIME_NO_HYPHEN_AND_NO_FORMAT);
    }

    /**
     * 取得今天对应的日期字符串
     */
    public static String getCurrentDate(String pattern) {
        return convertToString(new Date(), pattern);
    }

    /**
     * 判断�?个日期字符串是否合法
     *
     * @param date    日期字符�?
     * @param pattern 日期模式
     * @return 合法则返回true
     */
    public static boolean isValidDate(final String date, final String pattern) {
        if (date != null) {
            try {
                converToDate(date, pattern);
                return true;
            } catch (ParseException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 将日期转换为当日�?始的日期
     */
    public static Date getBeginDateOfDay(final Date date) {
        try {
            return converToDate(convertToString(date, ISO_DATE_FORMAT) + " 00:00:00", ISO_DATETIME_NO_T_FORMAT);
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage(), e); // Will Never happen
        }
    }

    /**
     * 将输入的日期字符串转换为当日�?始日�?
     *
     * @throws ParseException
     */
    public static Date getBeginDateOfDay(final String date, final String pattern) throws ParseException {
        return converToDate(date + " 00:00:00", pattern);
    }

    /**
     * 将输入日期转换为当日结束日期
     */
    public static Date getEndDateOfDay(final Date date) {
        try {
            return converToDate(convertToString(date, ISO_DATE_FORMAT) + " 23:59:59", ISO_DATETIME_NO_T_FORMAT);
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage(), e); // Will Never happen
        }
    }

    /**
     * 将输入的日期字符串转换为当日结束日期
     *
     * @throws ParseException
     */
    public static Date getEndDateOfDay(final String date, final String pattern) throws ParseException {
        return converToDate(date + " 23:59:59", pattern);
    }

    public static String getEndDateOfDay(final String pattern) {
        return getCurrentDate(pattern) + "23:59:59";
    }


    //yyyy-MM-dd'T'HH:mm:ss.SSS 转 yyyy-MM-dd HH:mm:ss
    public static String getDate(final String source) throws ParseException {
        return new SimpleDateFormat(ISO_DATETIME_NO_T_FORMAT).format(converToDate(source,"yyyy-MM-dd'T'HH:mm:ss.SSS"));
    }


    /**
     * 求某年月的第�?�? java�?0�?11分别�?1�?12�?
     *
     * @param year
     * @param month
     * @return Date
     */
    public static Date getMonthFirst(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 求某年月的最后一�? java�?0�?11分别�?1�?12�?
     *
     * @param year
     * @param month
     * @return Date
     */
    public static Date getMonthLast(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.roll(Calendar.DAY_OF_MONTH, -1);// 向指定日历字段添加指定（有符号的）时间量，不更改更大的字段�?�负的时间量意味�?向下滚动�?
        Date currMonthLast = calendar.getTime();

        return currMonthLast;
    }

    /**
     * 某年某月第几周第几天 是几月几�?
     *
     * @param year        年份
     * @param month       月份
     * @param weekOfMonth 这个月的第几�?
     * @param dayOfWeek   星期�?
     * @return
     */
    public static String weekDateToData(int year, int month, int weekOfMonth, int dayOfWeek) {
        Calendar c = Calendar.getInstance();
        // 计算�? x�? y�? 1�? 是星期几
        c.set(year, month - 1, 1);

        // 如果i_week_day =1 的话 实际上是周日
        int i_week_day = c.get(Calendar.DAY_OF_WEEK);

        int sumDay = 0;
        // dayOfWeek+1 就是星期几（星期�? �? 1�?
        if (i_week_day == 1) {
            sumDay = (weekOfMonth - 1) * 7 + dayOfWeek + 1;
        } else {
            sumDay = 7 - i_week_day + 1 + (weekOfMonth - 1) * 7 + dayOfWeek + 1;
        }
        // �?1号的基础上加上相应的天数
        c.set(Calendar.DATE, sumDay);
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
        return sf2.format(c.getTime());
    }

    /**
     * 获得当天之后的N天日�?
     *
     * @param num 当天后的第N�?
     * @return 返回的日�?
     */
    public static List<String> getNextDays(int num) {
        List<String> list = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
        for (int i = 1; i < num; i++) {
            list.add(sdf.format(getAfterDate(i)));
        }
        return list;
    }

    /**
     * 获取当前日期n天后的日�?
     *
     * @param n:返回当天后的第N�?
     * @return 返回的日�?
     */
    public static Date getAfterDate(int n) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, n);
        return c.getTime();
    }

    /**
     * 获取当前日期n天后的日�?
     *
     * @param n:返回当天后的第N�?
     * @return 返回的日�?
     */
    public static Date getAfterDate(Date date, int n) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, n);
        return calendar.getTime();
    }

    public static String format2yyyyMMdd(String time) {
        try {
            return new SimpleDateFormat(ISO_DATE_NO_HYPHEN_FORMAT)
                    .format(new SimpleDateFormat(ISO_DATE_FORMAT).parse(time));
        } catch (ParseException e) {
            log.error(e.getMessage());
            return time.substring(0, 4) + time.substring(5, 7) + time.substring(8, 10);
        }
    }

    public static String getFlowNo() {
        return (new SimpleDateFormat(ISO_DATETIME_NO_HYPHEN_AND_NO_FORMAT).format(new Date()) + generate4RandomInt());
    }

    public static int generate4RandomInt() {
        return (int) (Math.random() * 9000 + 1000);
    }

    public static void main(String[] args) {
        System.out.println(getFlowNo());
    }

    public static String format2yyyyMMdd(Date date) {
        return new SimpleDateFormat("yyyyMMdd").format(date);
    }
    public static String format3yyyyMMdd(Date date) {
        return new SimpleDateFormat(ISO_DATETIME_NO_HYPHEN_AND_ALL_FORMAT).format(date);
    }

    public static String format4yyyyMMdd(Date date) {
        return new SimpleDateFormat(ISO_DATE_FORMAT).format(date);
    }

    public static String formatStandard(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:SS").format(date);
    }

    public static String format2YYYYMMDDHHMMSS(Long milliseconds){
        return new SimpleDateFormat("YYYYMMddHHmmss").format(new Date(milliseconds));
    }

}
