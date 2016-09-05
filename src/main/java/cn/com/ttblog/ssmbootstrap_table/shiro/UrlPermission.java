package cn.com.ttblog.ssmbootstrap_table.shiro;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.util.PatternMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UrlPermission implements Permission {
	private static final Logger LOG=LoggerFactory.getLogger(UrlPermission.class);
	
	private String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public UrlPermission() {
	}
	
	public UrlPermission(String url) {
		this.url = url;
	}

	@Override
	public boolean implies(Permission p) {
		if(!(p instanceof UrlPermission)) return false;
		UrlPermission up = (UrlPermission)p;
		// /admin/role/**
		PatternMatcher patternMatcher = new AntPathMatcher();
		LOG.debug(this.getUrl()+","+up.getUrl()+","+patternMatcher.matches(this.getUrl(), up.getUrl()));
		return patternMatcher.matches(this.getUrl(), up.getUrl());
	}
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}

}
