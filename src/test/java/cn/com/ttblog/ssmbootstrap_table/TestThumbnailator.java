package cn.com.ttblog.ssmbootstrap_table;

import java.io.File;
import java.io.IOException;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http://rensanning.iteye.com/blog/1545708
 * Thumbnailator 图片裁切、缩放、水印等
 */
public class TestThumbnailator {
	
	private static final String FILENAME="thumbnailator.jpg";
	private static final Logger LOG=LoggerFactory.getLogger(TestThumbnailator.class);
	@Test
	public void testThumbnailator() throws IOException{
//		指定大小进行缩放
		/*   
	     * 若图片横比200小，高比300小，不变   
	     * 若图片横比200小，高比300大，高缩小到300，图片比例不变   
	     * 若图片横比200大，高比300小，横缩小到200，图片比例不变   
	     * 若图片横比200大，高比300大，图片按比例缩小，横为200或高为300   
	     */
		String file=this.getClass().getResource(FILENAME).getFile();
		File f=new File(file);
		LOG.debug("file-path:{},parent:{}",f.getAbsolutePath(),f.getParent());
	    Thumbnails.of(f.getAbsolutePath())   
	            .size(200, 300)
	            .toFile("thumbnailator"+RandomStringUtils.randomAlphabetic(5)+".jpg");  
	      
	    Thumbnails.of(f.getAbsolutePath())   
	            .size(2560, 2048)   
	            .toFile("thumbnailator"+RandomStringUtils.randomAlphabetic(5)+".jpg");
	    
	    Thumbnails.of(f.getAbsolutePath())   
        .scale(0.25f)  
        .toFile("thumbnailator"+RandomStringUtils.randomAlphabetic(5)+".jpg");  
  
		Thumbnails.of(f.getAbsolutePath())   
		        .scale(1.10f)  
		        .toFile("thumbnailator"+RandomStringUtils.randomAlphabetic(5)+".jpg");
	}
}
