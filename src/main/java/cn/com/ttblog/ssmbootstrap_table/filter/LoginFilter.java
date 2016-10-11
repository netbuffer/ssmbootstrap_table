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

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;

import com.alibaba.druid.support.logging.Log;
import com.github.jscookie.javacookie.Cookies;

import cn.com.ttblog.ssmbootstrap_table.Constant.ConfigConstant;
import cn.com.ttblog.ssmbootstrap_table.util.AntPathMatcherUtil;


public class LoginFilter implements Filter {

	private FilterConfig filterConfig;
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

		String noFilterTagString = filterConfig
				.getInitParameter("noFilterTags").trim();
		boolean enable=Boolean.parseBoolean(filterConfig.getInitParameter("enable"));
		//不起用的情况下直接通过
		if(!enable){
			filterChain.doFilter(httpServletRequest,
					httpServletResponse);
			return ;
		}
		
		String[] noFilterTags = noFilterTagString.split("\n");
		int length=noFilterTags.length;
		for(int i=0;i<length;i++){
			noFilterTags[i]=noFilterTags[i].trim();
		}
		LOG.debug("放行路径:{}-{},访问路径:{}",Arrays.toString(noFilterTags),noFilterTags.length,httpServletRequest.getRequestURI());
		if(AntPathMatcherUtil.isMatch(noFilterTags,httpServletRequest.getRequestURI())){
			filterChain.doFilter(httpServletRequest,
					httpServletResponse);
			LOG.debug("非拦截uri");
			return ;
		}
		String uri = httpServletRequest.getRequestURI();
		LOG.debug("过滤路径:{}",uri);
		// 配置文件中允许放行的关键字
		if (noFilterTags != null) {
			for (String noFilterTag : noFilterTags) {
				if (noFilterTag == null || "".equals(noFilterTag.trim())) {
					continue;
				}
				if (uri.indexOf(noFilterTag.trim()) != -1) {
					LOG.debug("uri:"+uri);
					filterChain.doFilter(httpServletRequest,
							httpServletResponse);
					return;
				}
			}
		}
		
		Cookie[] cookies=httpServletRequest.getCookies();
		Cookies cs=Cookies.initFromServlet(httpServletRequest, httpServletResponse);
		LOG.debug("path:"+uri);
		LOG.debug("cookies:"+cs.get().toString());
		Object islogin=httpServletRequest.getSession().getAttribute(ConfigConstant.ISLOGIN);
		if ( islogin!= null&&Boolean.parseBoolean(islogin.toString())) {
			LOG.debug("p1");
			if(uri.endsWith(ConfigConstant.PROJECTNAME+"/")){
				httpServletResponse.sendRedirect(httpServletRequest
						.getContextPath() + "/manage.html");
			}else{
				filterChain.doFilter(httpServletRequest, httpServletResponse);
			}
		} else if(cookies!=null){
			LOG.debug("p2");
			boolean find=false;
			for(Cookie cookie:cookies){
				if(cookie.getName().equals(ConfigConstant.USERNAME)&&cookie.getValue().length()>0){
					find=true;
					httpServletRequest.getSession().setAttribute(ConfigConstant.ISLOGIN, true);
					httpServletRequest.getSession().setAttribute(ConfigConstant.USERNAME, cookie.getValue());
					if(uri.endsWith(ConfigConstant.PROJECTNAME+"/")){
						httpServletResponse.sendRedirect(httpServletRequest
								.getContextPath() + "/manage.html");
					}else{
						filterChain.doFilter(httpServletRequest, httpServletResponse);
					}
				}
			}
			if(!find){
				//关于committed状态 http://blog.csdn.net/jubincn/article/details/8920573
				if(!httpServletResponse.isCommitted()){
					//记录之前访问的参数
					String requrib=httpServletRequest.getRequestURI()+"?"+httpServletRequest.getQueryString();
					String requri=Base64.encodeBase64String(requrib.getBytes());
					httpServletResponse.sendRedirect(httpServletRequest
							.getContextPath() + "/index.html?requri="+requri);
				}
				return ;
			}
		}else{
			LOG.debug("^^^no cookie，no session");
//			if(uri.endsWith(ConfigConstant.PROJECTNAME+"/")){
//			httpServletResponse.sendRedirect(httpServletRequest
//					.getContextPath() + "/index.html");
//			}
			if(!httpServletResponse.isCommitted()){
				//记录之前访问的参数
				String requrib=httpServletRequest.getRequestURI()+"?"+httpServletRequest.getQueryString();
				String requri=Base64.encodeBase64String(requrib.getBytes());
				httpServletResponse.sendRedirect(httpServletRequest
						.getContextPath() + "/index.html?requri="+requri);
			}
			return ;
		}
		
	}

	@Override
	public void destroy() {

	}
}
