package cn.com.ttblog.ssmbootstrap_table.controller;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jscookie.javacookie.Cookies;

@RestController
@RequestMapping("/cookie")
public class CookieController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/cookies")
	public Cookies cookies(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		Cookies cookies=Cookies.initFromServlet(request, response);
		logger.debug("cookies:{}",cookies.get());
		return cookies;
	}
	/**
	 * 添加httponly cookie
	 * @param name
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addh/{name}")
	public boolean addh(@PathVariable("name") String name,HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		Cookie c=new Cookie(name, name);
//		c.setComment("purpose");
		c.setHttpOnly(true);
		c.setMaxAge(3600);
//		c.setDomain("localhost");
//		c.setPath("/ssmbootstrap_table");
		response.addCookie(c);
		logger.debug("addh cookie:{}",ToStringBuilder.reflectionToString(c));
		return true;
	}
	

	@RequestMapping("/add/{name}")
	public boolean add(@PathVariable("name") String name,HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		Cookie c=new Cookie(name, name);
		c.setComment("purpose");
		c.setMaxAge(3600);
		response.addCookie(c);
		logger.debug("add cookie:{}",ToStringBuilder.reflectionToString(c));
		return true;
	}
	
	/**
	 * 删除http only cookie，必须保证和添加的cookie参数是一致的才能删除成功
	 * @param name
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/delh/{name}")
	public boolean delh(@PathVariable("name") String name,HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		Cookie[] cookies=request.getCookies();
		Cookie s=null;
		for(Cookie c:cookies){
			if(c.getName().equals(name)){
				s=c;
				break;
			}
		}
		logger.debug("delh cookie:{},cookie:{}",name,ToStringBuilder.reflectionToString(s));
		if(s!=null){
			//清除cookie        
			Cookie c=new Cookie(s.getName(), s.getValue());
//			c.setComment("purpose");
			c.setHttpOnly(true);
			c.setMaxAge(0);
//			c.setDomain("localhost");
//			c.setPath("/ssmbootstrap_table");
//			s.setValue("");
//			s.setMaxAge(0);
//			s.setHttpOnly(true);
			response.addCookie(c);
		}
		return true;
	}
	
	@RequestMapping("/del/{name}")
	public boolean del(@PathVariable("name") String name,HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		Cookie[] cookies=request.getCookies();
		Cookie s=null;
		for(Cookie c:cookies){
			if(c.getName().equals(name)){
				s=c;
				break;
			}
		}
		logger.debug("del cookie:{},cookie:{}",name,ToStringBuilder.reflectionToString(s));
		if(s!=null){
			//清除cookie        
			s.setValue("");
			s.setMaxAge(0);
			response.addCookie(s);
		}
		return true;
	}
	
	/**
	 * 测试清除tomcat中JSESSIONID
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/delsession")
	public boolean delJsessionid(HttpServletRequest request,
			HttpServletResponse response) {
		Cookie[] cookies=request.getCookies();
		Cookie s=null;
		for(Cookie c:cookies){
			if(c.getName().equals("JSESSIONID")){
				s=c;
				break;
			}
		}
		logger.debug("JSESSIONID:{}",ToStringBuilder.reflectionToString(s));
		if(s!=null){
			//清除cookie        
			s.setValue("");
			s.setMaxAge(0);
			s.setDomain("localhost");
			s.setPath("/ssmbootstrap_table/");
			s.setHttpOnly(true);
			response.addCookie(s);
		}
		return true;
	}

}