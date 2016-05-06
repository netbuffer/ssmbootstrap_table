package cn.com.ttblog.ssmbootstrap_table;

import org.joda.time.DateTime;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.ttblog.ssmbootstrap_table.util.JodaTimeUtil;

public class TestJodaTime {
	Logger log = LoggerFactory.getLogger(this.getClass());
	private final static  String FORMAT="yyyy-MM-dd";
	
	@Test
	public void test(){
		DateTime d=new DateTime();
		System.out.println("aa:"+d.getMonthOfYear());
	}

	@Test
	public void testWeekStart(){
		log.debug("本周开始时间:{}",JodaTimeUtil.getWeekStart(new DateTime()).toString(FORMAT));
	}
	@Test
	public void testWeekEnd(){
		log.debug("本周结束时间:{}",JodaTimeUtil.getWeekEnd(new DateTime()).toString(FORMAT));
	}
	@Test
	public void testMonthStart(){
		log.debug("本月开始时间:{}",JodaTimeUtil.getMonthStart(new DateTime()).toString(FORMAT));
	}
	@Test
	public void testMonthEnd(){
		log.debug("本月结束时间:{}",JodaTimeUtil.getMonthEnd(new DateTime()).toString(FORMAT));
	}
	@Test
	public void testSeasonStart(){
		log.debug("本季度开始时间:{}",JodaTimeUtil.getSeasonStart(new DateTime()).toString(FORMAT));
	}
	@Test
	public void testSeasonEnd(){
		log.debug("本季度结束时间:{}",JodaTimeUtil.getSeasonEnd(new DateTime()).toString(FORMAT));
	}
}
