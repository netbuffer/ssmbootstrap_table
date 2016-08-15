package cn.com.ttblog.ssmbootstrap_table.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.codec.binary.Base64;
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
		String relativePath=request.getServletContext().getRealPath("image")+File.separator;
		String originName=file.getOriginalFilename();
		String ext=originName.split("\\.")[1];
		log.info("file.getName()：{},file.getOriginalFilename()：{},originName.split(\".\")：{}",file.getName(),file.getOriginalFilename(),Arrays.deepToString(originName.split("\\.")));
		String usedName=Base64.encodeBase64String(originName.getBytes())+"."+ext;
		String savePath=relativePath+usedName;
		String host=request.getHeader("host");
		String contextPath=request.getContextPath();
		String url=request.getScheme()+"://"+host+contextPath+"/image/"+usedName;
		log.info("host:{},contextPath:{},relativePath:{},savePath:{}",host,contextPath,relativePath,savePath);
		//缩率图
		File saveFile=new File(savePath);
		log.info("ajax文件上传：{},存储路径：{},url:{}--name:{}",ToStringBuilder.reflectionToString(new File(filename)),filename,url,file.getName());
		file.transferTo(saveFile);
		String thumbFileName=Base64.encodeBase64String((originName+"scale030").getBytes())+"."+ext;
		Thumbnails.of(saveFile).scale(0.30f).toFile(relativePath+thumbFileName);
		FileMsgBean bean=new FileMsgBean();
		bean.setName(file.getOriginalFilename());
		bean.setSize(file.getSize());
		bean.setUrl(url);
		bean.setThumbnailUrl(request.getScheme()+"://"+host+contextPath+"/image/"+thumbFileName);
		bean.setDeleteUrl("url");
		return bean;
	}
}