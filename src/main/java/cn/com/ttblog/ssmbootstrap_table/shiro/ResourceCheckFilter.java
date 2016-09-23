package cn.com.ttblog.ssmbootstrap_table.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceCheckFilter extends AccessControlFilter {
	private static final Logger LOG=LoggerFactory.getLogger(ResourceCheckFilter.class);
	private String errorUrl;
	
	public String getErrorUrl() {
		return errorUrl;
	}

	public void setErrorUrl(String errorUrl) {
		this.errorUrl = errorUrl;
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		Subject subject = getSubject(request, response);
		String url = getPathWithinApplication(request);
		boolean isPermit=subject.isPermitted(url);
		LOG.debug("shiro resource check,url:{},ispermitted:{}",url,isPermit);
		if(subject.hasRole("admin")){
			LOG.debug("has admin role,pass");
			return true; 
		}
		return isPermit;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		HttpServletResponse hsp = (HttpServletResponse)response;
		HttpServletRequest hReq = (HttpServletRequest)request;
		hsp.sendRedirect(hReq.getContextPath()+this.errorUrl);
		return false;
	}

}
