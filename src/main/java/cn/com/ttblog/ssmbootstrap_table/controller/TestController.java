package cn.com.ttblog.ssmbootstrap_table.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.octo.captcha.service.image.ImageCaptchaService;

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
	@Autowired
	private ImageCaptchaService imageCaptchaService;

	@RequestMapping(value="/jcaptcha",method=RequestMethod.GET)
	public void jcaptcha(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("创建图片验证码");
		try {
			ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
			String captchaId = request.getSession().getId();
			BufferedImage challenge = imageCaptchaService.getImageChallengeForID(captchaId, request.getLocale());
			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0L);
			response.setContentType("image/jpeg");
			ImageIO.write(challenge, "jpeg", jpegOutputStream);
			byte[] captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
			ServletOutputStream respOs = response.getOutputStream();
			respOs.write(captchaChallengeAsJpeg);
			respOs.flush();
			respOs.close();
		} catch (IOException e) {
			logger.error("generate captcha image error: {}", e.getMessage());
		}
	}

	@RequestMapping(value = { "", "/{id}", "/index/{id}" })
	public String index(@PathVariable("id") int id, ModelMap m) {
		logger.debug("template id:{}", id);
		m.addAttribute("uri", id);
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

	@RequestMapping(value = { "/aaa" })
	public String aaa() {
		logger.debug("aaa");
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("key1", "v1");
		res.put("key2", "v2");
		return "test";
	}
}
