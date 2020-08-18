package cn.netbuffer.ssmbootstrap_table;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.netbuffer.ssmbootstrap_table.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-context.xml","classpath:spring/spring-mybatis.xml" })
public class TestMyBatisCache {

	private static Logger logger = LoggerFactory.getLogger(TestMyBatisCache.class);
	@Resource
	private IUserService userService;
	@Resource
	private SqlSession sqlSession;
	
	@Test
	public void testCache() throws InterruptedException{
		logger.debug("first query");
		userService.getUserById(1);
		logger.debug("second query");
		TimeUnit.SECONDS.sleep(3);
		userService.getUserById(1);
		logger.debug("third query");
		userService.getUserById(1);
	}
}
