package cn.netbuffer.ssmbootstrap_table.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/route")
public class RouteController {
	
	private static final Logger LOG = LoggerFactory.getLogger(RouteController.class);
	
	//指定参数路径映射
	@RequestMapping(params="m=m")
	public @ResponseBody String m(HttpServletRequest request){
		LOG.debug("m=m");
		return "hello m";
	}
	
	@RequestMapping(params="m=m2")
	public @ResponseBody String m2(HttpServletRequest request){
		LOG.debug("m=m2");
		return "hello m2";
	}
}
