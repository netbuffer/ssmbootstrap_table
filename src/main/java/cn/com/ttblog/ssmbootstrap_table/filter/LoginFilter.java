package cn.com.ttblog.ssmbootstrap_table.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.github.jscookie.javacookie.Cookies;
import cn.com.ttblog.ssmbootstrap_table.Constant.ConfigConstant;


public class LoginFilter implements Filter {

	private FilterConfig filterConfig;

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
				.getInitParameter("noFilterTags");
		boolean enable=Boolean.parseBoolean(filterConfig.getInitParameter("enable"));
		//不起用的情况下直接通过
		if(!enable){
			filterChain.doFilter(httpServletRequest,
					httpServletResponse);
			return ;
		}
		String[] noFilterTags = noFilterTagString.split(";");

		String uri = httpServletRequest.getRequestURI();
		System.out.println("过滤路径:"+uri);
		// 配置文件中允许放行的关键字
		if (noFilterTags != null) {
			for (String noFilterTag : noFilterTags) {
				if (noFilterTag == null || "".equals(noFilterTag.trim())) {
					continue;
				}
				if (uri.indexOf(noFilterTag.trim()) != -1) {
					System.out.println("uri:"+uri);
					filterChain.doFilter(httpServletRequest,
							httpServletResponse);
					return;
				}
			}
		}
		
		Cookie[] cookies=httpServletRequest.getCookies();
		Cookies cs=Cookies.initFromServlet(httpServletRequest, httpServletResponse);
		System.out.println("path:"+uri);
		System.out.println("cookies:"+cs.get().toString());
		Object islogin=httpServletRequest.getSession().getAttribute(ConfigConstant.ISLOGIN);
		if ( islogin!= null&&Boolean.parseBoolean(islogin.toString())) {
			System.out.println("p1");
			if(uri.endsWith(ConfigConstant.PROJECTNAME+"/")){
				httpServletResponse.sendRedirect(httpServletRequest
						.getContextPath() + "/manage.html");
			}else{
				filterChain.doFilter(httpServletRequest, httpServletResponse);
			}
		} else if(cookies!=null){
			System.out.println("p2");
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
				httpServletResponse.sendRedirect(httpServletRequest
						.getContextPath() + "/index.html");
				return ;
			}
		}else{
			System.out.println("^^^");
		}
		
	}

	@Override
	public void destroy() {

	}
}
