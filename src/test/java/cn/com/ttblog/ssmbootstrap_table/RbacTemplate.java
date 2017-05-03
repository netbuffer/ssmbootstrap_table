package cn.com.ttblog.ssmbootstrap_table;

import cn.com.ttblog.ssmbootstrap_table.model.User;
import cn.com.ttblog.ssmbootstrap_table.service.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.ttblog.ssmbootstrap_table.model.Permission;
import cn.com.ttblog.ssmbootstrap_table.model.Role;
import cn.com.ttblog.ssmbootstrap_table.model.RolePermission;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml" })
public class RbacTemplate {

	private static Logger logger = LoggerFactory
			.getLogger(RbacTemplate.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IPermissionService permissionService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IUserRoleService userRoleService;
	@Autowired
	private IRolePermissionService rolePermissionService;
	@Resource
	private IUserService userService;

	@Test
	public void testAddUser() {
		//添加超级管理员
		User u = new User();
		u.setName("admin");
		u.setPassword("admin");
		u.setSex("男");
		u.setAge(29);
		u.setPhone("13823883883");
		u.setDeliveryaddress("这是收获地址的测试");
		u.setAdddate((int) ((System.currentTimeMillis() / 1000)));
		userService.addUser(u);

//		User u = new User();
//		u.setName("test");
//		u.setPassword("test");
//		u.setSex("男");
//		u.setAge(29);
//		u.setPhone("13823883883");
//		u.setDeliveryaddress("这是收获地址的测试");
//		u.setAdddate((int) ((System.currentTimeMillis() / 1000)));
//		userService.addUser(u);
		logger.debug("添加用户:{}",u);
	}

	@Test
	public void testAddPermission(){
		Permission p=new Permission();
		p.setName("user");
		p.setUrl("/user/add");
		p.setPermission("user:add");
		p.setParentPermission("user");
		permissionService.addPermission(p);
	}
	
	@Test
	public void testlistPermissions(){
		permissionService.listPermissions();
	}
	
	@Test
	public void testAddRole(){
		Role r=new Role();
		r.setName("user");
		r.setDescription("普通用户角色");
		roleService.addRole(r);
	}
	
	@Test
	public void listRoles(){
		roleService.listRoles();
	}
	
	@Test
	public void addRolePermission(){
		RolePermission rp=new RolePermission();
		rp.setPermissionId(1);
		rp.setRoleId(2);
		rolePermissionService.addRolePermission(rp);
	}
}
