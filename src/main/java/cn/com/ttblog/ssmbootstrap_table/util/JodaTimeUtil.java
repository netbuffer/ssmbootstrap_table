package cn.com.ttblog.ssmbootstrap_table.util;

import org.joda.time.DateTime;

/**
 * @author netbuffer
 */
public class JodaTimeUtil {

	/**
	 * 本周开始时间
	 * 
	 * @param d
	 * @return
	 */
	public static DateTime getWeekStart(DateTime d) {
		int day = d.getDayOfWeek();
		int min = d.dayOfWeek().getMinimumValue();
		return d.plusDays(-(day - min));
	}

	/**
	 * 本周结束时间
	 * 
	 * @param d
	 * @return
	 */
	public static DateTime getWeekEnd(DateTime d) {
		int day = d.getDayOfWeek();
		int max = d.dayOfWeek().getMaximumValue();
		return d.plusDays((max - day));
	}

	/**
	 * 本月开始时间
	 * 
	 * @param d
	 * @return
	 */
	public static DateTime getMonthStart(DateTime d) {
		int day = d.getDayOfMonth();
		int min = d.dayOfMonth().getMinimumValue();
		return d.plusDays(-(day - min));
	}

	/**
	 * 本月结束时间
	 * 
	 * @param d
	 * @return
	 */
	public static DateTime getMonthEnd(DateTime d) {
		int day = d.getDayOfMonth();
		int max = d.dayOfMonth().getMaximumValue();
		return d.plusDays((max - day));
	}

	/**
	 * 本季度开始时间
	 * @param d
	 * @return
	 */
	public static DateTime getSeasonStart(DateTime d) {
		int season=getSeason(d);
		switch (season) {
		case 1:
			return new DateTime(d.getYear(),1,1,d.getHourOfDay(),d.getMinuteOfHour(),d.getSecondOfMinute());
		case 2:
			return new DateTime(d.getYear(),4,1,d.getHourOfDay(),d.getMinuteOfHour(),d.getSecondOfMinute());
		case 3:
			return new DateTime(d.getYear(),7,1,d.getHourOfDay(),d.getMinuteOfHour(),d.getSecondOfMinute());
		case 4:
			return new DateTime(d.getYear(),10,1,d.getHourOfDay(),d.getMinuteOfHour(),d.getSecondOfMinute());
		default:
			return new DateTime(d.getYear(),1,1,d.getHourOfDay(),d.getMinuteOfHour(),d.getSecondOfMinute());
		}
	}
	
	/**
	 * 本季度结束时间
	 * @param d
	 * @return
	 */
	public static DateTime getSeasonEnd(DateTime d) {
		int season=getSeason(d);
		switch (season) {
		case 1:
			return new DateTime(d.getYear(),3,31,d.getHourOfDay(),d.getMinuteOfHour(),d.getSecondOfMinute());
		case 2:
			return new DateTime(d.getYear(),6,30,d.getHourOfDay(),d.getMinuteOfHour(),d.getSecondOfMinute());
		case 3:
			return new DateTime(d.getYear(),9,30,d.getHourOfDay(),d.getMinuteOfHour(),d.getSecondOfMinute());
		case 4:
			return new DateTime(d.getYear(),12,31,d.getHourOfDay(),d.getMinuteOfHour(),d.getSecondOfMinute());
		default:
			return new DateTime(d.getYear(),3,31,d.getHourOfDay(),d.getMinuteOfHour(),d.getSecondOfMinute());
		}
	}

	/*
	 * 获取季度
	 */
	public static int getSeason(DateTime d) {
		int season=0;
		int month=d.getMonthOfYear();
		if (month >= 1 && month <= 3) {
			season = 1;
		}
		if (month >= 4 && month <= 6) {
			season = 2;
		}
		if (month >= 7 && month <= 9) {
			season = 3;
		}
		if (month >= 10 && month <= 12) {
			season = 4;
		}
		return season;
	}
	
	/**
	 * 本年开始时间
	 * @param d
	 * @return
	 */
	public static DateTime getYearStart(DateTime d) {
		return new DateTime(d.getYear(),1,1,0,0,0);
	}
	
	/**
	 * 本年结束时间
	 * @param d
	 * @return
	 */
	public static DateTime getYearEnd(DateTime d) {
		return new DateTime(d.getYear(),12,31,23,59,59);
	}
}
