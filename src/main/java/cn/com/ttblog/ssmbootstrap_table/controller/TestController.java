package cn.com.ttblog.ssmbootstrap_table.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import cn.com.ttblog.ssmbootstrap_table.annotation.Token;
import cn.com.ttblog.ssmbootstrap_table.model.Address;
import cn.com.ttblog.ssmbootstrap_table.model.ExtendUser;
import cn.com.ttblog.ssmbootstrap_table.model.User;
import cn.com.ttblog.ssmbootstrap_table.util.AjaxUtils;
import eu.bitwalker.useragentutils.UserAgent;

@Controller
@RequestMapping("/test")
@SessionAttributes("name")
public class TestController {
	private Logger loggerAccess = LoggerFactory.getLogger("access");
//	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private Logger logger = loggerAccess;
	
	@Autowired  
	private ApplicationContext applicationContext;
	
	@Resource
	private Properties configProperties;
	@Value("#{configProperties['url2']}")
	private String url;
	@Value("#{configProperties['mysql.connectTime']}")
	private Integer connectTime;
	@Autowired
	private CookieLocaleResolver cookieResolver;
	//锁
	private Lock lock=new ReentrantLock();
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
	
	@RequestMapping(value = {"/getbean/{name}" })
	public @ResponseBody Object getbean(@PathVariable("name") String name) {
		return applicationContext.getBean(name);
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
	
	@Token(remove=true,tokenname="testformtoken",failuri="/user/error.jsp")
	@RequestMapping(value="/form",method=RequestMethod.POST)
	public String postform(@Valid User u){
//		,BindingResult result
		logger.debug("test post form:{}",u);
//		if(result.hasErrors()){
//			logger.info("校验user出错:"+ToStringBuilder.reflectionToString(result));
//			throw new RuntimeException("请填写正确的用户信息");
//		}
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
	
	@RequestMapping(value={"/ajax"})
	public String ajax(HttpServletRequest request){
		logger.debug("request.getHeader(\"X-Requested-With\"):{}",request.getHeader("X-Requested-With"));
		logger.debug("is ajax:{}",AjaxUtils.isAjaxRequest(request));
		return "index";
	}
	
	@RequestMapping(value={"/access"})
	public String access(){
		loggerAccess.info("access");
		return "index";
	}
	
	@RequestMapping(value={"/syn"})
	public synchronized String testSynchronized(ModelMap model){
		logger.warn("{}-执行synchronized操作!", Thread.currentThread().getName());
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new RuntimeException("服务器错误");
		}
		if(model.get("syn")==null){
			model.put("syn", Thread.currentThread().getName());
		}
		logger.warn("{}-执行synchronized操作完成", Thread.currentThread().getName());
		return "test";
	}

	@RequestMapping(value={"/syn2"})
	public String testSynchronized2(ModelMap model){
		lock.lock();
		logger.warn("{}-执行synchronized2操作!", Thread.currentThread().getName());
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new RuntimeException("服务器错误2");
		}
		if(model.get("syn")==null){
			model.put("syn", Thread.currentThread().getName());
		}
		logger.warn("{}-执行synchronized操作完成2", Thread.currentThread().getName());
		lock.unlock();
		return "test";
	}
	
	@RequestMapping(value={"/ue"})
	public String ue(ModelMap model){
		logger.debug("open ueditor");
		return "ueditor/index";
	}
	
	@RequestMapping(value={"/{no}/uri"})
	public String uri(ModelMap model){
		return "redirect:/{no}/index";//springmvc会对模板变量中的值解析处理
//		return "{no}/index";
	}
	
	@RequestMapping(value={"/websocket"})
	public String websocket(ModelMap model){
		return "websocket";
	}
	
	/**
	 * 创建二维码
	 * @param response
	 * @param param
	 * @throws IOException
	 * @throws WriterException
	 */
	@RequestMapping(value={"/qr"},method=RequestMethod.GET)
	public void qr(HttpServletResponse response,@RequestParam(value="param",defaultValue="test",required=false) String param,
			@RequestParam(value="width",defaultValue="200") int width,@RequestParam(value="height",defaultValue="200") int height,
			@RequestParam(value="format",defaultValue="jpg") String format,@RequestParam(value="r",defaultValue="0") int r,
			@RequestParam(value="g",defaultValue="0") int g,@RequestParam(value="b",defaultValue="0") int b) 
			throws IOException, WriterException{
		logger.debug("使用zxing生成二维码,内容:{}",param);
//		int width = 200; // 图像宽度  
//        int height = 200; // 图像高度  
//        String format = "png";// 图像类型  
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
//        http://www.tuicool.com/articles/vQFZNfq
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); //编码
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M); //容错率
        hints.put(EncodeHintType.MARGIN, 0);  //二维码边框宽度，这里文档说设置0-4
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");  
        BitMatrix bitMatrix = new MultiFormatWriter().encode(param,  
                BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵  
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0L);
		response.setContentType("image/jpeg");
		//颜色
		MatrixToImageConfig config = new MatrixToImageConfig(new Color(r,g,b).getRGB(),0xFFFFFFFF);
		ImageIO.write(MatrixToImageWriter.toBufferedImage(bitMatrix,config), "jpeg", jpegOutputStream);
		byte[] captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
		ServletOutputStream respOs = response.getOutputStream();
		respOs.write(captchaChallengeAsJpeg);
		respOs.flush();
		respOs.close();
	}
	
	@RequestMapping(value={"/decodeqr"},method=RequestMethod.GET)
	public String decodeqr(){
		logger.debug("二维码解析");
		return "decodeqr";
	}
	
	@RequestMapping(value={"/decodeqr"},method=RequestMethod.POST)
	@ResponseBody
	public String decodeqr(HttpServletRequest request,@RequestParam(value="qrimg",required=true)CommonsMultipartFile file) throws NotFoundException, IOException{
		if(file.isEmpty()){
			throw new RuntimeException("请上传文件");
		}
		BufferedImage image = ImageIO.read(file.getInputStream());  
        LuminanceSource source = new BufferedImageLuminanceSource(image);  
        Binarizer binarizer = new HybridBinarizer(source);  
        BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);  
        Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();  
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
        //对图像进行解码 
        Result result = new MultiFormatReader().decode(binaryBitmap, hints); 
        logger.debug("zxing解析二维码结果:{}",result);
        return result.getText();
	}
	
	@RequestMapping(value={"/server"},method=RequestMethod.GET)
	@ResponseBody
	public String server(HttpServletRequest request){
		return request.getServerName();
	}
	
	@RequestMapping(value={"/bean"},method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> bean(User u,Address a){
		//访问http://localhost:8080/ssmbootstrap_table/test/bean?name=mingzi&province=aaa&userId=1&sex=n，会自动组装对应的bean字段值
		Map<String, Object> result=new HashMap<>(2);
		result.put("u", u);
		result.put("a", a);
		logger.debug("test receive bean1:{}",result);
		return result;
	}
	
	@RequestMapping(value={"/beans"},method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> beansame(User u,ExtendUser eu){
		//http://localhost:8080/ssmbootstrap_table/test/beans?name=mingzisex=n ,会自动组装到所有的bean中
		Map<String, Object> result=new HashMap<>(2);
		result.put("u", u);
		result.put("eu", eu);
		logger.debug("test receive bean2:{}",result);
		return result;
	}
	
	@RequestMapping(value={"/getids"},method=RequestMethod.GET)
	@ResponseBody
	public String getids(String ids){
		logger.debug("用户输入ids格式:{}",ids);
		return ids;
	}
	
	@RequestMapping(value={"/ua"},method=RequestMethod.GET)
	@ResponseBody
	public UserAgent ua(HttpServletRequest request){
		UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
		logger.debug("用户ua:{}",userAgent);
		return userAgent;
	}
}
