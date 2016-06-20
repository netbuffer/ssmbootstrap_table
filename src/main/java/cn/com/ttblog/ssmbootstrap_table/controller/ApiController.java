package cn.com.ttblog.ssmbootstrap_table.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.com.ttblog.ssmbootstrap_table.model.User;
import cn.com.ttblog.ssmbootstrap_table.service.IUserService;

/**
 * Restful Controller, 直接输出内容，不调用template引擎
 * @package cn.com.ttblog.ssmbootstrap_table.controller
 * @author netbuffer
 */
@RestController
@RequestMapping("/restapi")
public class ApiController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = { "", "/{id}", "/index/{id}" },method=RequestMethod.GET)
	public User index(@PathVariable("id") int id) {
		logger.debug("restapi get user:",id);
		return userService.getUserById(id);
	}

}
