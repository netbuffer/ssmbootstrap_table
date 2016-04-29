package com.other.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 同名bean指定别名
 */
@Controller(value="otherindex")
@RequestMapping("/other")
public class IndexController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("index")
	public ModelAndView index(){
		logger.debug("index...");
		ModelAndView mav=new ModelAndView("test");
		mav.addObject("test", "test");
		return mav;
	}

}