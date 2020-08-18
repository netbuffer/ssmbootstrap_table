package cn.netbuffer.ssmbootstrap_table;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.netbuffer.ssmbootstrap_table.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-context.xml"})
public class TestSlf4j {
	
	private static final Logger logger=LoggerFactory.getLogger(TestSlf4j.class);
	
	@Test
	public void testDebug(){
		User u=new User("test", "nan", 22, "", "", (int)(System.currentTimeMillis()/1000),"bei", null,null);
		logger.debug("u:{}",u);
	}
	
	@Test
	public void testInfo(){
		String str=Base64.encodeBase64URLSafeString("test".getBytes());
		logger.info("base64encode test:{}",str);
		logger.info("base64decode:{}",new String(Base64.decodeBase64(str)));
		logger.info("base64decode_:{}",new String(Base64.decodeBase64("_")).toString());
		logger.info("encodeBase64URLSafeString:{}",Base64.encodeBase64URLSafeString("/19891406/order/book/201?startDate=2016-07-04&a=3&b=2".getBytes()));
		logger.info("encodeBase64String:{}",Base64.encodeBase64String("/19891406/order/book/201?startDate=2016-07-04&a=3&b=2".getBytes()));
	}
}
 