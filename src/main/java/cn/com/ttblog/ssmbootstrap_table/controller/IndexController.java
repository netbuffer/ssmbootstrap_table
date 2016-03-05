package cn.com.ttblog.ssmbootstrap_table.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.ttblog.ssmbootstrap_table.model.User;
import cn.com.ttblog.ssmbootstrap_table.service.IUserService;
import cn.com.ttblog.ssmbootstrap_table.util.BeanMapUtil;
import cn.com.ttblog.ssmbootstrap_table.util.POIExcelUtil;

@Controller
@RequestMapping("/")
public class IndexController {

	@Resource
	private IUserService userService;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/login")
	public String login(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, String username, String password) {
		logger.info("进入username:{},pwd:{}", username, password);
		if (username.equals(ConfigConstant.VAL_USERNAME)
				&& password.equals(ConfigConstant.VAL_PWD)) {
			session.setAttribute(ConfigConstant.ISLOGIN, true);
			session.setAttribute(ConfigConstant.USERNAME, username);
			Cookie c = new Cookie(ConfigConstant.USERNAME, username);
			c.setMaxAge(86400);
			response.addCookie(c);
			return "redirect:/manage.html";
		} else {
			return "redirect:/index.html";
		}
	}

	@RequestMapping("/newdata")
	public String newdata(HttpSession session,Model model) {
		logger.info("执行前:{}",model);
		int newcount = userService.getNewData();
		String username = session.getAttribute(ConfigConstant.USERNAME)
				.toString();
		model.addAttribute("newcount", newcount);
		model.addAttribute("username", username);
		logger.info("执行后:{}",model);
		return "newdata";
	}

	@RequestMapping("/export")
	public ResponseEntity<byte[]>  export(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		List<User> users = userService.getUserList("desc", 10, 0);
		String projectPath = request.getServletContext().getRealPath("export")+File.separator;
		int userCount = users.size();
		List<Map<String, Object>> mps = new ArrayList<Map<String, Object>>(
				users.size());
		for (int i = 0; i < userCount; i++) {
			Map<String, Object> m = BeanMapUtil.transBean2Map(users.get(i));
			mps.add(m);
		}
		logger.info("users:{}", mps);
		List<String> titles = new ArrayList<String>(mps.get(0).size() - 1);
		titles.add("adddate");
		titles.add("age");
		titles.add("deliveryaddress");
		titles.add("id");
		titles.add("name");
		titles.add("phone");
		titles.add("sex");
		String file = projectPath + format.format(new Date()) + "."
				+ ConfigConstant.EXCELSTR;
		logger.info("文件路径:{}",file);
		POIExcelUtil.export(titles, mps, file);
		HttpHeaders headers = new HttpHeaders();  
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  
	    headers.setContentDispositionFormData("attachment",file.replace(projectPath, ""));  
	    try {
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(file)),  
			                                  headers, HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	    return null;
	}
}