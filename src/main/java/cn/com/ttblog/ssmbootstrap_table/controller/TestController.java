package cn.com.ttblog.ssmbootstrap_table.controller;

import java.util.Properties;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/test")
public class TestController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private Properties configProperties;
	@Value("#{configProperties['url2']}")
	private String url;
	@Value("#{configProperties['mysql.connectTime']}")
	private Integer connectTime;
	
	@RequestMapping(value = { "", "/{id}", "/index/{id}" })
	public String index(@PathVariable("id") int id,ModelMap m) {
		logger.debug("template id:{}", id);
		m.addAttribute("uri", id);
		return "test";
	}
	
	@RequestMapping(value = {"/getconfig"})
	public Object getConfig(){
		return JSONObject.toJSON(configProperties);
	}
	
	@RequestMapping(value = {"/geturl"})
	public @ResponseBody String getUrl(){
		logger.debug("url:{}",url);
		return url;
	}
	
	@RequestMapping(value = {"/getConnectTime"})
	public @ResponseBody Integer getConnectTime(){
		logger.debug("connectTime:{}",connectTime);
		return connectTime;
	}
	
}