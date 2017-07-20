package com.hejia.dataAnalysis.module.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;

public class DateUtils {
	public final static String FORMAT_COMMON_SHORTER = "yyyy-MM";
	public final static String FORMAT_COMMON_SHORT = "yyyy-MM-dd";
	public final static String FORMAT_COMMON_SHORT_NO_DELIMITER = "yyyyMMdd";
	public final static String FORMAT_COMMON_MINUTE = "yyyy-MM-dd HH:mm";
	public final static String FORMAT_COMMON_LONG = "yyyy-MM-dd HH:mm:ss";
	public final static String FORMAT_COMMON_LONG_NO_DELIMITER = "yyyyMMddHHmmss";
	public final static String FORMAT_COMMON_SHORT_CHINESE = "yyyy年MM月dd日";
	public final static String FORMAT_COMMON_MINUTE_CHINESE = "yyyy年MM月dd日 HH:mm";
	public final static String FORMAT_COMMON_LONG_CHINESE = "yyyy年MM月dd日 HH:mm:ss";
	
	private final static Logger logger = Logger.getLogger(DateUtils.class);
	private static SimpleDateFormat longDateFormat = new SimpleDateFormat(
			FORMAT_COMMON_LONG);
	private static SimpleDateFormat shortDateFormat = new SimpleDateFormat(
			FORMAT_COMMON_SHORT);
	private static SimpleDateFormat shorterDateFormat = new SimpleDateFormat(
			FORMAT_COMMON_SHORTER);
	
	/**
	 * 
	 * @param dateStr
	 *            yyyy-MM-dd
	 * @return
	 */
	public static Date parse(String dateStr) {
		return DateUtils.parse(dateStr, FORMAT_COMMON_SHORT);
	}

	/**
	 * @param dateStr
	 *            yyyy-MM-dd
	 * @param pattern
	 * @return
	 */
	public static Date parse(String dateStr, String pattern) {
		Date date = null;
		SimpleDateFormat format = null;
		if (pattern == null || ("").equals(pattern)) {
			format = shortDateFormat;
		} else {
			format = new SimpleDateFormat(pattern, Locale.US);
		}
		try {
			synchronized (format) {
				// SimpleDateFormat is not thread safe
				date = format.parse(dateStr);
			}
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
		return date;
	}

	/**
	 * @param ts
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String toString(Timestamp ts) {
		if (ts != null) {
			synchronized (longDateFormat) {
				return longDateFormat.format(ts);
			}
		}
		return null;
	}

	/**
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String toString(Date date) {
		if (date != null) {
			synchronized (longDateFormat) {
				return longDateFormat.format(date);
			}
		}
		return null;
	}
	public static String toStringShort(Date date) {//只返回年月日
		if (date != null) {
			synchronized (shortDateFormat) {
				return shortDateFormat.format(date);
			}
		}
		return null;
	}
	public static String toStringShorter(Date date) {//只返回年月
		if (date != null) {
			synchronized (shorterDateFormat) {
				return shorterDateFormat.format(date);
			}
		}
		return null;
	}

	public static String toString(Date date, String pattern) {
		if(date != null){
			SimpleDateFormat shortDateFormat = new SimpleDateFormat(pattern);
			return shortDateFormat.format(date);
		} else {
			return "";
		}
	}
	
	/**
	 * 获取年
	 */
	public static int getYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}
	
	/**
	 * 获取月
	 */
	public static int getMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}
	
	public static String getYearAndMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		return year +"年"+ month + "月";
	}
	
	/**
	 * 获取日期
	 */
	public static int getDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	public static int getAge(Date birthDay) throws Exception {
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthDay)) {
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthDay);

		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				} else {
					// do nothing
				}
			} else {
				// monthNow>monthBirth
				age--;
			}
		} else {
			// monthNow<monthBirth
			// donothing
		}

		return age;
	}

	public static String getTimestampYmdhms() {

		Calendar cal = Calendar.getInstance();

		String year = Integer.toString(cal.get(Calendar.YEAR));
		String month = Integer.toString(cal.get(Calendar.MONTH) + 1);
		String day = Integer.toString(cal.get(Calendar.DATE));
		String hour = Integer.toString(cal.get(Calendar.HOUR_OF_DAY));
		String minute = Integer.toString(cal.get(Calendar.MINUTE));
		String second = Integer.toString(cal.get(Calendar.SECOND));
		String m = Integer.toString(cal.get(Calendar.MILLISECOND));

		StringBuffer ret = new StringBuffer();
		ret.append(year).append("-");
		ret.append(month.length() == 1 ? "0" + month : month).append("-");
		ret.append(day.length() == 1 ? "0" + day : day).append(" ");
		ret.append(hour.length() == 1 ? "0" + hour : hour).append(":");
		ret.append(minute.length() == 1 ? "0" + minute : minute).append(":");
		ret.append(second.length() == 1 ? "0" + second : second);
		ret.append(":" + m);
		return ret.toString();
	}

	public static String toYMDHMS(String argYmdHMS) {

		if (argYmdHMS == null || argYmdHMS.length() == 0) {
			return null;
		}
		String strTmpDate = argYmdHMS.replaceAll("-", "");
		strTmpDate = strTmpDate.replaceAll("/", "");
		strTmpDate = strTmpDate.replaceAll(":", "");
		strTmpDate = strTmpDate.replaceAll(" ", "");
		return strTmpDate;
	}

	/**
	 * 得到类似微博的时间格式
	 * @param date
	 * @return
	 */
	public static String getShortTime(Date date) {
		String shortstring = null;

		if (date == null)
			return shortstring;

		long now = Calendar.getInstance().getTimeInMillis();
		long deltime = (now - date.getTime()) / 1000;
		if (deltime > 2 * 24 * 60 * 60) {
			shortstring = toString(date, "MM月dd日 HH:mm");
		} else if (deltime > 24 * 60 * 60) {
			shortstring = "昨天 " + toString(date, "HH:mm");
		} else if (deltime > 60 * 60) {
			shortstring = (int) (deltime / (60 * 60)) + "小时前";
		} else if (deltime > 60) {
			shortstring = (int) (deltime / (60)) + "分钟前";
		} else if (deltime > 1) {
			shortstring = deltime + "秒前";
		} else {
			shortstring = "刚刚";
		}
		return shortstring;
	}
	
	/**
	 * 获取当前日期的第一天
	 * @param date 当前日期
	 * @return 当前日期的第一天
	 */
	public static Date getFirstDayByDate(Date date){
		if(date == null){
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}
	
	public static Date getNextMonthFirst(Date date) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);// 减一个月
		cal.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		return cal.getTime();
	}
	
	/**
	 * 日期提前或者推迟N天
	 * @param date 日期
	 * @param amount 计量数
	 * @return 处理后的日期
	 */
	public static Date addDate(Date date, int amount){
		
		if(date == null){
			return null;
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, amount);
		return cal.getTime();
	}

	/**
	 * 求2个日期之间的差值
	 * @param aDate
	 * @param bDate
	 * @return
	 */
	public static long subDates(Date aDate, Date bDate){
		long sub = 0;
		
		if (aDate != null && bDate != null) {
			sub = aDate.getTime() - bDate.getTime();
		}
		
		return sub;
	}
	
	/**
	 * 获取日期0点值
	 * @param day
	 * @return
	 */
	public static Date getBeginOfDay(Date day) {
        if (day == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 000);
        return calendar.getTime();
    }
	
	/**
	 * @Description: 获取从1970年到现在的年份 
	 * @author: liming
	 * @Date: 2016年9月5日
	 *	@return
	 */
	public static List<Map<String,Integer>> getYearList(int beginYear){
	    List<Map<String,Integer>> yearList = new ArrayList<>();
	    Calendar calendar=Calendar.getInstance();
	    int current = calendar.get(Calendar.YEAR);
	    for(int i=beginYear;i<=current;i++){
	        Map<String,Integer> year = new HashMap<>();
	        year.put("value", i);
	        year.put("name", i);
	        yearList.add(year);
	    }
	    return yearList;
	}
	
	public static void main(String[] args) {
		Date date = new Date();

		String dateString = "Sun Nov 04 11:04:27 +0800 2012";
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"EEE MMM dd HH:mm:ss Z yyyy", Locale.CHINA);
		try {
			System.out.println(dateFormat.parse(dateString));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(DateUtils.toString(DateUtils.parse(dateString,
				"EEE MMM dd HH:mm:ss Z yyyy")));
		
		System.out.println("The first day is : " + getFirstDayByDate(date));
		
		System.out.println("The final day is : " + addDate(date, -30));
		
		System.out.println("The final day is : " + addDate(date, 30));
	}
}
