package cn.com.ttblog.ssmbootstrap_table.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;

import cn.com.ttblog.ssmbootstrap_table.annotation.Token;
import cn.com.ttblog.ssmbootstrap_table.model.User;

@Controller
@RequestMapping("/test")
@SessionAttributes("name")
public class TestController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private Properties configProperties;
	@Value("#{configProperties['url2']}")
	private String url;
	@Value("#{configProperties['mysql.connectTime']}")
	private Integer connectTime;
	@Autowired
	private CookieLocaleResolver cookieResolver;
	//注入静态属性值
	private static String  JDBCURL;
	//注入方法
	@Value("#{configProperties['url']}")
    public void setJdbcUrl(String url) {
		JDBCURL = url;
    }
	
	@RequestMapping(value = {"/getJdbcUrl" })
	public @ResponseBody String getJdbcUrl() {
		logger.debug("静态属性值:{}",JDBCURL);
		return JDBCURL;
	}
	
	@RequestMapping(value = {"/{id}", "/index/{id}" })
	public String index(@PathVariable("id") int id, ModelMap m) {
		logger.debug("template id:{}", id);
		m.addAttribute("uri", id);
		m.addAttribute("showTime", System.currentTimeMillis() / 1000);
		m.addAttribute("test",id);
		return "test";
	}

	@RequestMapping(value = { "/getconfig" })
	public Object getConfig() {
		return JSONObject.toJSON(configProperties);
	}

	@RequestMapping(value = { "/geturl" })
	public @ResponseBody String getUrl() {
		logger.debug("url:{}", url);
		return url;
	}

	@RequestMapping(value = { "/getConnectTime" })
	public @ResponseBody Integer getConnectTime() {
		logger.debug("connectTime:{}", connectTime);
		return connectTime;
	}

	/**
	 * 直接写响应，会使用FastJsonHttpMessageConverter作json转换 访问路径会直接以json输出
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/getobj" })
	public @ResponseBody Map<String, Object> getobj() {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("key1", "v1");
		res.put("key2", "v2");
		return res;
	}

	@RequestMapping(value = { "/setSessionAttr" })
	public @ResponseBody String setSessionAttr(ModelMap m) {
		logger.debug("setSessionAttr");
		// 会被放到session中
		m.put("name", "this is name's value");
		return "success";
	}

	/**
	 * 返回本地化信息
	 * 
	 * @param locale
	 * @return
	 * @author genie
	 * @date 2016年6月13日 下午1:54:11
	 */
	@RequestMapping(value = { "/locale" })
	public @ResponseBody String locale(Locale locale) {
		logger.debug("locale:{}", locale);
		return locale.toString();
	}

	@RequestMapping("/lang")
	@ResponseBody
	public String lang(@RequestParam(defaultValue="zh",required=false,value="lang")String langType, HttpServletRequest request, HttpServletResponse response) {
		if (langType.equals("zh")) {
			Locale locale = new Locale("zh", "CN");
			// request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,
			// locale);
			cookieResolver.setLocale(request, response, locale);
		} else if (langType.equals("en")) {
			Locale locale = new Locale("en", "US");
			// request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,
			// locale);
			cookieResolver.setLocale(request, response, locale);
		} else
			cookieResolver.setLocale(request, response, LocaleContextHolder.getLocale());
		// request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,
		// LocaleContextHolder.getLocale());
		return langType;
	}
	
	@Token(save=true,tokenname="testformtoken")
	@RequestMapping(value="/form",method=RequestMethod.GET)
	public String getform(){
		logger.debug("test get form ");
		return "user/add";
	}
	
	@Token(remove=true,tokenname="testformtoken")
	@RequestMapping(value="/form",method=RequestMethod.POST)
	public String postform(User u){
		logger.debug("test post form:{}",u);
//		return "redirect:/register-success.html";
		//forward请求导致表单重复提交问题
		return "user/success";
	}
	
	/**
	 * 直接返回json数据 ,produces={"application/json"}
	 */
	@RequestMapping(value={"/uri"},method=RequestMethod.GET,headers={"Accept=application/json"})
	public JSONObject uri(HttpServletRequest request){
		JSONObject j=new JSONObject();
		j.put("request.getRequestURI", request.getRequestURI());
		j.put("request.getRequestURI().split(\"/\")", Arrays.deepToString(request.getRequestURI().split("/")));
		j.put("request.getRequestURL", request.getRequestURL());
		j.put("request.getServletContext().getContextPath", request.getServletContext().getContextPath());
		j.put("request.getServletContext().getRealPath(\"/\")", request.getServletContext().getRealPath("/"));
		return j;
	}
	
	/**
	 * 重定向拼接参数跳转
	 * @return
	 */
	@RequestMapping(value={"/redirect"})
	public String redirect(ModelMap m){
		//spring自动做了参数拼接
		logger.debug("redirect");
		m.put("param", "this is parameter");
		return "redirect:/test/1";
	}
	
	@RequestMapping(value={"/redirect2"})
	public String redirect2(RedirectAttributes attributes){
		logger.debug("redirect2");
		attributes.addAttribute("param", "this is parameter");
		return "redirect:/test/1";
	}
	
	@RequestMapping(value={"/error"})
	public String error(ModelMap m){
		logger.debug("test error");
//		int i=1/0;
		try{
			throw new RuntimeException("test");
		}catch(Exception ex){
			m.put("ex",ExceptionUtils.getStackTrace(ex));
		}
		
		return "error";
	}
	
	@ResponseStatus(reason="test",value=HttpStatus.NO_CONTENT)
	@RequestMapping(value={"/status"})
	public String status(){
		logger.debug("status");
		return "error";
	}
}
