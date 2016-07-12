package cn.com.ttblog.ssmbootstrap_table.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.com.ttblog.ssmbootstrap_table.model.User;
import cn.com.ttblog.ssmbootstrap_table.service.IUserService;

/**
 * jsonp测试
 */
@Controller
@RequestMapping("/jsonp")
@SessionAttributes("name")
public class JsonpController {

	@Resource
	private IUserService userService;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 被@ModelAttribute注释的方法会在此controller每个方法执行前被执行
	 */
	@ModelAttribute
	public void testModelAttr(HttpSession session, Model model) {
		JSONObject j = new JSONObject();
		if (session.getAttribute(ConfigConstant.ISLOGIN) != null) {
			if (Boolean.parseBoolean(session.getAttribute(
					ConfigConstant.ISLOGIN).toString())) {
				j.put("msg", "^true");
				logger.debug("^true");
			} else {
				j.put("msg", "^false");
				logger.debug("^false");
			}
		} else {
			logger.debug("^null");
			j.put("msg", "^null");
		}
		model.addAttribute("j", j);
	}

	/**
	 * 手动构建jsonp支持
	 * 前端使用jquery调用demo $.getJSON(
	 * "http://localhost:8080/ssmbootstrap_table/jsonp/test/1?callback=?"
	 * ,function(data){ $("body").append(data.name); console.log(data); });
	 * 
	 * 
	 * $.ajax({ type: "GET",
	 * url:"http://localhost:8080/ssmbootstrap_table/jsonp/test/1", dataType:
	 * "jsonp", jsonp: "callback", success: function(data) { console.log(data);
	 * }, error: function(jqXHR){ console.log(jqXHR); }, });
	 * 
	 * 
	 * @param id
	 * @param callback
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/test/{id}")
	public @ResponseBody String test(@PathVariable Long id,
			@RequestParam String callback, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		logger.debug("model:{}",model);
		response.setContentType("application/javascript");
		response.setCharacterEncoding("utf-8");
		logger.info("request:{}", ToStringBuilder.reflectionToString(request));
		logger.info("id:{}", id);
		String result = callback + "("
				+ JSON.toJSONString(userService.getUserById(id)) + ")";
		logger.debug("返回结果:{}", result);
		return result;
	}
	
	@RequestMapping("/t/{id}")
	public void t(@PathVariable Long id, @RequestParam String callback,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		response.setContentType("application/javascript");
		response.setCharacterEncoding("utf-8");
		logger.info("request:{}", ToStringBuilder.reflectionToString(request));
		logger.info("id:{}", id);
		String result = callback + "("
				+ JSON.toJSONString(userService.getUserById(id)) + ")";
		logger.debug("返回结果:{}", result);
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
			logger.debug("发生错误:{}", e);
		}
	}

	/**
	 * 使用spring-jsonp-support库 jsonp调用
	 * $.ajax({ 
			url:"http://localhost:8080/ssmbootstrap_table/tj/3.json", 
			dataType:"jsonp", 
			jsonp: "callback", 
			success: function(data) {
				console.log(data);
			}, 
			error: function(jqXHR){ 
				console.log(jqXHR); 
			}
		});
	 */
	@RequestMapping("/tj/{id}")
	public User t(@PathVariable Long id, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return userService.getUserById(id);
	}

	@RequestMapping("/str")
	public @ResponseBody String str() {
		return "你好";
	}

	/**
	 * http://localhost:8080/ssmbootstrap_table/jsonp/testarr?v[]=1&v[]=2&v[]=3
	 * var arr=["a","b","c"]; 前端jquery发送数组，需要traditional置为 true $.ajax({
	 * url:"http://localhost:8080/ssmbootstrap_table/jsonp/testarr",
	 * data:{"v":arr}, type:"post", traditional: true, success:function(data){
	 * console.log(data); }, error:function(xhr){ console.log(xhr); } });
	 * 
	 * @param values
	 * @return
	 */
	@RequestMapping("/testarr")
	public @ResponseBody String[] testarr(
			@RequestParam(value = "v") String[] values, Model model) {
		logger.debug("接收到的数组参数:{}", Arrays.deepToString(values));
		return values;
	}
	
	/**
	 * 获取session中name的值
	 * @param model
	 * @return
	 */
	@RequestMapping("/getSessionAttr")
	public @ResponseBody String getSessionAttr(@ModelAttribute("name")String name,HttpSession session) {
		logger.debug("name:{}",name);
		return session.getAttribute("name").toString();
	}
}