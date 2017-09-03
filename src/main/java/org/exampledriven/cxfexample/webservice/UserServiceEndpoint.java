package org.exampledriven.cxfexample.webservice;

import cn.com.ttblog.ssmbootstrap_table.model.User;
import cn.com.ttblog.ssmbootstrap_table.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.jws.WebService;

/**
 * cxf帮助文档 http://cxf.apache.org/docs/index.html
 * http://cxf.apache.org/download.html
 * 测试 src\test\java\cn\com\ttblog\ssmbootstrap_table\TestWebService.java
 */
@WebService(portName = "SoapPort", serviceName = "userService", targetNamespace = "http://ttblog.com/ssmsoap",
		endpointInterface = "org.exampledriven.cxfexample.webservice.UserServiceWebService")
public class UserServiceEndpoint implements UserServiceWebService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceEndpoint.class);

	@Resource
	private IUserService userService;

	@Override
	public User findById(Long id) {
		User user=userService.getUserById(id);
		LOGGER.info("webservice调用findById:{}->{}",id,user);
		return user;
	}

	@Override
	public User updateUser(User user) {
		LOGGER.info("webservice调用updateUser:{}",user);
		return userService.updateUser(user);
	}
}