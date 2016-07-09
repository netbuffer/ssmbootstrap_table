package cn.com.ttblog.ssmbootstrap_table;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;
import cn.com.ttblog.ssmbootstrap_table.model.User;

@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:spring/spring-context.xml","classpath:spring/spring-mvc.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestSpringProfile {
	private static final Logger log = LoggerFactory
			.getLogger(TestSpringProfile.class);
	@Autowired
	protected WebApplicationContext wac;

	@Before
	public void before() {
		log.warn("set spring.profiles.active->production");
//		System.setProperty("spring.profiles.active", "production");
		((GenericApplicationContext)wac).getEnvironment().setActiveProfiles("production");
	}

	@Test
	public void test() {
		log.info("getStartupDate：{}", new DateTime(wac.getStartupDate())
				.toString("yyyy-MM-dd HH:mm:ss"));
		log.info("getEnvironment：{}", wac.getEnvironment());
		log.info("userFromSpring：{}", wac.getBean("userFromSpring", User.class));
	}

}