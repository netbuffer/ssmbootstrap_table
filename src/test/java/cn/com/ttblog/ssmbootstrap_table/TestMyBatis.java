package cn.com.ttblog.ssmbootstrap_table;

import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.ibatis.session.SqlSession;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tk.mybatis.mapper.entity.Example;
import cn.com.ttblog.ssmbootstrap_table.dao.IUserDao;
import cn.com.ttblog.ssmbootstrap_table.model.User;
import cn.com.ttblog.ssmbootstrap_table.service.IUserService;
import cn.com.ttblog.ssmbootstrap_table.serviceimpl.UserServiceImpl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;

@RunWith(SpringJUnit4ClassRunner.class)
// 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-context.xml","classpath:spring-mybatis.xml" })
public class TestMyBatis {

	private static Logger logger = LoggerFactory.getLogger(TestMyBatis.class);
	@Resource
	private IUserService userService;
	@Resource 
	private IUserDao userDao;
	@Resource
	private SqlSession sqlSession;
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
//	@Ignore
	public void testAddUser() {
		for (int i = 0; i < 10; i++) {
			User u = new User();
			u.setAge(i + new Random().nextInt(1));
			u.setAdddate((int)(System.currentTimeMillis() / 1000));
			u.setName("用户:"+i);
			u.setDeliveryaddress("收货地址");
			u.setPhone("1324");
			u.setSex("男");
			userService.addUser(u);
		}
	}
	
	@Test
	@Ignore
	public void testdao(){
		logger.info("getnew:{}",userDao.getNewData());
	}
	
	@Test
	@Ignore
	public void testDatacount(){
		logger.info("datacount:{}",userService.getDataSum());
	}
	
	@Test
	@Ignore
	public void testAddUserTran(){
		User u=new User();
		u.setName("事务测试");
		u.setAge(10);
		u.setSex("男");
		u.setPhone("13833422322");
		u.setAdddate((int)System.currentTimeMillis()/1000);
		userService.addUser(u);
	}
	
	@Test
	@Ignore
	public void testInterceptor(){
//		logger.debug(ToStringBuilder.reflectionToString(sqlSession.getConfiguration()));
//		logger.debug("测试拦截器:{}",sqlSession.selectOne("cn.com.ttblog.ssmbootstrap_table.dao.IUserDao.selectByPrimaryKey",1L));
//		logger.debug("getdatasum:{}",userDao.getDataSum());
//		http://git.oschina.net/free/Mapper/blob/master/wiki/mapper3/5.Mappers.md通用接口文档
		Example ex=new Example(User.class);
		ex.orderBy("id").desc();
		logger.debug("getdatasum:{}",userDao.selectByExample(ex));
	}
	
	@Test
	@Ignore
	public void testPages(){
		PageHelper.startPage(1, 4);
		Example ex=new Example(User.class);
		ex.orderBy("id").desc();
		userDao.selectByExample(ex);
		PageHelper.startPage(2, 4);
		Example ex2=new Example(User.class);
		ex2.orderBy("id").desc();
		userDao.selectByExample(ex2);
	}
	
	@Test
	public void testSelectOne(){
		User u=new User();
		u.setId(1L);
//		u.setAge(23);
		logger.debug("queryone:{}",userDao.selectOne(u));
	}
}
