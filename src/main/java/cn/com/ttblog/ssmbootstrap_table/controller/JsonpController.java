package cn.com.ttblog.ssmbootstrap_table.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;

import cn.com.ttblog.ssmbootstrap_table.model.User;
import cn.com.ttblog.ssmbootstrap_table.service.IUserService;

/**
 * jsonp测试
 */
@Api(description="jsonp请求",value="jsonp测试",produces=MediaType.APPLICATION_JSON_VALUE)
@Controller
@RequestMapping("/jsonp")
public class JsonpController {

	@Resource
	private IUserService userService;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 前端使用jquery调用demo
	    $.getJSON("http://localhost:8080/ssmbootstrap_table/jsonp/test/1?callback=?",function(data){
		  $("body").append(data.name);
		  console.log(data);
		});
	 * 
	 * 
		$.ajax({ 
		  type: "GET", 	
		  url:"http://localhost:8080/ssmbootstrap_table/jsonp/test/1",
		  dataType: "jsonp",
		  jsonp: "callback",
		  success: function(data) {
		   console.log(data);
		  },
		  error: function(jqXHR){     
		    console.log(jqXHR);
		  },     
		});
	 * 
	 * 
	 * @param id
	 * @param callback
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/test/{id}")
	public @ResponseBody String test(@PathVariable Long id,@RequestParam String callback,HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("application/javascript");
		response.setCharacterEncoding("utf-8");
		logger.info("request:{}",ToStringBuilder.reflectionToString(request));
		logger.info("id:{}",id);
//		String result=callback+"("+JSON.toJSONString(userService.getUserById(id))+")";
//		logger.debug("返回结果:{}",result);
//		return result;
		return "";
	}
	
	@RequestMapping("/t/{id}")
	public void t(@PathVariable Long id,@RequestParam String callback,HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("application/javascript");
		response.setCharacterEncoding("utf-8");
		logger.info("request:{}",ToStringBuilder.reflectionToString(request));
		logger.info("id:{}",id);
//		String result=callback+"("+JSON.toJSONString(userService.getUserById(id))+")";
//		logger.debug("返回结果:{}",result);
//		try {
//			response.getWriter().write(result);
//		} catch (IOException e) {
//			e.printStackTrace();
//			logger.debug("发生错误:{}",e);
//		}
	}
	@ApiOperation(notes="获取用户信息", value = "获取用户信息")
	@RequestMapping("/tj/{id}")
	public User t(@PathVariable Long id,HttpServletRequest request,HttpServletResponse response) {
		return userService.getUserById(id);
	}
	
	@RequestMapping("/str")
	public@ResponseBody String str() {
		return "你好";
	}
	/**
	 * http://localhost:8080/ssmbootstrap_table/jsonp/testarr?v[]=1&v[]=2&v[]=3
	 * var arr=["a","b","c"];
	  前端jquery发送数组，需要traditional置为 true
	 $.ajax({
	   url:"http://localhost:8080/ssmbootstrap_table/jsonp/testarr",
	   data:{"v":arr},
	   type:"post",
	   traditional: true,
	   success:function(data){
	     console.log(data);
	   },
	   error:function(xhr){
	     console.log(xhr);
	   }
	 });
	 * @param values
	 * @return
	 */
	@RequestMapping("/testarr")
	public @ResponseBody String[] testarr(@RequestParam(value="v")String[] values) {
		logger.debug("接收到的数组参数:{}",Arrays.deepToString(values));
		return values;
	}
}