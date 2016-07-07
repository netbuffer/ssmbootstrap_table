package cn.com.ttblog.ssmbootstrap_table.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
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
	@Autowired  
    private ApplicationContext applicationContext;

	@RequestMapping(value = {"/index/{cache}" })
	public String index(@PathVariable("cache") String cache, ModelMap m) {
		logger.debug("cache:{}", cache);
		EhCacheCacheManager cacheManager=(EhCacheCacheManager) applicationContext.getBean("cacheManager");
		if(cacheManager!=null){
			m.put("caches", cacheManager.getCacheNames());
			CacheManager cm=cacheManager.getCacheManager();
			m.put("getDiskStorePath", cm.getDiskStorePath());
			m.put("getName", cm.getName());
//			m.put("getOriginalConfigurationText", cm.getOriginalConfigurationText());
			m.put("getStatus", cm.getStatus());
			Cache c=cm.getCache(cache);
			if(c!=null){
				m.put("getStatistics",c.getStatistics());
				logger.debug("ehcache-{} getStatistics:{}",cache,c.getStatistics());
			}
		}
		logger.debug("ehcache model:{}",m);
		return "ehcache";
	}
}
