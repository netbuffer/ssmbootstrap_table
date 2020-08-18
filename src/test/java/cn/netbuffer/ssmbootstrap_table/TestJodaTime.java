package cn.netbuffer.ssmbootstrap_table;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.netbuffer.ssmbootstrap_table.util.JodaTimeUtil;

/**
 * 	 LocalDate - date without time
	 LocalTime - time without date
	 Instant - an instantaneous point on the time-line
	 DateTime - full date and time with time-zone
	 DateTimeZone - a better time-zone
	 Duration and Period - amounts of time
	 Interval - the time between two instants
	 A comprehensive and flexible formatter-parser
 */
//@Ignore
public class TestJodaTime {
	Logger log = LoggerFactory.getLogger(this.getClass());
	private final static  String FORMAT="yyyy-MM-dd";

	@Test
	@Ignore
	public void testWeekStart(){
		log.debug("本周开始时间:{}",JodaTimeUtil.getWeekStart(new DateTime()).toString(FORMAT));
	}
	@Test
	@Ignore
	public void testWeekEnd(){
		log.debug("本周结束时间:{}",JodaTimeUtil.getWeekEnd(new DateTime()).toString(FORMAT));
	}
	@Test
	@Ignore
	public void testMonthStart(){
		log.debug("本月开始时间:{}",JodaTimeUtil.getMonthStart(new DateTime()).toString(FORMAT));
	}
	@Test
	@Ignore
	public void testMonthEnd(){
		log.debug("本月结束时间:{}",JodaTimeUtil.getMonthEnd(new DateTime()).toString(FORMAT));
	}
	@Test
	@Ignore
	public void testSeasonStart(){
		log.debug("本季度开始时间:{}",JodaTimeUtil.getSeasonStart(new DateTime()).toString(FORMAT));
	}
	@Test
	@Ignore
	public void testSeasonEnd(){
		log.debug("本季度结束时间:{}",JodaTimeUtil.getSeasonEnd(new DateTime()).toString(FORMAT));
	}
	@Test
	@Ignore
	public void testYearStart(){
		log.debug("本年度开始时间:{}",JodaTimeUtil.getYearStart(new DateTime()).toString(FORMAT));
	}
	@Test
	@Ignore
	public void testYearEnd(){
		log.debug("本年度结束时间:{}",JodaTimeUtil.getYearEnd(new DateTime()).toString(FORMAT));
	}
	@Test
	@Ignore
	public void t(){
		SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(fmt.format(new Date((long)System.currentTimeMillis())));
		System.out.println(fmt.format(new Date(new Date().getTime())));
	}
	@Test
	@Ignore
	public void testCompare(){
		Date yes=new DateTime().plusDays(-1).toDate();
		Date today=new Date();
		System.out.println("yes.compareTo(today):"+yes.compareTo(today));
		System.out.println("today.compareTo(yes):"+today.compareTo(yes));
		System.out.println("today.compareTo(today):"+today.compareTo(today));
	}
	@Test
	@Ignore
	public void testFormat(){
		System.out.println("-----------------------------------");
		String yDate="2016-07-15";
//		String date="2";
//		int flag=1;
		String date="12:52";
		int flag=0;
		Date current=new Date();
		if(flag==0){
			Date limit=new Date();
			String[] h=date.split(":");
			limit.setHours(Integer.parseInt(h[0]));
			limit.setMinutes(Integer.parseInt(h[1]));
			if(current.compareTo(limit)>0){
				System.out.println(">");
			}
			System.out.println("^^^^^^limit:"+new DateTime(limit).toString("yyyy-MM-dd HH:mm:ss")+",current:"+new DateTime(current).toString("yyyy-MM-dd HH:mm:ss"));
		}else if(flag==1){
			Date d=null;
			try {
				d=new SimpleDateFormat("yyyy-MM-dd").parse(yDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Date limit=new DateTime(d).plusDays(-Integer.parseInt(date)).toDate();
			if(limit.compareTo(current)>0){
				System.out.println(">");
			}
			System.out.println("******limit:"+new DateTime(limit).toString("yyyy-MM-dd HH:mm:ss")+",current:"+new DateTime(current).toString("yyyy-MM-dd HH:mm:ss"));
		}
	}
	
	@Test
	public void parse(){
		Integer time=1469151612;
		System.out.println("日期:"+new DateTime(new Date(time*1000L)).toString("yyyy-MM-dd HH:mm:ss"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date(time*1000L));
		System.out.println(date); 
	}
	
	@Test
	public void getTimeStamp(){
		System.out.println("timestamp:"+System.currentTimeMillis());
	}

	@Test
	public void test() {
		LocalDate localDate = new LocalDate();
		System.out.println("localDate：" + localDate);
		System.out.println("localDate.toDate()：" + localDate.toDate());
		LocalTime localTime=new LocalTime();
		System.out.println("localTime:"+localTime);
		System.out.println("localTime.toDateTimeToday():"+localTime.toDateTimeToday());
		LocalTime minTime=new LocalTime(0,0,0,0);
		LocalTime maxTime=new LocalTime(23,59,59,59);
		System.out.println("maxTime:"+maxTime);
		System.out.println("maxTime:"+maxTime.toDateTimeToday());
		System.out.println("昨天的时间从:"+minTime.toDateTimeToday().plusDays(-1)+"到:"+maxTime.toDateTimeToday().plusDays(-1));
		DateTime d1=new DateTime();
		DateTime d2=new DateTime().plusMinutes(2);
		System.out.println("d2.compareTo(d1):"+d2.compareTo(d1));
	}
}
