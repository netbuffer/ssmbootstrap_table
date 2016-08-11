package cn.com.ttblog.ssmbootstrap_table.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DomainHttpServletRequestWrapper extends HttpServletRequestWrapper {
	private static final Logger log=LoggerFactory.getLogger(DomainHttpServletRequestWrapper.class);
	public DomainHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	public String getServerName() {
		log.debug("wrapper-get servername");
        return "deny";
    }
}