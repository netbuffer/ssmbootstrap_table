package cn.com.ttblog.ssmbootstrap_table;

import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.ttblog.ssmbootstrap_table.dao.IUserDao;
import cn.com.ttblog.ssmbootstrap_table.model.User;
import cn.com.ttblog.ssmbootstrap_table.service.IUserService;
import cn.com.ttblog.ssmbootstrap_table.serviceimpl.UserServiceImpl;

import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
// 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class TestMyBatis {

	private static Logger logger = LoggerFactory.getLogger(TestMyBatis.class);
	@Resource
	private IUserService userService;
	@Resource 
	private IUserDao userDao;

	// @Before
	// public void before() {
	// ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	// userService = (IUserService) ac.getBean("userService");
	// }

	@Test
	@Ignore
	public void test1() {

		// for(int i=0;i<10;i++){
		// final int index=i;
		// new Thread(new Runnable() {
		// @Override
		// public void run() {
		// System.out.println("执行了"+index);
		// User user = userService.getUserById(1);
		// System.out.println("数据:"+user.getUserName());
		// logger.info(JSON.toJSONString(user));
		// }
		// }).start();
		// }
		// User user = userService.getUserById(1);
		// System.out.println("数据:"+user.getUserName());
		// logger.info(JSON.toJSONString(user));
		for (int i = 0; i < 10; i++) {
			System.out.println("\r\n" + new Random().nextInt(3));
		}
	}

	@Test
	@Ignore
	public void testAddUser() {
		for (int i = 0; i < 10; i++) {
			User u = new User();
			u.setAge(i + new Random().nextInt(1));
			u.setAdddate((int)System.currentTimeMillis());
			u.setName("用户:"+i);
			u.setDeliveryaddress("收货地址");
			u.setPhone("1324");
			u.setSex("男");
			userService.addUser(u);
		}
	}
	
	@Test
	public void testdao(){
		logger.info("getnew:{}",userDao.getNewData());
	}

}
