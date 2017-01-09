package cn.com.ttblog.ssmbootstrap_table.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.async.DeferredResult;

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
	
	//http://mvc.linesh.tw/publish/21-3/4-asynchronous-request-processing.html
	/**
	 * 通过Spring MVC所管理的线程来产生返回值。与此同时，Servlet容器的主线程则可以退出并释放其资源了，同时也允许容器去处理其他的请求。
	 * 通过一个TaskExecutor，Spring
	 * MVC可以在另外的线程中调用Callable。当Callable返回时，请求再携带Callable返回的值
	 * ，再次被分配到Servlet容器中恢复处理流程
	 * @param str
	 * @return
	 */
	@RequestMapping(value = "/asyncc", method = RequestMethod.GET)
	@ResponseBody
	public Callable<String> asyncc(final String str) {
		logger.debug("async test");
		return new Callable<String>() {
			public String call() throws Exception {
				logger.debug("async thread");
				TimeUnit.SECONDS.sleep(10);
				return "hello:" + str;
			}
		};
	}

	@RequestMapping(value = "/asyncd", method = RequestMethod.GET)
	@ResponseBody
	public DeferredResult<String> asyncd(final String str) {
		DeferredResult<String> dr = new DeferredResult<String>();
		dr.onCompletion(new Runnable() {
			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				logger.debug("run with:{}",str);
			}
		});
		return dr;
	}

	/**
	 * 手动构建jsonp支持 前端使用jquery调用demo $.getJSON(
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
		logger.debug("model:{}", model);
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
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
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
	 * 使用spring-jsonp-support库 jsonp调用 $.ajax({
	 * url:"http://localhost:8080/ssmbootstrap_table/tj/3.json",
	 * dataType:"jsonp", jsonp: "callback", success: function(data) {
	 * console.log(data); }, error: function(jqXHR){ console.log(jqXHR); } });
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
	 * var arr=["a","b","c"]; 前端jquery发送数组，需要traditional置为 true 
	 * $.ajax({
	 * 		url:"http://localhost:8080/ssmbootstrap_table/jsonp/testarr",
	 * 		data:{"v":arr}, 
	 * 		type:"post", 
	 * 		traditional: true, 
	 * 		success:function(data){
	 * 			console.log(data); 
	 * 		}, 
	 * 		error:function(xhr){ 
	 * 			console.log(xhr); 
	 * 		} 
	 * });
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
	    var d={"id":1,name:"test"};
		$.ajax({
	 		url:"http://localhost:8080/ssmbootstrap_table/jsonp/receivejson",
	 		data:{"json":JSON.stringify(d)}, 
	 		type:"post", 
	 		ContentType: "application/json;charset=UTF-8", 
	 		success:function(data){
	 			console.log(data); 
	 		}, 
	 		error:function(xhr){ 
	 			console.log(xhr); 
	 		} 
		 });
		 get to http://localhost:8080/ssmbootstrap_table/jsonp/receivejson?json={%22id%22:1,name:%22test%22}
	 * 
	 * @param values
	 * @return
	 */
	@RequestMapping("/receivejson")
	public @ResponseBody Object receivejson(@RequestParam(value="json") String json) {
		logger.debug("receive json str:{}",json);
		JSONObject j=(JSONObject) JSONObject.parse(json);
		logger.debug("receive json to jsonobject:{}",j);
		return j;
	}

	/**
	    $.ajax({
		  url:"http://localhost:8080/ssmbootstrap_table/jsonp/receivejsonobj",
		  type:"post", 
		  contentType:'application/json;charset=UTF-8',
		  data: "{id:1,name:\"test\"}", 
		  success:function(data){
		    console.log(data); 
		  }, 
		  error:function(xhr){ 
		    console.log(xhr); 
		  } 
		});
	 * 
	 * @param values
	 * @return
	 */
	@RequestMapping("/receivejsonobj")
	public @ResponseBody User receivejsonobj(@RequestBody User user) {
		logger.debug("receive json obj:{}",user);
		return user;
	}

	//接收JSONObject
	@RequestMapping("/receiveJSONObject")
	public @ResponseBody
	Map receivejsonobj(@RequestBody JSONObject user,HttpServletRequest request) {
		logger.debug("receiveJSONObject:{}",user);
		return user;

	}

	//接收Map
	@RequestMapping("/receiveMap")
	public @ResponseBody
	Map receivejsonobj(@RequestBody Map user,HttpServletRequest request) {
		logger.debug("receiveMap:{}",user);
		return user;

	}

	/**
	 * 获取session中name的值
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/getSessionAttr")
	public @ResponseBody String getSessionAttr(
			@ModelAttribute("name") String name, HttpSession session) {
		logger.debug("name:{}", name);
		return session.getAttribute("name").toString();
	}
}