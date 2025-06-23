package org.example.vibee.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateTimeUtil {
	// 默认时间格式
	public final static String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	// 默认日期格式
	public final static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	public final static String YYYY_MM_DD = "yyyy/MM/dd";
	public final static String YYYYMMDD = "yyyyMMdd";
	public final static String YYYYMM = "yyyyMM";
	public final static String MM_DD_YYYY = "MM/dd/yyyy";
	public final static String DD_MM_YYYY = "dd/MM/yyyy";
	public final static String HH24_MM_SS = "HH:mm:ss";
	public final static String HH24_MM = "HH:mm";
	public final static String HH12_MM_SS = "hh:mm:ss";
	public final static String HH12_MM_A = "hh:mm a";
	public final static String HH12_MM_SS_A = "hh:mm:ss a";
	public final static String MM_DD_HH_MM = "MM-dd hh:mm";
	public final static String WEEK_FMT = "yyyy年MM月DD日 E HH:mm";
	public final static String WEEK_DATE_FMT = "yyyy年MM月DD日 E";

	private DateTimeUtil() {
		super();
	}

	/**
	 * 解析时间日期
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public final static String format(Date date, String format) {
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	/**
	 * 解析时间日期
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public final static String format(Date date) {
		DateFormat df = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		return df.format(date);
	}

	/**
	 * 将字符串解析成java.util.Date
	 * 
	 * @param source
	 * @param format
	 * @return
	 */
	public final static Date parseToDate(String source, String format) {
		DateFormat df = new SimpleDateFormat(format);
		try {
			return df.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将字符串解析成java.sql.Date
	 * 
	 * @param source
	 * @param format
	 * @return
	 */
	public final static java.sql.Date parseToSqlDate(String source,
			String format) {
		return new java.sql.Date(parseToDate(source, format).getTime());
	}

	/**
	 * 将字符串解析成java.sql.Time
	 * 
	 * @param source
	 * @param format
	 * @return
	 */
	public final static Time parseToTime(String source, String format) {
		return new Time(parseToDate(source, format).getTime());
	}

	/**
	 * 将字符串解析成java.sql.Timestamp
	 * 
	 * @param source
	 * @param format
	 * @return
	 */
	public final static Timestamp parseToTimestamp(String source, String format) {
		return new Timestamp(parseToDate(source, format).getTime());
	}

	/**
	 * 获取当月的天数
	 * 
	 * @return
	 */
	public final static int getMonthDays() {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.DATE, 1); // 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1); // 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 格式化日期
	 * 
	 * @param time
	 *            日期
	 * @param fmt
	 *            时间格式
	 * @return 格式化的日期字符串
	 */
	public final static String formateDate(Timestamp time, String fmt) {
		String date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		date = sdf.format(time);
		return date;
	}

	/**
	 * 返回与当天的关系 如
	 * 
	 * @param minusDays
	 *            距离当前天的天数
	 * @return
	 */
	public static String getDayRelation(int minusDays) {
		switch (minusDays) {
		case 0:
			return "今天";
		case 1:
			return "明天";
		case 2:
			return "后天";
		default:
			return "今天";
		}
	}

	/**
	 * 生成星期天数
	 * 
	 * @param day
	 *            周天
	 * @return
	 */
	public static String dayOfWeek(int day) {
		String strWeekOfDay = null;
		switch (day) {
		case 0:
			strWeekOfDay = "周日";
			break;
		case 1:
			strWeekOfDay = "周一";
			break;
		case 2:
			strWeekOfDay = "周二";
			break;
		case 3:
			strWeekOfDay = "周三";
			break;
		case 4:
			strWeekOfDay = "周四";
			break;
		case 5:
			strWeekOfDay = "周五";
			break;
		case 6:
			strWeekOfDay = "周六";
			break;
		default:
			strWeekOfDay = "周日";
			break;
		}
		return strWeekOfDay;

	}
}
