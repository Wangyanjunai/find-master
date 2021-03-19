package com.potato369.find.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <pre>
 * @PackageName com.potato369.answer.app.web.utils
 * @ClassName DateUtil
 * @Desc 日期时间处理相关工具类
 * @WebSite https://www.potato369.com
 * @Author Jack
 * @Date 2019/7/12 15:10
 * @CreateBy IntellJ IDEA 2019.1.1
 * @Copyright Copyright (c) 2016 ~ 2020 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
public class DateUtil {

    public static final String sdfYearFmt = "yyyy";

    public static final String sdfDayFmt = "yyyy-MM-dd";

    public static final String sdfDaysFmt = "yyyyMMdd";

    public static final String sdfTimeFmt = "yyyy-MM-dd HH:mm:ss";
    
    public static final String sdfDateFmt = "HH:mm:ss";

    public static final String sdfTimeCNFmt = "yyyy年MM月dd日 HH:mm:ss";

    public static final String sdfTimeMinuFmt = "yyyyMMddHHmmss";
    
    public static final String sdfTimeMinuFmt1 = "yyyyMMdd HH:mm:ss";
    
    public static final String sdfTimeStampFmt = "yyyyMMddHHmmssSSS";

    public final static SimpleDateFormat sdfYear = new SimpleDateFormat(sdfYearFmt);

    public final static SimpleDateFormat sdfDay = new SimpleDateFormat(sdfDayFmt);

    public final static SimpleDateFormat sdfDays = new SimpleDateFormat(sdfDaysFmt);

    public final static SimpleDateFormat sdfTime = new SimpleDateFormat(sdfTimeFmt);

    public final static SimpleDateFormat sdfTimeCN = new SimpleDateFormat(sdfTimeCNFmt);

    public final static SimpleDateFormat sdfTimeMinu = new SimpleDateFormat(sdfTimeMinuFmt);

    public final static SimpleDateFormat sdfTimeStamp = new SimpleDateFormat(sdfTimeStampFmt);

    /**
     * 获取YYYY格式
     *
     * @return
     */
    public static String getYear() {
        return sdfYear.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD格式
     *
     * @return
     */
    public static String getDay() {
        return sdfDay.format(new Date());
    }

    /**
     * 获取YYYYMMDD格式
     *
     * @return
     */
    public static String getDays() {
        return sdfDays.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     *
     * @return
     */
    public static String getTime() {
        return sdfTime.format(new Date());
    }

    /**
     * 获取YYYY年MM月DD日 HH:mm:ss中文格式
     *
     * @return
     */
    public static String getTimeCN() {
        return sdfTimeCN.format(new Date());
    }

    /**
     * 获取YYYYMMDDHHmmss格式
     *
     * @return
     */
    public static String getTimeMinu() {
        return sdfTimeMinu.format(new Date());
    }

    /**
     * 获取YYYYMMDDHHmmssSSS格式
     *
     * @return
     */
    public static String getTimestamp() {
        return sdfTimeStamp.format(new Date());
    }

    /**
     * @param s
     * @param e
     * @return boolean
     * @Title: compareDate
     * @Description (日期比较 ， 如果s > = e 返回true 否则返回false)
     * @author jack
     */
    public static boolean compareDateStr(String s, String e) {
        if (fomatDateTimeStamp(s) == null || fomatDateTimeStamp(e) == null) {
            return false;
        }
        return fomatDateTimeStamp(s).getTime() >= fomatDateTimeStamp(e).getTime();
    }

    /**
     * @param s
     * @param e
     * @return boolean
     * @Title: compareDate
     * @Description (日期比较 ， 如果s > e 返回true 否则返回false)
     * @author jack
     */
    public static boolean compareDate(Date s, Date e) {
        if (s == null || e == null) {
            return false;
        }
        return s.getTime() >= e.getTime();
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static Date fomatDate(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static Date fomatDateTimeStamp(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 校验日期是否合法
     *
     * @return
     */
    public static boolean isValidDate(String s) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fmt.parse(s);
            return true;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
    }

    public static int getDiffYear(String startTime, String endTime) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
            return years;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return 0;
        }
    }

    public static String getBeforeYearByAge(int age) {
        try {
            Calendar canlendar = Calendar.getInstance(); // java.util包
            canlendar.add(Calendar.YEAR, -age); // 年减
            Date year = canlendar.getTime();
            SimpleDateFormat sdfd = new SimpleDateFormat(sdfDayFmt);
            return sdfd.format(year);
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return null;
        }
    }

    /**
     * <li>功能描述：时间相减得到天数
     *
     * @param beginDateStr
     * @param endDateStr
     * @return long
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr, String endDateStr) {
        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;
        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        // System.out.println("相隔的天数="+day);
        return day;
    }

    /**
     * 得到n天之后的日期
     *
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);

        return dateStr;
    }

    /**
     * <pre>
     * getAfterDayDate方法的作用：得到days天之后的日期时间
     * @param now 当前的日期时间
     * @param days 多少天
     * </pre>
     */
    public static Date getAfterDayDate(Date now, int days) {
        Calendar calendar_day = Calendar.getInstance();
        calendar_day.setTime(now);
        calendar_day.add(Calendar.DATE, days);
        return calendar_day.getTime();
    }

    /**
     * <pre>
     * getAfterMonthDate方法的作用：得到months月之后的日期时间
     * @param now 当前的日期时间
     * @param months 多少月
     * </pre>
     */
    public static Date getAfterMonthDate(Date now, int months) {
        Calendar calendar_day = Calendar.getInstance();
        calendar_day.setTime(now);
        calendar_day.add(Calendar.MONTH, months);
        return calendar_day.getTime();
    }

    /**
     * 得到n天之后是周几
     *
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);

        return dateStr;
    }

    /**
     * 时间转换成字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String strFormat(Date date, String format) {
        try {
            return new SimpleDateFormat(format).format(date);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 字符串转时间
     *
     * @param formate
     * @param str_time
     * @return
     */
    public static Date dateFormat(String formate, String str_time) {

        try {
            return new SimpleDateFormat(formate).parse(str_time);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String formatDateStr(String format, String time) {
        return DateUtil.strFormat(DateUtil.dateFormat(format, time), format);
    }

    /**
     * 获取当前时间之前或之后几小时 hour
     */

    public static String getTimeByHour(int hour) {

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());

    }

    /**
     * 获取当前时间之前或之后几小时 hour
     */

    public static Date getTimeByHourAndDate(int hour, Date date) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);

        return calendar.getTime();

    }

    /**
     * 获取当前时间之前或之后几分钟
     */

    public static Date getTimeByMinuteAndDate(int minute, Date date) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + minute);

        return calendar.getTime();

    }

    /**
     * 获取当前时间之前或之后几个月的时间
     */

    public static Date getTimeByMonthAndDate(int month, Date date) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + month);

        return calendar.getTime();

    }

    /**
     * 获取当前时间之前或之后几分钟 minute
     */

    public static String getTimeByMinute(int minute) {

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.MINUTE, minute);

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());

    }

    /**
     * 从Date类型的时间中提取日期部分
     */
    public static Date getDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 从Date类型的时间中提取时间部分
     */
    public static Date getTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, 1970);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 格式化日期为指定格式字符串
     *
     * @param date 日期
     * @param fmt  格式
     * @return
     */
    public static String fomateDate(Date date, String fmt) {
        return new SimpleDateFormat(fmt).format(date);
    }

    /**
     * 判断某一时间是否在a和b时间范围内
     *
     * @param date 时间
     * @param a    开始时间
     * @param b    结束时间
     * @return
     */
    public static boolean betweenAndB(Date date, Date a, Date b) {
        if (date == null || a == null || b == null) {
            return false;
        }
        if (date.getTime() < a.getTime()) {
            return false;
        }
        if (date.getTime() > b.getTime()) {
            return false;
        }
        if (date.getTime() >= a.getTime() && date.getTime() <= b.getTime()) {
            return true;
        }
        return false;
    }
    
    public static String  randomDate() {
    	Calendar calendarStart = Calendar.getInstance();
    	calendarStart.set(Calendar.HOUR, 9);
    	calendarStart.set(Calendar.SECOND, 0);
    	calendarStart.set(Calendar.MINUTE, 0);
    	calendarStart.set(Calendar.MILLISECOND, 0);
    	
    	Calendar calendarEnd = Calendar.getInstance();
    	calendarEnd.set(Calendar.HOUR, 23);
    	calendarEnd.set(Calendar.SECOND, 0);
    	calendarEnd.set(Calendar.MINUTE, 0);
    	calendarEnd.set(Calendar.MILLISECOND, 0);
    	long dateTime = random(calendarStart.getTimeInMillis(), calendarEnd.getTimeInMillis());
    	Date date = new Date(dateTime);
    	return fomateDate(date, sdfDateFmt);
    }
    
    public static long random(long begin, long end) {
    	long rtn = begin + (long) (Math.random() * (end - begin));
    	if (rtn == begin || rtn == end) {
			return random(begin, end);
		}
    	return rtn;
    }
    
    public static Date randomDateTime(String datetimeString) {
    	String dateString1 = new StringBuilder()
				.append(datetimeString.substring(0, 4)).append("-")
				.append(datetimeString.substring(4, 6)).append("-")
				.append(datetimeString.substring(6, datetimeString.length())).toString();
    	System.out.println(dateString1);
		String dateString2 = new StringBuilder()
				.append(dateString1).append(" ")
				.append(randomDate()).toString();
		return fomatDateTimeStamp(dateString2);
	}


    public static void main(String[] args) {
//        System.out.println(getDays());
//        System.out.println(getAfterDayWeek("3"));
//        System.out.println(getTimestamp());
//        String orderId = DateUtil.getTimestamp().concat(UUIDUtil.genTimstampUUID().substring(17, 32));
//        System.out.println(orderId);
//        Date date = DateUtil.dateFormat("yyyyMMddHHmmss", "20170415012956");
//        String str = DateUtil.strFormat(date, "yyyy-MM-dd HH:mm:ss");
//        System.out.println("yyyy-MM-dd HH:mm:ss" + str);
//
//        // 获取当前时间5分钟前的时间 格式yyyy-MM-dd HH:mm:ss
//
//        System.out.println("获取当前时间5分钟前的时间 格式yyyy-MM-dd HH:mm:ss==>" + getTimeByMinute(-5));
//
//        // 获取当前时间1小时后的时间 格式yyyy-MM-dd HH:mm:ss
//
//        System.out.println("获取当前时间1小时后的时间 格式yyyy-MM-dd HH:mm:ss==>" + getTimeByHour(1));
//        System.out.println("获取当前格式yyyy-MM-dd HH:mm:ss时间 ==>" + DateUtil.fomatDateTimeStamp(DateUtil.getTime()));
//
//    	Date dateNow = new Date();
//    	Date date2 = DateUtil.getTimeByHourAndDate(1, dateNow);
//    	Date date3 = DateUtil.getTimeByMonthAndDate(1, dateNow);
//    	System.out.println("获取当前时间1小时后的时间 格式yyyy-MM-dd HH:mm:ss==>" + DateUtil.strFormat(date2, sdfTimeCNFmt));
//    	System.out.println("获取当前格式yyyy-MM-dd HH:mm:ss时间 ==>" + DateUtil.strFormat(date3, sdfTimeCNFmt));
//        Date date3 = DateUtil.getTimeByMinuteAndDate(10, new Date());
//        System.out.println("获取当前时间10分钟后的时间 格式yyyy-MM-dd HH:mm:ss==>" + DateUtil.strFormat(date3, sdfTimeCNFmt));
//        System.out.println(getBeforeYearByAge(35));
//        System.out.println(getDiffYear("1988-06-08", DateUtil.strFormat(new Date(), sdfDayFmt)));
//        System.out.print(randomDate());
        System.out.print(fomateDate(randomDateTime("19880608"), sdfTimeFmt));
    }
}
