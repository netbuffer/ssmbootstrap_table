package cn.com.ttblog.ssmbootstrap_table.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import cn.com.ttblog.ssmbootstrap_table.model.User;
import cn.com.ttblog.ssmbootstrap_table.model.query.QueryUser;
import cn.com.ttblog.ssmbootstrap_table.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private IUserService userService;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/showUser/{id}")
	public String toIndex(@PathVariable("id") int id,
			HttpServletRequest request, Model model) {
		logger.info("进入:{},参数:{}", request.getRequestURI(), model.toString());
		int userId = id;
		User user = userService.getUserById(userId);
		model.addAttribute("user", user);
		return "su";
	}

	@RequestMapping("/testmodel")
	public ModelAndView model() {
		ModelAndView mav = new ModelAndView();
		User u = new User();
		u.setName("tianyu");
		u.setAge(11);
		u.setDeliveryaddress("收货地址");
		mav.addObject("model", u);
		return mav;
	}

	/**
	 * http://localhost:8080/ssmbootstrap_table/user/userlist?order=asc&limit=10
	 * &offset=0
	 * 
	 * @param order
	 * @param limit
	 * @param offset
	 * @param model
	 * @return
	 */
	@RequestMapping("/userlist")
	public String userlist(@RequestParam(value="search",required=false)String search,String order, int limit, int offset, Model model) {
		long startTime = System.nanoTime();
		logger.info("参数:{},{},{}", order, limit, offset);
		if(search!=null){
			try {
				//get参数乱码问题:http://luanxiyuan.iteye.com/blog/1849169
				search=new String(search.getBytes("ISO-8859-1"), "UTF-8");
				logger.info("查询参数:{}", search);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
		}
		List<User> users =search==null? userService.getUserList(order, limit, offset): userService.getUserList(search,order, limit, offset);
		long total = userService.getUserListCount();
		Map<String, Object> params = new HashMap<String, Object>();
		model.addAttribute("total", total);
		model.addAttribute("rows", users);
		logger.info("结果:{}", params);
		long estimatedTime = System.nanoTime() - startTime;
		logger.debug("userlist execute with:{}",estimatedTime);
		return "userlist";
	}
	
	@RequestMapping("/userlistq")
	public String userlist(QueryUser query,ModelMap map) {
		logger.info("查询参数:{}", query);
		map.addAttribute("query", query);
		String querystr=JSON.toJSONString(query);
		map.addAttribute("querystr",querystr);
		logger.debug("querystr:{}",querystr);
		return "testquery";
	}

	@RequestMapping("/showUserXML")
	public ModelAndView showUserXML(HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView("xStreamMarshallingView");
		int userId = Integer.parseInt("1");
		User user = userService.getUserById(userId);
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/u/{id}", headers = "Accept=application/json")
	public @ResponseBody User getEmp(@PathVariable String id) {
		User e = userService.getUserById(1);
		return e;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/us", headers = "Accept=application/json")
	public @ResponseBody List<User> ListinggetAllEmp() {
		List<User> us = new ArrayList<User>();
		us.add(userService.getUserById(1));
		us.add(userService.getUserById(2));
		return us;
	}
}