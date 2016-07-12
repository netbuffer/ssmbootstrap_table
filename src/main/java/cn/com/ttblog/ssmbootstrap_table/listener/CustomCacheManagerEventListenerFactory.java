package cn.com.ttblog.ssmbootstrap_table.listener;

import java.util.Properties;
import net.sf.ehcache.event.CacheManagerEventListener;
import net.sf.ehcache.event.CacheManagerEventListenerFactory;

public class CustomCacheManagerEventListenerFactory extends CacheManagerEventListenerFactory {

	@Override
	public CacheManagerEventListener createCacheManagerEventListener(Properties properties) {
		return null;
	}

}