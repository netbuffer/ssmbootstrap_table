package cn.com.ttblog.ssmbootstrap_table.controller;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import cn.com.ttblog.ssmbootstrap_table.util.AjaxUtils;

@Controller
@RequestMapping("/fileupload")
public class FileUploadController {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@ModelAttribute
	public void ajaxAttribute(WebRequest request, Model model) {
		model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
	}

	@RequestMapping(method=RequestMethod.GET)
	public void fileUploadForm() {
	}

	@RequestMapping(method=RequestMethod.POST)
	public void processUpload(@RequestParam MultipartFile file, Model model) throws IOException {
		log.info("文件上传信息：{}",ToStringBuilder.reflectionToString(file));
		log.info("文件上传,存储路径：{}",System.getProperty("webapp.root")+file.getOriginalFilename());
		file.transferTo(new File(System.getProperty("webapp.root")+file.getOriginalFilename()));
		model.addAttribute("message", "File '" + file.getOriginalFilename() + "' uploaded successfully");
	}
	
}