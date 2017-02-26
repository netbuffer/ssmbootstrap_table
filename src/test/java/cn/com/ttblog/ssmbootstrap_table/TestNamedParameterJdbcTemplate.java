package cn.com.ttblog.ssmbootstrap_table;

import cn.com.ttblog.ssmbootstrap_table.model.User;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml" })
public class TestNamedParameterJdbcTemplate {

	private static Logger logger = LoggerFactory
			.getLogger(TestNamedParameterJdbcTemplate.class);

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	/**
	 * 使用参数命名
	 */
	@Test
	public void testInsert() {
		String sql="insert into user(name,password,salt,sex,age,phone,deliveryaddress,adddate,islock) " +
				"values(:name,:password,:salt,:sex,:age,:phone,:deliveryaddress,:adddate,:islock)";
		Map param=new HashMap();
		param.put("name","java");
		param.put("password","aaa");
		param.put("salt","bbb");
		param.put("sex","男");
		param.put("age",22);
		param.put("phone","13023211234");
		param.put("deliveryaddress","shouhuo");
		param.put("adddate",(int)(System.currentTimeMillis()/1000));
		param.put("islock",1);
		logger.debug("执行:{},参数为:{}",sql,param);
		namedParameterJdbcTemplate.update(sql,param);
	}

	/**
	 * 使用bean参数命名
	 */
	@Test
	public void testInsertBean() {
		String sql="insert into user(name,password,salt,sex,age,phone,deliveryaddress,adddate,islock) " +
				"values(:name,:password,:salt,:sex,:age,:phone,:deliveryaddress,:adddate,:isLock)";
		User u = new User();
		u.setName("新添加测试");
		u.setSex("男");
		u.setAge(29);
		u.setPhone("13823883883");
		u.setDeliveryaddress("这是收获地址的测试");
		u.setAdddate((int) ((System.currentTimeMillis() / 1000)));
		u.setIsLock((short) 1);
		SqlParameterSource sqlParameterSource=new BeanPropertySqlParameterSource(u);
		logger.debug("执行:{},参数为:{}",sql,sqlParameterSource);
		namedParameterJdbcTemplate.update(sql,sqlParameterSource);
	}
}
