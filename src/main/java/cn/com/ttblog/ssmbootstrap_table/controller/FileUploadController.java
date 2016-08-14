package cn.com.ttblog.ssmbootstrap_table.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import cn.com.ttblog.ssmbootstrap_table.model.FileMsgBean;
import cn.com.ttblog.ssmbootstrap_table.util.AjaxUtils;

@Controller
@RequestMapping("/fileupload")
public class FileUploadController {
	
	private static final Logger log=LoggerFactory.getLogger(FileUploadController.class);
	
	@ModelAttribute
	public void ajaxAttribute(WebRequest request, Model model) {
		model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
	}

	@RequestMapping(method=RequestMethod.GET)
	public void fileUploadForm() {
	}

	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public void processUpload(@RequestParam MultipartFile file, Model model) throws IOException {
		log.info("文件上传信息：{}",ToStringBuilder.reflectionToString(file));
		log.info("文件上传,存储路径：{}",System.getProperty("webapp.root")+file.getOriginalFilename());
		file.transferTo(new File(System.getProperty("webapp.root")+file.getOriginalFilename()));
		model.addAttribute("message", "File '" + file.getOriginalFilename() + "' uploaded successfully");
	}
	
	@RequestMapping(value="/ajaxupload",method=RequestMethod.POST)
	@ResponseBody
	public FileMsgBean ajaxUpload(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
		log.info("ajax文件上传信息：{}",ToStringBuilder.reflectionToString(file));
		String filename=System.getProperty("webapp.root")+File.separator+"image"+File.separator+file.getOriginalFilename();
		String url=request.getContextPath()+"/image/"+file.getOriginalFilename();
		//缩率图
//		Thumbnails.of(f.getAbsolutePath()).scale(1.10f).toFile("thumbnailator"+RandomStringUtils.randomAlphabetic(5)+".jpg");
		log.info("ajax文件上传,存储路径：{},url:{}",filename,url);
		FileMsgBean bean=new FileMsgBean();
		bean.setName(file.getOriginalFilename());
		bean.setSize(file.getSize());
		bean.setUrl(url);
		bean.setThumbnailUrl("url");
		bean.setDeleteUrl("url");
		return bean;
	}
}