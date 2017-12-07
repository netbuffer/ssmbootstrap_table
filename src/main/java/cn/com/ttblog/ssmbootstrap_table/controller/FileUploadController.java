package cn.com.ttblog.ssmbootstrap_table.controller;

import cn.com.ttblog.ssmbootstrap_table.model.FileMsgBean;
import cn.com.ttblog.ssmbootstrap_table.util.AjaxUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/fileupload")
public class FileUploadController {
	
	private static final Logger log=LoggerFactory.getLogger(FileUploadController.class);
	
	@ModelAttribute
	public void ajaxAttribute(WebRequest request, Model model) {
		model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
	}

	@RequestMapping(method=RequestMethod.GET)
	public String fileUploadForm() {
		return "/user/photos";
	}

	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public void processUpload(@RequestParam(value = "file") MultipartFile file, Model model) throws IOException {
		log.info("文件上传信息：{}",ToStringBuilder.reflectionToString(file));
		log.info("文件上传,存储路径：{}",System.getProperty("webapp.root")+file.getOriginalFilename());
		file.transferTo(new File(System.getProperty("webapp.root")+file.getOriginalFilename()));
		model.addAttribute("message", "File '" + file.getOriginalFilename() + "' uploaded successfully");
	}
	
	@RequestMapping(value="/ajaxupload",method=RequestMethod.POST)
	@ResponseBody
	public FileMsgBean ajaxUpload(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
		log.info("ajax文件上传信息：{}",ToStringBuilder.reflectionToString(file));
		String dir=System.getProperty("webapp.root")+File.separator+"image"+File.separator;
		String originName=file.getOriginalFilename();
		String ext=originName.split("\\.")[1];
		log.info("file.getName()：{},file.getOriginalFilename()：{},originName.split(\".\")：{}",file.getName(),file.getOriginalFilename(),Arrays.deepToString(originName.split("\\.")));
		String usedName=Base64.encodeBase64String(originName.getBytes())+"."+ext;
		String saveImagePath=dir+usedName;
		String host=request.getHeader("host");
		String contextPath=request.getContextPath();
		String accessRelativeUrl=request.getScheme()+"://"+host+contextPath+"/image/";
		String imageUrl=accessRelativeUrl+usedName;
		log.info("host:{},contextPath:{},saveImagePath:{}",host,contextPath,saveImagePath);
		//缩率图
		File saveFile=new File(saveImagePath);
		log.info("ajax文件上传：{},存储路径：{},url:{}--name:{}",file,saveImagePath,imageUrl,file.getName());
		file.transferTo(saveFile);
		String thumbFileName=Base64.encodeBase64String((originName+"scale030").getBytes())+"."+ext;
		Thumbnails.of(saveFile).scale(0.30f).toFile(dir+thumbFileName);
		String thumbnailUrl=accessRelativeUrl+thumbFileName;
		FileMsgBean bean=new FileMsgBean();
		bean.setName(file.getOriginalFilename());
		bean.setSize(file.getSize());
		bean.setUrl(imageUrl);
		bean.setThumbnailUrl(thumbnailUrl);
		bean.setDeleteUrl("no delete url");
		return bean;
	}
	
	@RequestMapping(value="/multiupload", method = RequestMethod.POST)
    public @ResponseBody List<FileMsgBean> upload(MultipartHttpServletRequest request,@RequestParam(value = "multifile",required = false) MultipartFile[] files, HttpServletResponse response) {
		log.debug("ajax多文件上传,files:{}",files);
		String dir=System.getProperty("webapp.root")+File.separator+"image"+File.separator;
//		int sleep=RandomUtils.nextInt(2, 6);
//        log.debug("upload-sleep:{}s",sleep);
//		try {
//			TimeUnit.SECONDS.sleep(sleep);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
        String host=request.getHeader("host");
		String contextPath=request.getContextPath();
		String url=request.getScheme()+"://"+host+contextPath+"/image/";
        List<FileMsgBean> beans=new ArrayList<FileMsgBean>(2);
		for(MultipartFile f:files){
			try {
				f.transferTo(new File(dir+f.getOriginalFilename()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			FileMsgBean bean=new FileMsgBean();
			bean.setName(f.getName());
			bean.setSize(f.getSize());
			bean.setUrl(url+f.getOriginalFilename());
			bean.setThumbnailUrl(url+f.getOriginalFilename());
			bean.setDeleteUrl("no delete url");
			beans.add(bean);
		}
		return beans;
    }
}