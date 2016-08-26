package cn.com.ttblog.ssmbootstrap_table.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;

//import com.codahale.metrics.annotation.Timed;
import cn.com.ttblog.ssmbootstrap_table.event.LoginEvent;
import cn.com.ttblog.ssmbootstrap_table.model.User;
import cn.com.ttblog.ssmbootstrap_table.service.IUserService;
import cn.com.ttblog.ssmbootstrap_table.util.BeanMapUtil;
import cn.com.ttblog.ssmbootstrap_table.util.POIExcelUtil;

/**
 * index
 */
@Controller(value="mainindex")
@RequestMapping("/")
public class IndexController {
	public IndexController(){
		
	}
	@Resource
	private IUserService userService;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired  
    private ApplicationContext applicationContext;  
	
	@RequestMapping("/login")
	public String login(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, String username, String password,@RequestParam(value="requri",required=false) String requri) {
//		RequestContextUtils.getWebApplicationContext(request)
		logger.info("进入username:{},pwd:{},requri:{}", username, password,requri);
		if (username.equals(ConfigConstant.VAL_USERNAME)
				&& password.equals(ConfigConstant.VAL_PWD)) {
			session.setAttribute(ConfigConstant.ISLOGIN, true);
			session.setAttribute(ConfigConstant.USERNAME, username);
			Cookie c = new Cookie(ConfigConstant.USERNAME, username);
			c.setMaxAge(86400);
			response.addCookie(c);
			Map<String, String> param=new HashMap<String,String>();
			param.put("loginname", username);
			param.put("logintime", new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
			param.put("loginip", request.getRemoteAddr());
			applicationContext.publishEvent(new LoginEvent(param));  
			if(requri!=null&&requri.length()>0){
				String uri=new String(Base64.decodeBase64(requri));
				String touri=uri.substring(request.getContextPath().length()+1);
				logger.debug("request.getContextPath():{}  decode-requri:{}  touri:{}",request.getContextPath(),uri,touri);
//				/ssmbootstrap_table
//				/ssmbootstrap_table/test/form?null
				return "redirect:/"+touri;
			}
			return "redirect:/manage.html";
		} else {
			return "redirect:/index.html?requri="+requri;
		}
	}
	
	@RequestMapping("/demo")
	public String demolist() {
		logger.debug("demo");
		return "redirect:/demolist.html";
	}

	@RequestMapping("/exit")
	public String exit(HttpSession session,HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("用户{}退出系统",session.getAttribute(ConfigConstant.USERNAME));
		//删除cookie
		Cookie cookie = new Cookie(ConfigConstant.USERNAME, null); 
		cookie.setMaxAge(0);
		session.invalidate();
		logger.debug("exit c2:{}",cookie);
		response.addCookie(cookie);
		return "redirect:/index.html";
	}
	
	@RequestMapping("/newdata")
	public String newdata(HttpSession session, Model model) {
		DecimalFormat df = new DecimalFormat("0.00");
		// Display the total amount of memory in the Java virtual machine.
		long totalMem = Runtime.getRuntime().totalMemory() / 1024 / 1024;
		System.out.println(df.format(totalMem) + " MB");
		// Display the maximum amount of memory that the Java virtual machine
		// will attempt to use.
		long maxMem = Runtime.getRuntime().maxMemory() / 1024 / 1024;
		System.out.println(df.format(maxMem) + " MB");
		// Display the amount of free memory in the Java Virtual Machine.
		long freeMem = Runtime.getRuntime().freeMemory() / 1024 / 1024;
		System.out.println(df.format(freeMem) + " MB");
		logger.info("执行前:{}", model);
		int newcount = userService.getNewData();
		String username = session.getAttribute(ConfigConstant.USERNAME).toString();
		model.addAttribute("newcount", newcount);
		model.addAttribute("username", username);
		logger.info("执行后:{}", model);
		return "newdata";
	}

	@RequestMapping("teststr")
	public @ResponseBody String teststr() {
		return "this is str";
	}
	
//	@Timed
	@RequestMapping("/datacount")
	public @ResponseBody Map<String, Object> datacount() {
		logger.debug("获取datacount");
		List<Map<String, Object>> counts = userService.getDataSum();
		JSONArray categorys = new JSONArray();
		JSONArray nums = new JSONArray();
		for (Map<String, Object> m : counts) {
			categorys.add(m.get("adddate")==null?"":m.get("adddate").toString());
			nums.add(m.get("num").toString());
		}
		logger.debug("categorys:{},nums:{}", categorys, nums);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("c", categorys);
		data.put("d", nums);
		return data;
	}

	@RequestMapping("/export")
	public ResponseEntity<byte[]> export(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		List<User> users = userService.getUserList("desc", 10, 0);
		String projectPath = request.getServletContext().getRealPath("export")
				+ File.separator;
		File dir=new File(projectPath);
		if(!dir.exists()){
			if(dir.mkdir()){
				logger.debug("创建目录:{}",dir.getAbsolutePath());
			}else{
				logger.debug("创建目录:{}失败!,请检查权限!",dir.getAbsolutePath());
				throw new RuntimeException("没有创建:"+dir.getAbsolutePath()+"目录的权限!");
			}
		}
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
		List<String> columns = new ArrayList<String>(mps.get(0).size() - 1);
		columns.add("添加时间");
		columns.add("年龄");
		columns.add("收货地址");
		columns.add("ID");
		columns.add("名字");
		columns.add("手机");
		columns.add("性别");
		String file = projectPath + format.format(new Date()) + "."
				+ ConfigConstant.EXCELSTR;
		logger.info("文件路径:{}", file);
		POIExcelUtil.export(titles,columns, mps, file);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//		String filename="中文";
		String filename="";
		try {
			filename = URLEncoder.encode(file.replace(projectPath, ""),"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
//		try {
//			filename=MimeUtility.encodeWord(filename);
//		} catch (UnsupportedEncodingException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
//		try {
////			"gbk"
//			filename=new String(filename.getBytes(),"iso-8859-1");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//			throw new RuntimeException(e1.getMessage());
//		}
//		中文编码
//		String userAgentStr = request.getHeader("user-agent");
//		if(userAgentStr!=null&&userAgentStr.toLowerCase().indexOf("msie")!=-1){//ie浏览器
//			filename = URLEncoder.encode(filename,"utf-8");
//		}else if(userAgentStr!=null&&userAgentStr.toLowerCase().indexOf("trident")!=-1){//ie11浏览器
//			filename = URLEncoder.encode(filename,"utf-8");
//		}
//		filename = new String(filename.getBytes(),"ISO8859-1");
		
		logger.debug("下载文件名字:{}",filename);
		headers.setContentDispositionFormData("attachment",filename);
		try {
//			http://stackoverflow.com/questions/11203111/downloading-a-spring-mvc-generated-file-not-working-in-ie ie下载问题
			return new ResponseEntity<byte[]>(
					FileUtils.readFileToByteArray(new File(file)), headers,
					HttpStatus.OK);
//			HttpStatus.CREATED
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/files/{file_name}", method = RequestMethod.GET)
//	@ResponseBody
	public FileSystemResource getFile(@PathVariable("file_name") String fileName,HttpServletRequest request) {
	    return new FileSystemResource(new File(request.getServletContext().getRealPath("export")+ File.separator+fileName+".xls")); 
	}
	
	@RequestMapping("/testerror")
	public String testthrowException() {
		throw new RuntimeException("test error");
	}

	@ExceptionHandler
	public ModelAndView handleAllException(Exception ex) {
		ModelAndView mav = new ModelAndView("500");
		mav.addObject("errMsg", ex.getMessage());
		return mav;
	}

	/**
	 * 数据转换处理
	 */
	// @InitBinder
	// public void bind(WebDataBinder binder){
	// //设置不转换name，自行处理
	// binder.setDisallowedFields("name");
	// }
}