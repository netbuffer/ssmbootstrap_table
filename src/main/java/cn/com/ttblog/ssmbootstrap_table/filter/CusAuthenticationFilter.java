package cn.com.ttblog.ssmbootstrap_table.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component(value="cfauth")
public class CusAuthenticationFilter extends FormAuthenticationFilter {
	
	private static final Logger LOG=LoggerFactory.getLogger(CusAuthenticationFilter.class);
	
	@Override
	protected boolean executeLogin(ServletRequest request,
			ServletResponse response) throws Exception {
		if(SecurityUtils.getSubject().isAuthenticated()){
			LOG.debug("已经认证过!");
			return false;
		}
		boolean login=super.executeLogin(request, response);
		LOG.debug("executeLogin:{}",login);
		return login;
	}
	
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		LOG.debug("login accessdenied");
		if(this.isLoginRequest(request, response)) {
			if(this.isLoginSubmission(request, response)) {
				LOG.debug("LOGin submission detected.  Attempting to execute LOGin.");
				return super.executeLogin(request, response);
			} else {
				LOG.debug("LOGin page view.");
				return true;
			}
		} else {
			LOG.debug("Attempting to access a path which requires authentication.  Forwarding to the Authentication url [" + this.getLoginUrl() + "]");
			this.saveRequestAndRedirectToLogin(request, response);
			return false;
		}
	}

	@Override
	protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
		String loginUrl = this.getLoginUrl();
		//可以在这里面做未登录地址的跳转规则，跳转到不同的页面
		LOG.debug("未登录要跳转到的地址:{},req uri:{}",loginUrl,((HttpServletRequest)request).getRequestURI());
		WebUtils.issueRedirect(request, response, loginUrl);
	}

	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
            ServletRequest request, ServletResponse response) throws Exception {
		boolean success=super.onLoginSuccess(token, subject, request, response);
		LOG.debug("login success execute:{}",success);
		//success为false使得阻止向下传递chain，进行重定向操作
		return success;
	}
	
	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e,
            ServletRequest request, ServletResponse response) {
		boolean fail=super.onLoginFailure(token, e, request, response);
		//fail为true使得返回到登陆页
		LOG.debug("login fail execute:{}",fail);
		return fail;
	}
}
