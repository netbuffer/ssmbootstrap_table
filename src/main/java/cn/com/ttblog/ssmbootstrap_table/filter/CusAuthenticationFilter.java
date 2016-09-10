package cn.com.ttblog.ssmbootstrap_table.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
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
		boolean login=super.executeLogin(request, response);
		LOG.debug("执行登陆,登录结果:{}",login);
		return login;
	}
	
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		LOG.debug("login accessdenied");
		return super.onAccessDenied(request, response);
	}
	
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
            ServletRequest request, ServletResponse response) throws Exception {
		boolean success=super.onLoginSuccess(token, subject, request, response);
		LOG.debug("login success:{}",success);
		return success;
	}
	
	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e,
            ServletRequest request, ServletResponse response) {
		boolean fail=super.onLoginFailure(token, e, request, response);
		LOG.debug("login fail：{}",fail);
		return fail;
	}
}
