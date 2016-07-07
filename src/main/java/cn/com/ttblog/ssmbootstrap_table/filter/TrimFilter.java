package cn.com.ttblog.ssmbootstrap_table.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 过滤空白符号filter
 * 
 * @author netbuffer
 *
 */
public class TrimFilter implements Filter {
	private static final Logger trimFilterlogger = LoggerFactory
			.getLogger(TrimFilter.class);
	private FilterConfig filterConfig;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		boolean enable = Boolean.parseBoolean(filterConfig
				.getInitParameter("enable"));
		if (enable) {
			trimFilterlogger.trace("清除空白");
			chain.doFilter(
					new TrimRequestWrapper((HttpServletRequest) request),
					response);
		} else {
			trimFilterlogger.trace("直接放行");
			chain.doFilter(request, response);
		}
	}
}
