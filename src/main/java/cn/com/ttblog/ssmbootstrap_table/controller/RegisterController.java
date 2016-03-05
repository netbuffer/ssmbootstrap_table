package cn.com.ttblog.ssmbootstrap_table.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.ttblog.ssmbootstrap_table.model.User;
import cn.com.ttblog.ssmbootstrap_table.service.IUserService;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Resource
	private IUserService userService;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value={"/index","/"})
	public String index() {
		return "redirect:/register.html";
	}

	@RequestMapping("/save")
	public String save(User user) {
		user.setAdddate((int)(System.currentTimeMillis()/1000));
		try {
			userService.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/register-error.html";
		}
		return "redirect:/register-success.html";
	}

}