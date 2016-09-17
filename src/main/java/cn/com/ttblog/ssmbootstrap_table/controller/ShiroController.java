package cn.com.ttblog.ssmbootstrap_table.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/shiro")
@Controller
public class ShiroController {

	private static final Logger LOG = LoggerFactory.getLogger(ShiroController.class);
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	@ResponseBody
	public Object index(){
		LOG.debug("shiro-index");
		return "shiro方法测试";
	}
	
	@RequestMapping(value="/getPrincipal",method=RequestMethod.GET)
	@ResponseBody
	public Object getPrincipal(){
		Subject subject=SecurityUtils.getSubject();
		Object p=subject.getPrincipal();
		LOG.debug("getPrincipal:{}",p);
//		Subject subject=SecurityUtils.getSubject();
		return p;
	}
	
}