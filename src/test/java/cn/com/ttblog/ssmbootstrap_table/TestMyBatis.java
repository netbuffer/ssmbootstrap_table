package cn.com.ttblog.ssmbootstrap_table;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.ttblog.ssmbootstrap_table.dao.IMenuDao;
import cn.com.ttblog.ssmbootstrap_table.dao.IUserDao;
import cn.com.ttblog.ssmbootstrap_table.dao2.IUserDaoTest;
import cn.com.ttblog.ssmbootstrap_table.model.Menu;
import cn.com.ttblog.ssmbootstrap_table.model.User;
import cn.com.ttblog.ssmbootstrap_table.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
// 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring/spring-context.xml","classpath:spring/spring-mybatis.xml" })
public class TestMyBatis {

	private static Logger logger = LoggerFactory.getLogger(TestMyBatis.class);
	@Resource
	private IUserService userService;
	@Resource 
	private IUserDao userDao;
	@Resource
	private SqlSession sqlSession;
	@Resource
	private IUserDaoTest userDaoTest;
	@Resource
	private IMenuDao menuDao;
	// @Before
	// public void before() {
	// ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	// userService = (IUserService) ac.getBean("userService");
	// }
	
	@Test
	@Ignore
	public void testMenu(){
		Menu m=menuDao.getMenuById(1L);
		logger.debug("menu:{}",m);
		if(m.getMenus()!=null){
			logger.debug("submenu:{}",m.getMenus().get(0));
		}
	}
	
	@Test
	@Ignore
	public void test1() {

		// for(int i=0;i<10;i++){
		// final int index=i;
		// new Thread(new Runnable() {
		// @Override
		// public void run() {
		// System.out.println("执行了"+index);
		 User user = userService.getUserById(1);
		 logger.debug("user1:{}",user);
		 
		 User user2 = userDao.selectByPrimaryKey(2L);
		 logger.debug("user2:{}",user2);
		// logger.info(JSON.toJSONString(user));
		// }
		// }).start();
		// }
		// User user = userService.getUserById(1);
		// System.out.println("数据:"+user.getUserName());
		// logger.info(JSON.toJSONString(user));
//		for (int i = 0; i < 10; i++) {
//			System.out.println("\r\n" + new Random().nextInt(3));
//		}
	}

	@Test
	@Ignore
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
		u.setAdddate((int)(System.currentTimeMillis() / 1000));
		userService.addUser(u);
	}
	
	@Test
	@Ignore
	public void testTran(){
		userService.addUM();
	}
	
	@Test
	@Ignore
	public void testTrantest(){
		userService.addUMtest();
	}
	
	@Test
	@Ignore
	public void testSelectCache(){
		//mapper中需要配置<cache />节点，会开启缓存
		logger.debug("select1：{}",userService.getUserById(1));
		logger.debug("select2：{}",userService.getUserById(1));
	}
	
	@Test
	@Ignore
	public void testInterceptor(){
//		logger.debug(ToStringBuilder.reflectionToString(sqlSession.getConfiguration()));
//		logger.debug("测试拦截器:{}",sqlSession.selectOne("cn.com.ttblog.ssmbootstrap_table.dao.IUserDao.selectByPrimaryKey",1L));
		logger.debug("getdatasum:{}",userDao.getDataSum());
	}
	
	@Ignore
	@Test
	public void testDataSource2(){
		logger.debug("testDataSource2:{}",userDaoTest.getSum());
	}
	
	@Test
	@Ignore
	public void testForeach(){
		List<Long> ids=new ArrayList<Long>();
		ids.add(1L);
		ids.add(2L);
		ids.add(3L);
		logger.debug("id列表{},获取集合:{}",ids,userDao.getUsersByIds(ids));
	}
	
	@Test
	@Ignore
	public void testInsert(){
		//mybatis 开启BATCH处理，会导致insert返回值问题 http://www.cnblogs.com/seven7seven/p/4039622.html
		User u=new User();
		u.setName("ceshi");
		logger.debug("insert return:{}",sqlSession.insert("cn.com.ttblog.ssmbootstrap_table.dao.IUserDao.insert",u));
	}
	
	@Test
	@Ignore
	public void testInsertSelectKey(){
		User u=new User();
		String str=RandomStringUtils.randomAlphabetic(6);
		u.setName(str);
		logger.debug("test testInsertSelectKey:{}",str);
		logger.debug("insert return:{},插入记录的user id:{}",userDao.insert(u),u.getId());
	}
	
	@Test
	public void testselectWhen(){
		logger.debug("userDao.selectWhen(null):{}",userDao.selectWhen(null));
		logger.debug("userDao.selectWhen(2):{}",userDao.selectWhen(2));
	}
}
