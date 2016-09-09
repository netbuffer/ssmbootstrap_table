package cn.com.ttblog.ssmbootstrap_table.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component(value="cfauth")
public class CusAuthenticationFilter extends FormAuthenticationFilter {
	
	private static final Logger LOG=LoggerFactory.getLogger(CusAuthenticationFilter.class);
	
	@Override
	protected boolean executeLogin(ServletRequest request,
			ServletResponse response) throws Exception {
		LOG.debug("执行登陆");
		super.executeLogin(request, response);
		return false;
	}
}
