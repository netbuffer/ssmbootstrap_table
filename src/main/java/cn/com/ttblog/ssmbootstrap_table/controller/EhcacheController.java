package cn.com.ttblog.ssmbootstrap_table.controller;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

/**
 * 浏览ehcache状态 
 */
@Controller
@RequestMapping("/ehcache")
public class EhcacheController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
//	@Autowired  
//    private ApplicationContext applicationContext;
	@Autowired
	private CacheManager cacheManager;
	@RequestMapping(value = {"/index/{cache}" })
	public String index(@PathVariable("cache") String cache, ModelMap m) {
		logger.debug("cache:{}", cache);
		if(cacheManager!=null){
			m.put("caches",Arrays.deepToString(cacheManager.getCacheNames()));
			m.put("getDiskStorePath", cacheManager.getDiskStorePath());
			m.put("getName", cacheManager.getName());
//			m.put("getOriginalConfigurationText", cacheManager.getOriginalConfigurationText());
			m.put("getStatus", cacheManager.getStatus());
			Cache c=cacheManager.getCache(cache);
			if(c!=null){
				m.put("getStatistics",c.getStatistics());
				logger.debug("ehcache-{} getStatistics:{}",cache,c.getStatistics());
			}
		}
		logger.debug("ehcache model:{}",m);
		return "ehcache";
	}
}
