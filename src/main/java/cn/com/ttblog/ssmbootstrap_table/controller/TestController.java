package cn.com.ttblog.ssmbootstrap_table.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.alibaba.fastjson.JSONObject;

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
	
	@RequestMapping(value = { "", "/{id}", "/index/{id}" })
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
	
	@RequestMapping(value="/form",method=RequestMethod.GET)
	public String getform(){
		logger.debug("test get form ");
		return "user/add";
	}
	
	@RequestMapping(value="/form",method=RequestMethod.POST)
	public String postform(User u){
		logger.debug("test post form:{}",u);
		return "redirect:/register-success.html";
	}
}
