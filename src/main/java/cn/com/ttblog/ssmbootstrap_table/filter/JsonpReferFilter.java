package cn.com.ttblog.ssmbootstrap_table.filter;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * jsonp refer检测，只允许特定的refer调用jsonp接口
 * 
 * @author netbuffer
 */
public class JsonpReferFilter implements Filter {

	private FilterConfig filterConfig;
	private static final Logger log=LoggerFactory.getLogger(JsonpReferFilter.class);
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
		boolean enable = Boolean.parseBoolean(filterConfig.getInitParameter("enable"));
		// 不起用的情况下直接通过
		if (!enable) {
			filterChain.doFilter(httpServletRequest, httpServletResponse);
			return ;
		}
		String callback=httpServletRequest.getParameter("callback");
		if(callback==null){
			log.debug("非jsonp请求");
			filterChain.doFilter(httpServletRequest, httpServletResponse);
		}else{
			log.debug("jsonp请求:{}",httpServletRequest.getParameterMap());
			String referString = filterConfig.getInitParameter("refers");
			String[] refers = referString.split(";");
			if(refers.length>0){
				String userRefer=httpServletRequest.getHeader("Referer");
				log.debug("允许调用jsonp接口-refer列表:{},用户refer:{}",refers,userRefer);
				if(userRefer!=null){
					for(String r:refers){
						if(userRefer.contains(r)){
							filterChain.doFilter(httpServletRequest, httpServletResponse);
						}
					}
				}
			}
		}
	}

	@Override
	public void destroy() {
	}
}
