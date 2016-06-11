//package cn.com.ttblog.ssmbootstrap_table.controller;
//
//import io.swagger.annotations.ApiOperation;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
//import javax.annotation.Resource;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.alibaba.fastjson.JSONObject;
//
//@Controller
//@RequestMapping("/test")
//public class TestController {
//	private Logger logger = LoggerFactory.getLogger(this.getClass());
//	
//	@Resource
//	private Properties configProperties;
//	@Value("#{configProperties['url2']}")
//	private String url;
//	@Value("#{configProperties['mysql.connectTime']}")
//	private Integer connectTime;
//	
//	@RequestMapping(value = { "", "/{id}", "/index/{id}" })
//	public String index(@PathVariable("id") int id,ModelMap m) {
//		logger.debug("template id:{}", id);
//		m.addAttribute("uri", id);
//		return "test";
//	}
//	
//	@RequestMapping(value = {"/getconfig"})
//	public Object getConfig(){
//		return JSONObject.toJSON(configProperties);
//	}
//	
//	@RequestMapping(value = {"/geturl"})
//	public @ResponseBody String getUrl(){
//		logger.debug("url:{}",url);
//		return url;
//	}
//	
//	@RequestMapping(value = {"/getConnectTime"})
//	public @ResponseBody Integer getConnectTime(){
//		logger.debug("connectTime:{}",connectTime);
//		return connectTime;
//	}
//	
//	/**
//	 * 直接写响应，会使用FastJsonHttpMessageConverter作json转换
//	 * 访问路径会直接以json输出
//	 * @return
//	 */
//	@RequestMapping(value = {"/getobj"})
//	public  @ResponseBody  Map<String, Object> getobj(){
//		Map<String, Object> res=new HashMap<String, Object>();
//		res.put("key1", "v1");
//		res.put("key2", "v2");
//		return res;
//	}
//	
//
//	  @ResponseBody
//	  @ApiOperation(value = "summary", httpMethod = "GET", position = 1, notes = "some detail on the endpoint")
//	  @RequestMapping(value = "/position1", method = RequestMethod.GET)
//	  public String position2() {
//	    return "";
//	  }
//
//	  @ResponseBody
//	  @ApiOperation(value = "summary", position = 0, httpMethod = "GET", notes = "some detail on the endpoint")
//	  @RequestMapping(value = "/position0", method = RequestMethod.GET)
//	  public String position1() {
//	    return "";
//	  }
//
//	  @ResponseBody
//	  @RequestMapping(value = "/springMediaTypes", method = RequestMethod.GET,
//	          produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
//	  public String springProducesMediaTypes() {
//	    return "";
//	  }
//
//	  @ResponseBody
//	  @ApiOperation(value = "/swaggerMediaTypes", produces = "application/xml, application/json, application/pdf",
//	          consumes = "application/xml, application/json, application/pdf ")
//	  @RequestMapping(value = "/swaggerMediaTypes", method = RequestMethod.GET,
//	          produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
//	  public String swaggerMediaTypes() {
//	    return "";
//	  }
//
//	  @ResponseBody
//	  @RequestMapping("/allHttpMethods")
//	  public void allHttpMethods() {
//
//	  }
//
//	  @RequestMapping(value = "/upload", method = RequestMethod.POST)
//	  public
//	  @ResponseBody
//	  String handleFileUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
//	    return "{\"message\": \"success\"}";
//	  }
//}
