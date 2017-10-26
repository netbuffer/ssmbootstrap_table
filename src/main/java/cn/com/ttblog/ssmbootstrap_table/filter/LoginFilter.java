package cn.com.ttblog.ssmbootstrap_table.filter;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.ttblog.ssmbootstrap_table.model.User;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.jscookie.javacookie.Cookies;
import cn.com.ttblog.ssmbootstrap_table.constant.ConfigConstant;
import cn.com.ttblog.ssmbootstrap_table.service.IUserService;
import cn.com.ttblog.ssmbootstrap_table.util.AntPathMatcherUtil;

public class LoginFilter implements Filter {

	private FilterConfig filterConfig;
	@Autowired
	private IUserService userService;

	private static final Logger LOG=LoggerFactory.getLogger(LoginFilter.class);
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest servletRequest,
						 ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
		String noFilterTagString = filterConfig.getInitParameter("noFilterTags").trim();
		boolean enable=Boolean.parseBoolean(filterConfig.getInitParameter("enable"));
		//不起用的情况下直接通过
		if(!enable){
			filterChain.doFilter(httpServletRequest,httpServletResponse);
			return ;
		}
		String[] noFilterTags = noFilterTagString.split("\n");
		int length=noFilterTags.length;
		for(int i=0;i<length;i++){
			noFilterTags[i]=noFilterTags[i].trim();
		}
		String uri = httpServletRequest.getRequestURI();
		LOG.debug("访问路径:{},放行路径:{}-{}",uri,noFilterTags.length,Arrays.toString(noFilterTags));
		if(AntPathMatcherUtil.isMatch(noFilterTags,uri)){
			filterChain.doFilter(httpServletRequest, httpServletResponse);
			LOG.debug("非拦截uri");
			return ;
		}
		Cookies cookies=Cookies.initFromServlet(httpServletRequest, httpServletResponse);
		LOG.debug("cookies:{}",cookies.get().toString());
		Object islogin=httpServletRequest.getSession().getAttribute(ConfigConstant.ISLOGIN);
		if ( islogin!= null&&Boolean.parseBoolean(islogin.toString())) {
			//从session检查登录状态
			checkStatusFromSession(httpServletRequest,httpServletResponse,filterChain);
			return;
		} else if(cookies!=null&&cookies.get().size()>0){
			//从cookie检查登录状态
			checkStatusFromCookie(httpServletRequest,httpServletResponse,filterChain);
			return;
		}else{
			LOG.debug("^^^no cookie，no session");
			if(!httpServletResponse.isCommitted()){
				redirect(httpServletRequest,httpServletResponse);
				return ;
			}else {
				LOG.info("no cookie&session httpServletResponse.isCommitted():{}",httpServletResponse.isCommitted());
			}
		}
	}

	private void checkStatusFromSession(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,FilterChain filterChain) throws IOException, ServletException {
		LOG.debug("get login status from session");
		String uri = httpServletRequest.getRequestURI();
		if(uri.endsWith(ConfigConstant.PROJECTNAME+"/")){
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/manage.html");
		}else{
			filterChain.doFilter(httpServletRequest, httpServletResponse);
		}
	}

	private void checkStatusFromCookie(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,FilterChain filterChain) throws IOException, ServletException {
		LOG.debug("get login status from cookie");
		String uri = httpServletRequest.getRequestURI();
		Cookie[] cookiesArray=httpServletRequest.getCookies();
		boolean find=false;
		for(Cookie cookie:cookiesArray){
			if(cookie.getName().equals(ConfigConstant.USERNAME)&&cookie.getValue().length()>0){
				User user=userService.getUserByName(cookie.getValue());
				LOG.warn("根据cookie:{}查询用户:{}",cookie,user);
				if(user==null){
					break;
				}
				find=true;
				httpServletRequest.getSession().setAttribute(ConfigConstant.ISLOGIN, true);
				httpServletRequest.getSession().setAttribute(ConfigConstant.USERNAME, cookie.getValue());
				if(uri.endsWith(ConfigConstant.PROJECTNAME+"/")){
					httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/manage.html");
				}else{
					break;
				}
			}
		}
		if(!find){
			//关于committed状态 http://blog.csdn.net/jubincn/article/details/8920573
			if(!httpServletResponse.isCommitted()){
				redirect(httpServletRequest,httpServletResponse);
			}else {
				LOG.info("no cookie httpServletResponse.isCommitted():{}",httpServletResponse.isCommitted());
			}
		}else {
			filterChain.doFilter(httpServletRequest,httpServletResponse);
		}
	}

	private void redirect(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws IOException {
		//记录之前访问的参数
		String uri = httpServletRequest.getRequestURI();
		if(StringUtils.isNotBlank(httpServletRequest.getQueryString())){
			uri+="?="+httpServletRequest.getQueryString();
		}
		String base64uri=Base64.encodeBase64String(uri.getBytes());
		httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/index.html?requri="+base64uri);
	}

	@Override
	public void destroy() {
		LOG.debug("loginfilter destory");
	}
}
