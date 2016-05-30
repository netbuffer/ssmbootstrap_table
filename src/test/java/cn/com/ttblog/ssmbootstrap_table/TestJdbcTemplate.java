package cn.com.ttblog.ssmbootstrap_table;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mysql.fabric.xmlrpc.base.Array;

import cn.com.ttblog.ssmbootstrap_table.dao.IMenuDao;
import cn.com.ttblog.ssmbootstrap_table.dao.IUserDao;
import cn.com.ttblog.ssmbootstrap_table.model.User;
import cn.com.ttblog.ssmbootstrap_table.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
// 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-context.xml" })
public class TestJdbcTemplate {

	private static Logger logger = LoggerFactory
			.getLogger(TestJdbcTemplate.class);
	@Resource
	private IUserService userService;
	@Resource
	private IUserDao userDao;
	@Resource
	private IMenuDao menuDao;
	// @Before
	// public void before() {
	// ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	// userService = (IUserService) ac.getBean("userService");
	// }
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	@Ignore
	public void test() {
		logger.debug("select now:{}",
				jdbcTemplate.queryForObject("select now()", String.class));
	}

	@Test
	@Ignore
	public void testAddUser() {
		User u = new User();
		u.setName("新添加测试");
		u.setSex("男");
		u.setAge(29);
		u.setPhone("13823883883");
		u.setDeliveryaddress("这是收获地址的测试");
		u.setAdddate((int) ((System.currentTimeMillis() / 1000)));
		logger.debug("insert status:{}", userDao.insert(u));
		logger.debug("insert null status:{}", userDao.insert(new User()));
	}
	
	@Test
	@Ignore
	public void testGetUserById() {
		logger.debug("获取user:{}", userDao.getUserById(1L));
	}
	
	@Test
	@Ignore
	public void testGetUserByIds() {
		List<Long> ids=new ArrayList<Long>();
		ids.add(1L);
		ids.add(2L);
		ids.add(65L);
		logger.debug("获取user集合:{}", userDao.getUsersByIds(ids));
	}
	
	@Test
	public void testGetUserList() {
		logger.debug("获取userlist:{}", userService.getUserList("desc", 10, 0));
	}
}
