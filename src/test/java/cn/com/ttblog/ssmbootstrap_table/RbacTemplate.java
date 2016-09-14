package cn.com.ttblog.ssmbootstrap_table;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import cn.com.ttblog.ssmbootstrap_table.model.Permission;
import cn.com.ttblog.ssmbootstrap_table.service.IPermissionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml" })
public class RbacTemplate {

	private static Logger logger = LoggerFactory
			.getLogger(RbacTemplate.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IPermissionService permissionService;
	
	@Test
	public void testAddPermission(){
		Permission p=new Permission();
		p.setName("user");
		p.setUrl("/user/add");
		p.setPermission("user:add");
		p.setParentPermission("user");
		permissionService.addPermission(p);
	}
}
