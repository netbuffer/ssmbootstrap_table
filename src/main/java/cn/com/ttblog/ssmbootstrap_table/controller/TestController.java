package cn.com.ttblog.ssmbootstrap_table.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = { "", "/{id}", "/index/{id}" })
	public String index(@PathVariable("id") int id,ModelMap m) {
		logger.debug("template id:{}", id);
		m.addAttribute("uri", id);
		return "test";
	}
}