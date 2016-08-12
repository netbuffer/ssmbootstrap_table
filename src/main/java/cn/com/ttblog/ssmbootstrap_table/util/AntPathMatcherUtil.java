package cn.com.ttblog.ssmbootstrap_table.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;

public class AntPathMatcherUtil {
	
	private static final AntPathMatcher ap = new AntPathMatcher();
	private static final Logger LOG=LoggerFactory.getLogger(AntPathMatcherUtil.class);
	
	public static boolean isMatch(String[] mapping,String uri){
		if(mapping==null||mapping.length==0){
			throw new IllegalArgumentException("mapping不能为空");
		}
		for(String m:mapping){
			if(m.length()>0){
				String ms=m.trim();
				if(ap.match(ms,uri)){
					LOG.warn("匹配:{}---{}",ms,uri);
					return true;
				}
			}
		}
		return false;
	}
}
