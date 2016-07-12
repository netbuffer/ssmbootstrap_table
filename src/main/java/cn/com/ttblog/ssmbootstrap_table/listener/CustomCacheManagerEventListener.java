package cn.com.ttblog.ssmbootstrap_table.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Status;
import net.sf.ehcache.event.CacheManagerEventListener;

public class CustomCacheManagerEventListener implements CacheManagerEventListener {
	
	private static final Logger log=LoggerFactory.getLogger(CustomCacheManagerEventListener.class);
	
	private final CacheManager cacheManager;

	public CustomCacheManagerEventListener(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Override
	public void init() throws CacheException {
		log.info("init.....");
	}

	@Override
	public Status getStatus() {
		log.info("getStatus.....");
		return null;
	}

	@Override
	public void dispose() throws CacheException {
		log.info("dispose......");
	}

	@Override
	public void notifyCacheAdded(String cacheName) {
		log.info("cacheAdded......." + cacheName);
		log.info("getcache:{}",cacheManager.getCache(cacheName));
	}

	@Override
	public void notifyCacheRemoved(String cacheName) {
		log.info("cacheRemoved......" + cacheName);
	}

}